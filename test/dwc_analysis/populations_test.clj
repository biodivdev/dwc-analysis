(ns dwc-analysis.populations_test
  (:use dwc-analysis.geo)
  (:use dwc-analysis.populations)
  (:use midje.sweet))

(fact "Some calculations"
      (let [occ0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
            occ1 {:decimalLatitude 12.12 :decimalLongitude 22.22}
            occ2 {:decimalLatitude 10.12 :decimalLongitude 20.22}
            occ3 {:decimalLatitude 10.122 :decimalLongitude 20.222}]
  (:max-distance (populations [])) => 0
  (:max-distance (populations [occ0 occ1] )) => (roughly 469.769)
  (:max-distance (populations [occ0 occ1 occ2] )) => (roughly 470.526)))

(fact "Lets do this!"
      (let [occ0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
            occ1 {:decimalLatitude 12.12 :decimalLongitude 22.22}
            occ2 {:decimalLatitude 10.12 :decimalLongitude 20.22}
            occ3 {:decimalLatitude 10.12 :decimalLongitude 20.22}]
  (count (:populations (populations []))) => 0
  (count (:populations (populations [occ0]))) => 1
  (count (:populations (populations [occ0 occ1]))) => 2
  (count (:populations (populations [occ0 occ1 occ2]))) => 3
  (count (:populations (populations [occ0 occ1 occ2 occ3]))) => 3
))

(fact "Some bad input"
  (:area (populations [])) => 0
  (:area (populations [nil])) => 0
  (:area (populations [{}])) => 0
  (:area (populations nil)) => 0
  (:populations (populations [])) => []
      )

