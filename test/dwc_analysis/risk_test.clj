(ns dwc-analysis.risk-test
  (:use dwc-analysis.risk)
  (:use midje.sweet))

(fact "Can DD low occurrences"
  (occ-count 1) => {:category "DD"}
  (occ-count 2) => {:category "DD"}
  (occ-count 3) => {:category ""}
  (occ-count 4) => {:category ""})

(fact "Can assess EOO"
  (eoo 10) => (contains {:category "CR" :criteria "B1"} )
  (eoo 99) => (contains {:category "CR" :criteria "B1"} )
  (eoo 100) => (contains {:category "EN" :criteria "B1"}) 
  (eoo 101) => (contains {:category "EN" :criteria "B1"})
  (eoo 501) => (contains {:category "EN" :criteria "B1"})
  (eoo 5000) => (contains {:category "VU" :criteria "B1"})
  (eoo 15000) => (contains {:category "VU" :criteria "B1"})
  (eoo 20000) => (contains {:category "NT" :criteria ""})
  (eoo 50000) => (contains {:category "LC" :criteria ""})
)

(fact "Can assess AOO"
  (aoo 1) => (contains {:category "CR" :criteria "B2"})
  (aoo 9) => (contains {:category "CR" :criteria "B2"})
  (aoo 10) => (contains {:category "EN" :criteria "B2"})
  (aoo 11) => (contains {:category "EN" :criteria "B2"})
  (aoo 51) => (contains {:category "EN" :criteria "B2"})
  (aoo 500) => (contains {:category "VU" :criteria "B2"})
  (aoo 1500) => (contains {:category "VU" :criteria "B2"})
  (aoo 2000) => (contains {:category "NT" :criteria ""})
  (aoo 5000) => (contains {:category "LC" :criteria ""}))

(fact "Can assess Locations"
  (locations 1) => (contains {:category "CR"})
  (locations 4) => (contains {:category "EN"})
  (locations 5) => (contains {:category "EN"})
  (locations 6) => (contains {:category "VU"})
  (locations 10) => (contains {:category "VU"})
  (locations 11) => (contains {:category ""})
  )

(fact "Can choose best/worst assessment"
  (assess {:aoo 1}) 
   => [{:category "CR" :criteria "B2" :reason "AOO"}]
  (assess {:aoo 10}) 
    => [{:category "EN" :criteria "B2" :reason "AOO"}]
  (assess {:aoo 10 :decline true}) 
    => [{:category "EN" :criteria "B2b" :reason "Decline of AOO" }]
  (assess {:aoo 10 :eoo 10 :decline true}) 
    => [{:category "CR" :criteria "B1b" :reason "Decline of EOO"} {:category "EN" :criteria "B2b" :reason "Decline of AOO"}]
  (assess {:aoo 10 :eoo 10}) 
    => [{:category "CR" :criteria "B1" :reason "EOO"} {:category "EN" :criteria "B2" :reason "AOO"}]
  (assess {:aoo 10 :eoo 100}) 
    => [{:category "EN" :criteria "B1" :reason "EOO"} {:category "EN" :criteria "B2" :reason "AOO"}]
  (assess {:aoo 10 :eoo 6000}) 
    => [{:category "EN" :criteria "B2" :reason "AOO"} {:category "VU" :criteria "B1" :reason "EOO"}]
  (assess {:aoo 10 :eoo 6000 :decline true}) 
    => [{:category "EN" :criteria "B2b" :reason "Decline of AOO"} {:category "VU" :criteria "B1b" :reason "Decline of EOO"}]
  )

