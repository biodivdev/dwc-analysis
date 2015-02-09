(ns dwc-analysis.conglomerates_test
  (:use [clojure.data.json :only [read-str write-str]])
  (:use dwc-analysis.geo)
  (:use dwc-analysis.conglomerates)
  (:use midje.sweet))

(defn resource
  [file]
   (read-str (slurp (clojure.java.io/resource file)) :key-fn keyword))

(fact "Some calculations"
      (let [occ0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
            occ1 {:decimalLatitude 12.12 :decimalLongitude 22.22}
            occ2 {:decimalLatitude 10.12 :decimalLongitude 20.22}
            occ3 {:decimalLatitude 10.122 :decimalLongitude 20.222}]
  (:max-distance (conglomerates [])) => 0
  (:max-distance (conglomerates [occ0 occ1] )) => (roughly 313.93)
  (:max-distance (conglomerates [occ0 occ1 occ2] )) => (roughly 313.93)))

(fact "Lets do this!"
      (let [occ0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
            occ1 {:decimalLatitude 12.12 :decimalLongitude 22.22}
            occ2 {:decimalLatitude 10.12 :decimalLongitude 20.22}
            occ3 {:decimalLatitude 10.12 :decimalLongitude 20.22}]
  (count (:features (:conglomerates (conglomerates []))) ) => 0
  (int (:area (conglomerates []))) => 0
  (count (:features (:conglomerates (conglomerates [occ0])))  )=> 1
  (int (:area (conglomerates [occ0]))) => 0
  (count (:features (:conglomerates (conglomerates [occ0 occ1]))) ) => 2
  (int (:area (conglomerates [occ0 occ1]))) => 6152525
  (count (:features (:conglomerates (conglomerates [occ0 occ1 occ2]))) ) => 2
  (count (:features (:conglomerates (conglomerates [occ0 occ1 occ2 occ3])) )) => 2
))

(fact "Some bad input"
  (:area (conglomerates [])) => 0
  (:area (conglomerates [nil])) => 0
  (:area (conglomerates [{}])) => 0
  (:area (conglomerates nil)) => 0
  (:conglomerates (conglomerates [])) => []
      )

(fact "Do not return strange stuff"
  (let [pops (conglomerates [{:decimalLatitude 10.10 :decimalLongitude 20.20}])]
    (write-str pops)
    ))

(fact "Close occurrences"
   (let [occs (resource "occs.json")
         occs1 (resource "occs1.json")
         pops (conglomerates occs)
         pops1 (conglomerates occs1)]
     (spit "resources/occs.buffers.geojson" (write-str (:buffers pops)) )
     (spit "resources/occs.pops.geojson" (write-str (:conglomerates pops) ))
     (:buffers pops) => (resource "occs.buffers.geojson")
     (:conglomerates pops) => (resource "occs.pops.geojson")
     (:buffers pops1) => (resource "occs1.buffers.geojson")
     (:conglomerates pops1) => (resource "occs1.pops.geojson")
   ))

(fact "Vicia faba"
   (let [occs (resource "Vicia_faba.json")
         pops (conglomerates occs)]
     (:buffers pops) => (resource "Vicia_faba.buffers.geojson")
     (:conglomerates pops) => (resource "Vicia_faba.pops.geojson")
     (:n_conglomerates pops) => 7
   ))


