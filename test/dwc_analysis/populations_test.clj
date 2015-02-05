(ns dwc-analysis.populations_test
  (:use [clojure.data.json :only [read-str write-str]])
  (:use dwc-analysis.geo)
  (:use dwc-analysis.populations)
  (:use midje.sweet))

(defn resource
  [file]
   (read-str (slurp (clojure.java.io/resource file)) :key-fn keyword))

(fact "Some calculations"
      (let [occ0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
            occ1 {:decimalLatitude 12.12 :decimalLongitude 22.22}
            occ2 {:decimalLatitude 10.12 :decimalLongitude 20.22}
            occ3 {:decimalLatitude 10.122 :decimalLongitude 20.222}]
  (:max-distance (populations [])) => 0
  (:max-distance (populations [occ0 occ1] )) => (roughly 313.93)
  (:max-distance (populations [occ0 occ1 occ2] )) => (roughly 313.93)))

(fact "Lets do this!"
      (let [occ0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
            occ1 {:decimalLatitude 12.12 :decimalLongitude 22.22}
            occ2 {:decimalLatitude 10.12 :decimalLongitude 20.22}
            occ3 {:decimalLatitude 10.12 :decimalLongitude 20.22}]
  (count (:features (:populations (populations []))) ) => 0
  (int (:area (populations []))) => 0
  (count (:features (:populations (populations [occ0])))  )=> 1
  (int (:area (populations [occ0]))) => 31
  (count (:features (:populations (populations [occ0 occ1]))) ) => 2
  (int (:area (populations [occ0 occ1]))) => 6152525
  (count (:features (:populations (populations [occ0 occ1 occ2]))) ) => 2
  (count (:features (:populations (populations [occ0 occ1 occ2 occ3])) )) => 2
))

(fact "Some bad input"
  (:area (populations [])) => 0
  (:area (populations [nil])) => 0
  (:area (populations [{}])) => 0
  (:area (populations nil)) => 0
  (:populations (populations [])) => []
      )

(fact "Do not return strange stuff"
  (let [pops (populations [{:decimalLatitude 10.10 :decimalLongitude 20.20}])]
    (write-str pops)
    ))

(fact "Close occurrences"
   (let [occs (resource "occs.json")
         occs1 (resource "occs1.json")
         pops (populations occs)
         pops1 (populations occs1)]
     (:buffers pops) => (resource "occs.buffers.geojson")
     (:populations pops) => (resource "occs.pops.geojson")
     (:buffers pops1) => (resource "occs1.buffers.geojson")
     (:populations pops1) => (resource "occs1.pops.geojson")
   ))

(fact "Vicia faba"
   (let [occs (resource "Vicia_faba.json")
         pops (populations occs)]
     (:buffers pops) => (resource "Vicia_faba.buffers.geojson")
     (:populations pops) => (resource "Vicia_faba.pops.geojson")
     (:n_populations pops) => 7
   ))


