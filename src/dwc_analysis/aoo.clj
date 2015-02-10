(ns dwc-analysis.aoo
  (:use plumbing.core)
  (:use dwc-analysis.geo)
  (:require [plumbing.graph :as graph] [schema.core :as s])
  (:require [clojure.core.reducers :as r]))

(defn make-grid 
  ""
  [step points]
  (if (empty? points) []
    (let [max-lat (apply max (map first points))
          min-lat (apply min (map first points))
          max-lng (apply max (map second points))
          min-lng (apply min (map second points))]
      (into-array
        (for [lng (range (- min-lng step) (+ max-lng step) step)
              lat (range (- min-lat step) (+ max-lat step) step)]
                 (into-array
                 (mapv
                   int-array
                   [ 
                    [ lat lng ]
                    [ (+ lat step) lng]
                    [ (+ lat step) (+ lng step)]
                    [ lat (+ lng step)]
                   ]
                   )
                 )
        )))))

(defn point-in-cell?
  [cell point]
  (and 
    (>= (first point) (first (first cell)))
    (>= (last  point) (last  (first cell)))
    (< (first point) (first (second cell)))
    (< (last  point) (last  (last cell)))
    ))

(defn cell-has-point?
  [point cell]
   (point-in-cell? cell point))

(defn occs-to-points
  [occs]  
   (->> occs
     (filter point?)
     (mapv #(vector (:decimalLatitude %) (:decimalLongitude %)))
     (mapv (partial mapv #(* % 100)))
     (mapv (partial mapv int))
     (distinct)
     (mapv int-array)
     (into-array)
      ))

(defn filter-grid
  [grid points]
  (into []
    (filter 
      (fn [cell] (some (partial point-in-cell? cell) points))
      grid)))

(defn cluster-points
  [grid points]
   (group-by
     (fn [point]
       (first (filter (partial cell-has-point? point) grid)))
       points))


(def aoo-1
 (graph/compile
  {:points
     (fnk [occurrences] (occs-to-points occurrences))
   :big-grid 
     (fnk [points step]
       (->> points
         (partition-all 10)
         (pmap #(cluster-points (make-grid (* step 10) %) %))
         (reduce merge)))
   :grid 
     (fnk [big-grid step]
       (->> big-grid
         (map #(cluster-points (make-grid step (key %)) (val %)))
         (reduce concat)))
   :area 
    (fnk [grid step]
      (->> grid
           (map first)
           (count)
           (* (Math/pow step 2))
           (int))) 
   :geo
     (fnk [grid]
      { 
       :type "FeatureCollection"
       :features
        (map 
          (fn [cluster]
           {
            :type "Feature"
            :properties {}
            :geometry
             {
             :type "Polygon"
             :coordinates [(mapv (fn [cell] (reverse (mapv #(/ % 100) cell) )) (key cluster))]
             }
           }
          ) grid
        )
      }
     )
   }
  )
)


(defn aoo
  ([occs] (aoo occs 2))
  ([occs step] 
   (-> (aoo-1 {:occurrences occs :step step })
       (dissoc :small-grid :big-grid :points :occurrences)))
   )

