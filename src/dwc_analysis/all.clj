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

(defn year
  [] (.get (now) java.util.Calendar/YEAR))

(defn recent?
  [occ] (or (= (:year occ) nil) (not (number? (:year occ))) (>= (:year occ) (- (year) 50))))

(defn historic?
  [occ] (and (not (nil? (:year occ))) (number? (:year occ)) (< (:year occ) (- (year) 50))))

(def all
 (graph/compile
   {
    :limited (fnk [data limit] (and (> limit 0) (< limit (count data))))
    :quality (fnk [data] (quality/analyse data))
    :occurrences 
     (graph/compile 
       {:all (fnk [data limited limit] (filter occ? data))
        :count (fnk [all] (count all))
        :recent (fnk [all] (filter recent? all))
        :count_recent (fnk [recent] (count recent))
        :historic (fnk [all] (filter historic? all))
        :count_historic (fnk [historic] (count historic))})
    :points
     (graph/compile
       {:all (fnk [occurrences] (filter point? (:all occurrences)))
        :count (fnk [all] (count all))
        :recent (fnk [all] (filter recent? all))
        :count_recent (fnk [recent] (count recent))
        :historic (fnk [all] (filter historic? all))
        :count_historic (fnk [historic] (count historic))
        :geo (fnk [all] 
               (->> all 
                 (mapv to-point)
                 (mapv #(hash-map :type "Feature" :properties {} :geometry (as-geojson %)))
                 (hash-map :type "FeatureCollection" :features)))})
    :points-cut 
     (fnk [points limited limit]
          {:all  (if limited (take limit (:all points)) (:all points))
           :historic  (if limited (take limit (:historic points)) (:historic points))
           :recent  (if limited (take limit (:recent points)) (:recent points))})
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
       (dissoc :data :points-cut)
       (assoc  :limit 1000)
       ))

