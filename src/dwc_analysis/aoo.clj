(ns dwc-analysis.aoo)

(defn make-grid 
  ""
  [step points]
  (let [max-lat (apply max (map first points))
        min-lat (apply min (map first points))
        max-lng (apply max (map second points))
        min-lng (apply min (map second points))]
    (into []
      (for [lng (range (- min-lng step) (+ max-lng step) step)
            lat (range (- min-lat step) (+ max-lat step) step)]
                 [ 
                  [ lat lng ]
                  [ (+ lat step) lng]
                  [ (+ lat step) (+ lng step)]
                  [ lat (+ lng step)]
                 ]
        ))))

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
     (mapv #(vector (:decimalLatitude %) (:decimalLongitude %)))
     (mapv (partial mapv #(* % 100)))
     (mapv (partial mapv int))))

(defn filter-grid
  [grid points]
  (into []
    (filter 
      (fn [cell] (some (partial point-in-cell? cell) points))
      grid)))

(defn cluster-points
  [grid points]
   (mapv
     #(vector (key %) (val %))
       (group-by
         (fn [point]
           (first (filter (partial cell-has-point? point) grid)))
         points)))

(defn prepare-result
  ""
  [grid] 
  {:area (* (count (map first grid) ) 4000)
   :grid 
    (map 
      (fn [cluster]
        {:type "Polygon"
         :attributes {:count (count (second cluster))}
         :geometry [ (map #(/ % 100) (first cluster) ) ]}
       )
      grid)})

(defn aoo-0
  [occs]
  (let [points (occs-to-points occs)]
    (reduce concat 
      (mapv
        (fn [cluster]
          (cluster-points
            (make-grid 2 (second cluster))
            (second cluster)))
        (cluster-points (make-grid 20 points) points)))))

(defn aoo
  [occs] (prepare-result (aoo-0 occs)))

