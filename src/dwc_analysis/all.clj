(ns dwc-analysis.all
  (:require [dwc-analysis.eoo :as eoo])
  (:require [dwc-analysis.aoo :as aoo])
  (:require [dwc-analysis.risk :as risk])
  (:require [dwc-analysis.clusters :as clusters])
  (:use plumbing.core)
  (:use dwc-analysis.geo)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(defn occ?
  [occ] (not (nil? occ)))

(defn now
  [] (java.util.Calendar/getInstance))

(defn year
  [] (.get (now) java.util.Calendar/YEAR ))

(defn recent?
  [occ] (or (= (:year occ) nil) (not (number? (:year occ))) (>= (:year occ) (- 2015 50))))

(defn historic?
  [occ] (and (not (nil? (:year occ))) (number? (:year occ)) (< (:year occ) (- 2015 50))))

(def all
 (graph/compile
   {
    :occurrences 
     (graph/compile 
       {:all (fnk [data] (filter occ? data))
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
    :eoo 
      (fnk [points]
           {:all      (eoo/eoo (:all points))
            :historic (eoo/eoo (:historic points))
            :recent   (eoo/eoo (:recent points))})
    :aoo 
      (fnk [points]
           {:cell_size 2
            :all      (aoo/aoo (:all points))
            :historic (aoo/aoo (:historic points))
            :recent   (aoo/aoo (:recent points))})
    :clusters 
      (fnk [points]
           {:all      (clusters/clusters (:all points))
            :historic (clusters/clusters (:historic points))
            :recent   (clusters/clusters (:recent points))})
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
   (-> (all {:data occurrences})
       (dissoc :data)))

