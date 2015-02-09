(ns dwc-analysis.all
  (:require [dwc-analysis.eoo :as eoo])
  (:use plumbing.core)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(def all
 (graph/compile
   {:number_of_occurrences (fnk [occurrences] (count occurrences))
    :occurrences_with_georeference (fnk [occurrences] (filter eoo/filter-occs occurrences))
    :number_of_occurrences_with_georeference (fnk [occurrences_with_georeference] (count occurrences_with_georeference))
    }))

(defn all-analysis
  [occurrences]
   (all {:occurrences occurrences}))
