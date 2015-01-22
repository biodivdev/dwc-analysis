(ns dwc-analysis.risk)

(defn occ-count
  [number] 
   (if (<= number 2)
     {:category "DD" :note "More information is required."}
     {:category ""})
   )

(defn eoo
  [value] 
  (if (< value 100)
    {:category "CR" :criteria "B1" 
     :note "Extent of occurrence less them 100km² can classify the sepecie as Criticaly Endangered." }
    (if (< value 5000)
      {:category "EN" :criteria "B1"
       :note "Extent of occurrence less them 5000km² can classify the sepecie as Endangered." }
      (if (< value 20000)
        {:category "VU" :criteria "B1"
         :note "Extent of occurrence less them 20000km² can classify the sepecie as Vulnerable." }
        (if (< value 50000)
          {:category "NT" :criteria "" :note "Extent of occurrence large enough (< 50000km²) to be out of risk, but near it. "}
          {:category "LC" :criteria "" :note "Extent of occurrence large enough (>= 50000km²) to be classified as Least Concearn."}
          )
        )
      )
    )
  )

(defn aoo
  [value] 
  (if (< value 10)
    {:category "CR" :criteria "B2" :note "Area of occupancy of less them 10km² classify as Critically Endangered."}
    (if (< value 500)
      {:category "EN" :criteria "B2" :note "Area of occupancy of less them 500km² classify as Endangered."}
      (if (< value 2000)
        {:category "VU" :criteria "B2" :note "Area of occupancy of less them 2000km² classify as Vulnerable."}
        (if (< value 5000)
          {:category "NT" :criteria "" :note "Area of occupancy above risk, but near it (< 5000km²)."}
          {:category "LC" :criteria "" :note "Area of occupancy above risk (>= 5000km²)."}
          )
        )
      )
    )
  )

(defn locations
  [value] 
  (if (= value 1)
    {:category "CR"}
    (if (<= value 5)
      {:category "EN"}
      (if (<= value 10)
        {:category "VU"}
        {:category ""}
        )
      )
    )
  )

(defn assess
  [data]
  {:category "" :criteria "" :notes []})
