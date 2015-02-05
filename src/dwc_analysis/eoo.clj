(ns dwc-analysis.eoo
  (:use plumbing.core)
  (:require [plumbing.graph :as graph] [schema.core :as s])
  (:use dwc-analysis.geo))

(defn filter-occs
  [occ] 
   (and 
     (not (nil? occ))
     (not (nil? (:decimalLatitude occ)))
     (not (nil? (:decimalLongitude occ)))
     (number? (:decimalLatitude occ))
     (number? (:decimalLongitude occ))))

(def eoo-1
  (graph/compile
    {:occs
     (fnk [occurrences] 
       (->> occurrences
         (filter filter-occs)
         (map #(vector (:decimalLongitude %) (:decimalLatitude %))) 
         (distinct)))
     :points 
      (fnk [occs]
       (map #(point (c (first %) (last %))) occs))
     :raw-polygon
      (fnk [points]
        (if (empty? points) nil
          (if (>= (count points) 3)
            (convex-hull points)
            (union (map #(buffer-in-meters % 10000) points)))))
     :area 
      (fnk [raw-polygon]
        (if (nil? raw-polygon) 0
          (* (area raw-polygon) 10000)))
     :polygon 
      (fnk [raw-polygon area]
         {:type "Feature"
          :properties {:area area}
          :geometry (as-geojson raw-polygon)})
    }
  )
)
 
(defn eoo
  ""
  [occs] (-> (eoo-1 {:occurrences occs})
             (dissoc :points :raw-polygon :occs :occurrences)) )

