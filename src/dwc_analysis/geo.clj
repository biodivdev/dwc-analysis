(ns dwc-analysis.geo
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
(def intersects? relation/intersects?)

(defn distance-in-meters
  ([p0 p1] (distance-in-meters p0 p1 "EPSG:4326"))
  ([p0 p1 crs]
    (analysis/distance
      (transform/reproject p0 crs (utm p0))
      (transform/reproject p1 crs (utm p1)))))

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

(defn as-geojson
  ""
  [feature]
  (if (nil? feature) nil
    (read-str (io/write-geojson feature) :key-fn keyword)))

