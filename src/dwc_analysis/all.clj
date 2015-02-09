(ns dwc-analysis.all
  (:require [dwc-analysis.eoo :as eoo])
  (:use plumbing.core)
  (:use dwc-analysis.geo)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(defn point?
  [occ] 
   (and 
     (not (nil? occ))
     (not (nil? (:decimalLatitude occ)))
     (not (nil? (:decimalLongitude occ)))
     (number? (:decimalLatitude occ))
     (number? (:decimalLongitude occ))))

(defn recent?
  [occ] (or (= (:year occ) nil) (not (number? (:year occ))) (>= (:year occ) (- 2015 100))))

(defn historic?
  [occ] (and (not (nil? (:year occ))) (number? (:year occ)) (< (:year occ) (- 2015 100))))

(def all
 (graph/compile
   {
    :occurrences 
     (graph/compile 
       {:occurrences (fnk [data] data)
        :count (fnk [occurrences] (count occurrences))
        :recent (fnk [occurrences] (filter recent? occurrences))
        :count_recent (fnk [recent] (count recent))
        :historic (fnk [occurrences] (filter historic? occurrences))
        :count_historic (fnk [historic] (count historic))
       }
      )
    :points
     (graph/compile
       {:points (fnk [occurrences] (filter point? (:occurrences occurrences)))
        :count (fnk [points] (count points))
        :recent (fnk [points] (filter recent? points))
        :count_recent (fnk [recent] (count recent))
        :historic (fnk [points] (filter historic? points))
        :count_historic (fnk [historic] (count historic))
        :geo (fnk [points] 
               (->> points 
                 (mapv #(point (c (:decimalLongitude %) (:decimalLatitude %))))
                 (mapv #(hash-map :type "Feature" :properties {} :geometry (as-geojson %)))
                 (hash-map :type "FeatureCollection" :features)))}
     )
    }
  )
)

(defn all-analysis
  [occurrences]
   (all {:data occurrences}))
