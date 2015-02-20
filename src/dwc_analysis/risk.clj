(ns dwc-analysis.risk
  (:use plumbing.core)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(def table
  {"EX" 0
   "EW" 1
   "CR" 2
   "EN" 3
   "VU" 4
   "NT" 5
   "LC" 6
   "DD" 7
   ""   8})

(defn occ-count
  [number] 
   (if (<= number 2)
     {:category "DD" :criteria ""}))

(defn eoo
  [value] 
  (if (= value 0)
    {:category "DD" :criteria ""}
    (if (< value 100)
      {:category "CR" :criteria "B1"}
      (if (< value 5000)
        {:category "EN" :criteria "B1"}
        (if (< value 20000)
          {:category "VU" :criteria "B1"}
          (if (< value 50000)
            {:category "NT" :criteria ""}
            {:category "LC" :criteria ""}
            )
          )
        )
      )
    )
  )

(defn aoo
  [value] 
  (if (= value 0)
    {:category "DD" :criteria ""}
    (if (< value 10)
      {:category "CR" :criteria "B2"}
      (if (< value 500)
        {:category "EN" :criteria "B2"}
        (if (< value 2000)
          {:category "VU" :criteria "B2"}
          (if (< value 5000)
            {:category "NT" :criteria ""}
            {:category "LC" :criteria ""}
            )
          )
        )
      )
    )
  )

(defn locations
  [value] 
  (if (= value 1)
    {:category "CR" :criteria ""}
    (if (<= value 5)
      {:category "EN" :criteria ""}
      (if (<= value 10)
        {:category "VU" :criteria ""}
        {:category "" :criteria ""}
        )
      )
    )
  )

(defn assess
  [data]
  (->> (list 
        (if (:eoo data) (assoc (eoo (:eoo data)) :reason "EOO"))
        (if (:aoo data) (assoc (aoo (:aoo data)) :reason "AOO"))
        (if (:locations data) (assoc (locations (:locations data)) :reason "Locations"))
        (if (:occurrence_count data) (assoc (occ-count (:occurrence_count data)) :reason "Few occurrences")))
    (filter #(and (not (nil? %)) (not (nil? (:category %))) (not (empty? (:category %)))))
    (map #(if (and (or (= (:reason %) "AOO") (= (:reason %) "EOO") ) (:decline data)) (assoc % :criteria (str (:criteria %) "b") :reason (str "Decline of " (:reason %))) %))
    (sort-by #(table (:category %))))
)

