(ns dwc-analysis.populations
  (:use dwc-analysis.geo)
  (:use plumbing.core)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(defn filter-occs
  [occ] 
   (and 
     (not (nil? occ))
     (not (nil? (:decimalLatitude occ)))
     (not (nil? (:decimalLongitude occ)))
     (number? (:decimalLatitude occ))
     (number? (:decimalLongitude occ))))

(defn merge-buffers
  ""
  ([buffers]
   (
   (group-by
     (fn [b0]
       (some (fn [b1] (if (intersects? b0 b1) b1)) buffers))
     buffers)
   )
  ))


(def populations-0
  (graph/compile
    {:points 
      (fnk [occurrences] 
        (->> occurrences
          (filter filter-occs)
          (map #(point (c (:decimalLatitude %) (:decimalLongitude %))))))
     :max-distance
      (fnk [points]
        (/ (apply max 1000 (flatten (for [p0 points] (for [p1 points] (distance-in-meters p0 p1))))) 1000))
     :radius
      (fnk [max-distance] (* max-distance 0.1))
     :buffers
      (fnk [points radius]
          (map #(buffer-in-meters % (* radius 1000)) points))
     :groups
      (fnk [buffers] 
        (->> buffers
          (group-by (fn [b0] (if (some (fn [b1] (if (intersects? b0 b1) b1)) buffers) b0)))
          (mapv val)
          (mapv union)))
     :populations (fnk [groups] (mapv as-geojson groups))
     :area 
      (fnk [groups]
        (/ (apply + 0 (map area-in-meters groups)) 1000))
     }))

(defn populations
  ""
  [occs] (if (empty? (filter filter-occs occs))
          {:max-distance 0 :populations [] :area 0}
          (-> (populations-0 {:occurrences occs}) 
              (dissoc :groups :buffers :points))))



