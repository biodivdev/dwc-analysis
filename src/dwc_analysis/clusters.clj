(ns dwc-analysis.clusters
  (:use dwc-analysis.geo)
  (:use plumbing.core)
  (:require [cljts.geom :as g])
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(def clusters-0
  (graph/compile
    {:points
      (fnk [occurrences] 
        (->> occurrences
          (filter point?)
          (map #(vector (:decimalLongitude %) (:decimalLatitude %)))
          (distinct)
          (map #(point (c (first %)  (second %) )))))
     :max-distance-meters
      (fnk [points]
        (let [points-utm (map to-utm points)]
          (min 5000000 (apply max 1 (flatten (for [p0 points-utm] (for [p1 points-utm] (distance p0 p1))))))))
     :max-distance 
      (fnk [max-distance-meters] (/ max-distance-meters 1000))
     :buffers-raw
      (fnk [points max-distance-meters]
        (mapv #(buffer-in-meters % (* max-distance-meters 0.1)) points))
     :buffers 
      (fnk [buffers-raw]
         (->> buffers-raw
           (mapv #(hash-map :type "Feature" :properties {:area (/ (area-in-meters %) 1000)} :geometry (as-geojson % )))
           (hash-map :type "FeatureCollection" :features)))
     :clusters
      (fnk [buffers-raw] 
        (let [all (union buffers-raw)
              n   (.getNumGeometries all)]
          (if (= n 0) (list)
            (if (= n 1) (list all)
             (for [i (range 0 n)]
               (.getGeometryN all i))))))
     :geo 
     (fnk [clusters] 
           (->> clusters
             (mapv #(hash-map :type "Feature" :properties {:area (/ (area-in-meters %) 1000)} :geometry (as-geojson % )))
             (hash-map :type "FeatureCollection" :features)))
     :count
      (fnk [geo]
        (count (:features geo)))
     :area 
      (fnk [clusters]
        (/ (apply + 0 (map area-in-meters clusters)) 1000))
     }))

(defn clusters
  ""
  [occs] (if (empty? (filter point? occs))
          {:max-distance 0 :clusters [] :area 0 :geo {:type "FeatureCollection" :features []} :count 0}
          (-> (clusters-0 {:occurrences occs}) 
              (dissoc :clusters :buffers-raw :points :occurrences :occs))))

