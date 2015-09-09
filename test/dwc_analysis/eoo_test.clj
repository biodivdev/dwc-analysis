(ns dwc-analysis.eoo-test
  (:use [clojure.data.json :only [read-str write-str]])
  (:use dwc-analysis.eoo)
  (:use midje.sweet))

(fact "Calculate EOO buffers"
 (let [o0 {:decimalLatitude -15.48333 :decimalLongitude -55.68333}
       o1 {:decimalLatitude -15.402872 :decimalLongitude -55.881867 :foo "bar"}
       o2 {:decimalLatitude -15.402872 :decimalLongitude -55.881867 :foo "fuz"}
       o3 {:decimalLatitude -15.402872 :decimalLongitude -55.881867}]
   (int (:area (eoo [o0]))) => (roughly 262)
   (int (:area (eoo [o1 o2 o3]))) => (roughly 262)
   (int (:area (eoo [o0 o1 o2 o3]))) => (roughly 525)
   ))

(fact "Calculate EOO buffers others"
 (let [o0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
       o1 {:decimalLatitude 14.10 :decimalLongitude 21.21}
       o2 {:decimalLatitude 14.10 :decimalLongitude 21.21}
       o3 {:decimalLatitude -15.15 :decimalLongitude -35.35}]
   (int (:area (eoo [ o3 ]) )) => (roughly 262)
   (int (:area (eoo [ o1 o2 ]) )) => (roughly 261)
   (:area (eoo [ o0 o1 ]) ) => (roughly 519)
   (eoo [ o0 o1 o2 ]) => (eoo [ o0 o1 ])
   ))

(fact "Calculate EOO convex-hull"
 (let [o0 {:decimalLatitude 10.10 :decimalLongitude 20.20}
       o1 {:decimalLatitude 14.10 :decimalLongitude 21.21}
       o2 {:decimalLatitude 14.12 :decimalLongitude 21.22}
       o3 {:decimalLatitude 1412 :decimalLongitude 2122}]
   (:area (eoo [ o0 o1 o2 ]) ) => (roughly 99)
   (:area (eoo [ o0 o1 o2 o3]) ) => (roughly 99)
   (eoo [ o0 o1 o2 o3])  => (eoo [o0 o1 o2]) 
   ))

(fact "Is distinct working"
  (int (:area (eoo (read-str (slurp (clojure.java.io/resource "Grazielanthus_arkeocarpus.json")) :key-fn keyword))))
    => (roughly 273))

(fact "Return only nice things"
  (write-str (eoo [{:decimalLatitude 10.10 :decimalLongitude 20.20}])))

(fact "Bad input"
 (eoo nil) => (contains {:area 0} )
 (eoo []) => (contains {:area 0 } )
 (eoo [{:decimalLatitude 1500 :decimalLongitude 1600} {:decimalLatitude -5700 :decimalLongitude -290.0}]) => (contains {:area 0 } )
 (eoo [nil {}]) => (contains {:area 0}))

(fact "Going big"
  (int (:area (eoo (reduce concat (mapv  #(read-str ( slurp (clojure.java.io/resource %) ) :key-fn keyword )  ["occs.json" "Vicia_faba.json" "Vplus.json" "occs1.json" "Grazielanthus_arkeocarpus.json"]) ))))
    => 205166202)

