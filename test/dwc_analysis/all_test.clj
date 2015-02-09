(ns dwc-analysis.all-test
  (:use dwc-analysis.all)
  (:use midje.sweet))

(fact "Can make ALL analysis"
 (all-analysis [{:decimalLatitude 10.10 :decimalLongitude 20.20} {}])
    => (contains 
         {
           :number_of_occurrences 2
           :number_of_occurrences_with_georeference 1
         } ))
