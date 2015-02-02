(ns dwc-analysis.aoo-test
  (:use dwc-analysis.aoo)
  (:use midje.sweet))

(fact "Grids"
  (count (make-grid 2 [[0 0] [4 4]]) )
      => 16
  (mapv vec (first (make-grid 2 [[0 0] [4 4]])))
    => [[-2 -2] [0 -2] [0 0] [-2 0]])

(fact "Can create a regular GRID"
  (mapv (fn [cell] (mapv vec cell)) (make-grid 20 [[10 20] [20 40] [20 10]]))
    => [
        [[-10 -10] [10 -10] [10 10] [-10 10]] 
        [[10 -10] [30 -10] [30 10] [10 10]] 
        [[30 -10] [50 -10] [50 10] [30 10]]
        [[-10 10] [10 10] [10 30] [-10 30]]
        [[10 10] [30 10] [30 30] [10 30]]
        [[30 10] [50 10] [50 30] [30 30]]
        [[-10 30] [10 30] [10 50] [-10 50]]
        [[10 30] [30 30] [30 50] [10 50]] 
        [[30 30] [50 30] [50 50] [30 50]]
        [[-10 50] [10 50] [10 70] [-10 70]]
        [[10 50] [30 50] [30 70] [10 70]]
        [[30 50] [50 50] [50 70] [30 70]]])


(fact "Can check if point is inside cell and if cell contain a point"
  (let [cell0  [[10 10] [20 10] [20 20] [10 20]]
        point0 [10 10]
        point1 [20 20]
        point2 [15 15]
        point3 [5 10]
        point4 [5 15]
        point5 [15 5] ]
    (point-in-cell? cell0 point0) => true
    (point-in-cell? cell0 point1) => false #_"only lower border"
    (point-in-cell? cell0 point2) => true
    (point-in-cell? cell0 point3) => false
    (point-in-cell? cell0 point4) => false
    (point-in-cell? cell0 point5) => false
    (cell-has-point? point0 cell0) => true
    (cell-has-point? point5 cell0) => false
    ))

(fact "Filter cells in a grid that have points"
  (let [grid (make-grid 20 [[10 20] [20 40] [20 10]])
        points [[10 10] [15 10] [30 25]]]
    (mapv #(mapv vec %) (filter-grid grid points))
     => [[[10 10] [30 10] [30 30] [10 30]] [[30 10] [50 10] [50 30] [30 30]]]
    ))

(fact "Cluster points in cell"
  (let [grid (make-grid 20 [[10 20] [20 40] [20 10]])
        points [[10 10] [15 10] [30 25]]]
   (mapv (fn [cell] (hash-map (mapv vec (key cell)) (mapv vec (val cell)) )) (cluster-points grid points))
     => [
         {
          [
           [10 10] [30 10] [30 30] [10 30]
          ]
          [
           [10 10] [15 10]
          ]
         }
         {
          [
           [30 10] [50 10] [50 30] [30 30]
          ]
          [
           [30 25]
          ]
         }
        ]
    ))


(fact "bad input"
  (aoo nil)
    => (contains {:area 0 :grid []} )
  (aoo [])
    => (contains {:area 0 :grid []} )
  (aoo [{} nil])
    => (contains {:area 0 :grid []} )
      )

(fact "AOO"
 (let [o0 {:decimalLatitude -10.10 :decimalLongitude -20.20}
       o1 {:decimalLatitude -24.12 :decimalLongitude -21.22}
       o2 {:decimalLatitude -24.1200001 :decimalLongitude -21.2200001}
       o3 {:decimalLatitude 10.10 :decimalLongitude 20.20}]
   (:area (aoo [o0 o1 o2]))=> 8
   (:area (aoo [o0])) => 4
   (:area (aoo [o0] 4)) => 16
   (map #(get-in % [:attributes :count]) (:grid (aoo [o0 o1 o2])) )=> (list 1 2)
   (map #(get-in % [:attributes :count]) (:grid (aoo [o0])) )=> (list 1)
   (count (:grid (aoo [o0 o1 o2]))) => 2
   (count (:grid (aoo [o3]))) => 1
   (mapv (fn[cell] (mapv float cell)) (first (:coordinates (first (:grid (aoo [o3])))) ) )
     => (mapv (fn [cell] (mapv float cell)) [[20.20 10.10] [20.20 10.12] [20.22 10.12] [20.22 10.10]] )
   ))

(fact "More AOO"
  (:area (aoo [
    {:decimalLatitude -12.966667  :decimalLongitude -41.333333}
    {:decimalLatitude -12.564514  :decimalLongitude -41.544109}
    {:decimalLatitude -13 :decimalLongitude -41.4}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.47  :decimalLongitude -41.43}
    {:decimalLatitude -12.46  :decimalLongitude -41.46}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -13.337144  :decimalLongitude -41.43961}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -13.337144  :decimalLongitude -41.43961}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.466667  :decimalLongitude -41.433333}
    {:decimalLatitude -12.551035  :decimalLongitude -41.572546}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -13 :decimalLongitude -41}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.453889  :decimalLongitude -41.403611}
    {:decimalLatitude -13.337144  :decimalLongitude -41.43961}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.466667  :decimalLongitude -41.433333}
    {:decimalLatitude -12.447222  :decimalLongitude -41.420556}
   ])) => 56

  (:area (aoo [
    {:decimalLatitude -20.568944  :decimalLongitude -41.784721}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -20.568944  :decimalLongitude -41.784721}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.935203  :decimalLongitude -43.471631}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
    {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -20.568944  :decimalLongitude -41.784721}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.398194  :decimalLongitude -44.634236}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
    {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
    {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
           ])) => 20

  )


