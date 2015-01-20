(ns dwc-analysis.eoo
  (:use [clojure.data.json :only [read-str write-str]])
  (:require [cljts.transform :as transform])
  (:require [cljts.analysis :as analysis])
  (:require [cljts.relation :as relation])
  (:require [cljts.geom :as geom])
  (:require [cljts.io :as io])
  (:import [com.vividsolutions.jts.geom
            GeometryFactory
            PrecisionModel
            PrecisionModel$Type
            Coordinate
            LinearRing
            Point
            Polygon
            Geometry]))

(def point geom/point)
(def c geom/c)
(def area geom/area)
(def utm transform/utmzone)

(defn area-in-meters
  ""
  ([polygon] (area-in-meters polygon "EPSG:4326"))
  ([polygon crs] 
   (area
     (transform/reproject
       polygon crs (utm polygon)))))

(defn convex-hull
  ""
  [points] 
   (analysis/convex-hull 
     (reduce analysis/union points)))

(defn buffer-in-meters
  ""
  ([point meters] (buffer-in-meters point meters "EPSG:4326"))
  ([point meters crs]
    (transform/reproject 
      (analysis/buffer
        (transform/reproject point crs (utm point))
        meters) (utm point) crs)))

(defn union
  ""
  [ features ]
   (reduce analysis/union features))

(defn filter-occs
  [occ] 
   (and 
     (not (nil? occ))
     (not (nil? (:decimalLatitude occ)))
     (not (nil? (:decimalLongitude occ)))
     (number? (:decimalLatitude occ))
     (number? (:decimalLongitude occ))))

(defn eoo
  ""
  [ occs ]
   (let [occs   (filter filter-occs (distinct occs) )
         points (map #(point (c (:decimalLongitude %) (:decimalLatitude %))) occs) 
         poli   (if (empty? points) nil 
                  (if (>= (count points) 3) 
                    (convex-hull points)
                    (union (map #(buffer-in-meters % 10000) points))
                  ))
                  ]
     (if (nil? poli)
       {:polygon nil :area 0}
       {:polygon (read-str (io/write-geojson poli))
        :area    (* (area poli) 10000)}
       )
       ))

