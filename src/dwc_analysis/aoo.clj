(ns dwc-analysis.aoo
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

(defn filter-occs
  [occ] 
   (and 
     (not (nil? occ))
     (not (nil? (:decimalLatitude occ)))
     (not (nil? (:decimalLongitude occ)))
     (number? (:decimalLatitude occ))
     (number? (:decimalLongitude occ))))

(defn occs-to-points
  [occs]  
   (->> occs
     (filter filter-occs)
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

(defn prepare-result
  ""
  [grid] 
  {:area (* (count (map first grid) ) 4000)
   :grid 
    (map 
      (fn [cluster]
        {
         :type "Polygon"
         :attributes {:count (count (val cluster))}
         :coordinates [ (mapv (fn [cell] (reverse (mapv #(/ % 100) cell) )) (key cluster)) ]
         }
       )
      grid)})

(defn aoo-0
  ([occs] (aoo-0 occs 2))
  ([occs step]
    (let [points (occs-to-points occs)]
      (reduce concat 
        (map
          (fn [cluster]
            (cluster-points
              (make-grid step (key cluster))
              (val cluster)))
          (reduce merge
            (pmap
              (fn [points]
                (cluster-points (make-grid (* step 10) points) points))
              (partition-all 10 points)))
          )))))

(defn aoo
  ([occs] (aoo occs 2))
  ([occs step] (prepare-result (aoo-0 occs step))))

