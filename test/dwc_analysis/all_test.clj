(ns dwc-analysis.all-test
  (:use [clojure.data.json :only [read-str write-str]])
  (:use dwc-analysis.all)
  (:use midje.sweet))

(defn resource
  [file]
   (read-str (slurp (clojure.java.io/resource file)) :key-fn keyword))

(fact "Can make ALL analysis (simple)"
 (let [result (all-analysis [{:decimalLatitude 10.10 :decimalLongitude 20.20 :year 2000} {:year 1900} {}])]
   (:occurrences result)
    => (contains { :count 3 :count_historic 1 :count_recent 2 } )
   (:points result)
    => (contains { :count 1 :count_historic 0 :count_recent 1 } )
   (:area (:recent (:eoo result)))
    => (roughly 257.63 ) 
   (:historic (:eoo result))
    => (contains { :area 0 } )
   (:recent (:aoo result))
    => (contains { :area 4 } )
   (:historic (:aoo result))
    => (contains { :area 0 } )
   (:recent (:clusters result))
    => (contains { :count 1 } )
   (:historic (:clusters result))
    => (contains { :count 0 } )
 )
)

(fact "Work fine with big number. Limit aoo calcs (maybe?)"
      (println "test" )
  (get-in (all-analysis (map #(hash-map :decimalLatitude (/ % 100) :decimalLongitude (/ % 100)) (range 0 9000))) [:aoo :all :area]) => 2000)

(fact "Return only nice things"
  (write-str (all-analysis (resource "occs.json"))))

(fact "Bad input"
 (all-analysis nil) 
 (all-analysis []) 
 (all-analysis [nil {}]) 
)
