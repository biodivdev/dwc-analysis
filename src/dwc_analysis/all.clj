(ns dwc-analysis.all
  (:require [dwc-analysis.eoo :as eoo])
  (:require [dwc-analysis.aoo :as aoo])
  (:require [dwc-analysis.risk :as risk])
  (:require [dwc-analysis.clusters :as clusters])
  (:require [dwc-analysis.quality :as quality])
  (:use plumbing.core)
  (:use dwc-analysis.geo)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(defn occ?
  [occ] (not (nil? occ)))

(defn now
  [] (java.util.Calendar/getInstance))

(defn this-year
  [] (.get (now) java.util.Calendar/YEAR))

(defn historic?
  [occ]
  (let [year (if (number? (:year occ)) (:year occ) (try (Integer/parseInt (:year occ)) (catch Exception e 0)))]
    (and (not (nil? year))
         (number? year) 
         (> year 0)
         (< year (- (this-year) 50)))))

(defn recent?
  [occ]  (not (historic? occ)))

(def all
 (graph/compile
   {
    :limited (fnk [data limit] (and (> limit 0) (< limit (count data))))
    :quality (fnk [data] (quality/analyse data))
    :occs-hash 
     (fnk [data]
       (reduce 
        (fn [occs occ] (assoc occs (:occurrenceID occ) occ)) {}
        (map
          (fn [occ] 
            (if (not (nil? (:occurrenceID occ))) occ 
              (assoc occ :occurrenceID (str (java.util.UUID/randomUUID) ))))
        (filter occ? data))))
    :occurrences 
     (graph/compile 
       {:all (fnk [occs-hash limited limit] (map val occs-hash))
        :count (fnk [all] (count all))
        :recent (fnk [all] (map :occurrenceID (filter recent? all)))
        :count_recent (fnk [recent] (count recent))
        :historic (fnk [all] (map :occurrenceID (filter historic? all)))
        :count_historic (fnk [historic] (count historic))})
    :points
     (graph/compile
       {:all (fnk [occurrences] (filter point? (:all occurrences)))
        :count (fnk [all] (count all))
        :recent (fnk [all] (map :occurrenceID (filter recent? all)))
        :count_recent (fnk [recent] (count recent))
        :historic (fnk [all] (map :occurrenceID (filter historic? all)))
        :count_historic (fnk [historic] (count historic))
        :geo (fnk [all] 
               (->> all 
                 (mapv to-point)
                 (mapv #(hash-map :type "Feature" :properties {} :geometry (as-geojson %)))
                 (hash-map :type "FeatureCollection" :features)))})
    :points-cut 
     (fnk [occs-hash points limited limit]
        (reduce merge
          (map
            (fn [tag]
              {tag
                (map
                  (fn [id]
                    (get occs-hash (or (:occurrenceID id) id)))
                  (if limited
                    (take limit (tag points))
                    (tag points)))})
            [:all :historic :recent])))
    :eoo 
      (fnk [points-cut]
           {:all      (eoo/eoo (:all points-cut))
            :historic (eoo/eoo (:historic points-cut))
            :recent   (eoo/eoo (:recent points-cut))})
    :aoo 
      (fnk [points-cut]
           {:cell_size 2
            :all      (aoo/aoo (:all points-cut))
            :historic (aoo/aoo (:historic points-cut))
            :recent   (aoo/aoo (:recent points-cut))})
    :clusters 
      (fnk [points-cut]
           {:all      (clusters/clusters (:all points-cut))
            :historic (clusters/clusters (:historic points-cut))
            :recent   (clusters/clusters (:recent points-cut))})
    :aoo-variadic
      (fnk [points-cut clusters]
           {:all      (aoo/aoo (:all points-cut) (-> clusters :all :max-distance (* 0.1)))
            :historic (aoo/aoo (:historic points-cut) (-> clusters :historic :max-distance (* 0.1)))
            :recent   (aoo/aoo (:recent points-cut) (-> clusters :recent :max-distance (* 0.1)))})
    :risk-assessment 
      (fnk [points aoo eoo clusters]
         (risk/assess {:occurrence_count (count (:all points))
                       :aoo (:area (:all aoo)) 
                       :eoo (:area (:all eoo))
                       :decline (or (> (:area (:historic aoo)) (:area (:recent aoo)))
                                    (> (:area (:historic eoo)) (:area (:recent eoo))))}))
    }
  )
)

(defn all-analysis
  [occurrences]
   (-> (all {:data occurrences :limit 10000})
       (dissoc :data :points-cut :occs-hash)
       (dissoc-in [:occurrences :all])
       (dissoc-in [:points :all])
       (assoc  :limit 10000)
       ))

