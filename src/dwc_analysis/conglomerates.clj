(ns dwc-analysis.conglomerates
  (:use dwc-analysis.geo)
  (:use plumbing.core)
  (:require [cljts.geom :as g])
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

(defn haversine
    [[lon1 lat1] [lon2 lat2]]
    (let [R 6372.8 ; kilometers
                  dlat (Math/toRadians (- lat2 lat1))
                  dlon (Math/toRadians (- lon2 lon1))
                  lat1 (Math/toRadians lat1)
                  lat2 (Math/toRadians lat2)
                  a (+ (* (Math/sin (/ dlat 2)) (Math/sin (/ dlat 2))) (* (Math/sin (/ dlon 2)) (Math/sin (/ dlon 2)) (Math/cos lat1) (Math/cos lat2)))]
          (* R 2 (Math/asin (Math/sqrt a)))))

(def conglomerates-0
  (graph/compile
    {:points
      (fnk [occurrences] 
        (->> occurrences
          (filter filter-occs)
          (map #(vector (:decimalLongitude %) (:decimalLatitude %)))
          (distinct)
          (map #(point (c (first %) (second %))))))
     :max-distance
      (fnk [points]
        (let [points-utm (map to-utm points)]
          (/ (apply max 1 (flatten (for [p0 points-utm] (for [p1 points-utm] (distance p0 p1))))) 1000)))
     :buffers-raw
      (fnk [points max-distance]
        (mapv #(buffer-in-meters % (* (* max-distance 0.1 ) 1000)) points))
     :buffers 
      (fnk [buffers-raw]
         (->> buffers-raw
           (mapv #(hash-map :type "Feature" :properties {:area (/ (area-in-meters %) 1000)} :geometry (as-geojson % )))
           (hash-map :type "FeatureCollection" :features)))
     :conglomerates
      (fnk [buffers-raw] 
        (let [all (union buffers-raw)
              n   (.getNumGeometries all)]
          (if (= n 0) (list)
            (if (= n 1) (list all)
             (for [i (range 0 n)]
               (.getGeometryN all i))))))
     :geo 
     (fnk [conglomerates] 
           (->> conglomerates
             (mapv #(hash-map :type "Feature" :properties {:area (/ (area-in-meters %) 1000)} :geometry (as-geojson % )))
             (hash-map :type "FeatureCollection" :features)))
     :n_conglomerates
      (fnk [conglomerates]
          (count (:features conglomerates)))
     :area 
      (fnk [conglomerates]
        (/ (apply + 0 (map area-in-meters conglomerates)) 1000))
     }))

(defn conglomerates
  ""
  [occs] (if (empty? (filter filter-occs occs))
          {:max-distance 0 :conglomerates [] :area 0}
          (-> (conglomerates-0 {:occurrences occs}) 
              (dissoc :conglomerates-raw :buffers-raw :points :occurrences :occs))))

