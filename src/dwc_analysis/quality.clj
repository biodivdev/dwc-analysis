(ns dwc-analysis.quality
  (:use plumbing.core)
  (:use dwc-analysis.geo)
  (:require [plumbing.graph :as graph] [schema.core :as s]))

(defn now-year
  [] (.get (java.util.Calendar/getInstance) java.util.Calendar/YEAR))

(defn grade
  [occ & funs]
  (let [r (map (fn [fun] (fun occ)) funs)]
  (count (filter true? r))))

(defn identification
  [occ]
  (grade occ
    #(not (nil? (:occurrenceID %) ))
    #(not (nil? (:identifiedBy %) ))
    #(and (not (nil? (:recordNumber %))) (not (nil? (:recordedBy %))))
    #(and (not (nil? (:day %))) (not (nil? (:month %))) (not (nil? (:year %))))
    #(and (not (nil? (:family %)))
      (or (and (not (nil? (:genus %))) (not (nil? (:specificEpithet %))))
          (>= (count (.split (or (:scientificName %) "") " ")) 2)))))

(defn georeference
  [occ]
  (grade occ
     point?
    #(or (not (nil? (:coordinatePrecision %))) (not (nil? (:coordinateUncertaintyInMeters %))))
    #(not (nil? (:geodeticDatum %)))
    #(not (nil? (:locality %)))
    #(and (not (nil? (:country %))) 
          (not (nil? (:stateProvince %))) 
          (not (nil? (:municipality %))))
   ))

(defn abundance
  [occs] 
  (min 5 (count occs)))

(defn linage
  [occ] 
   (let [date (Integer/parseInt (first (.split (str (or (:dateLastModified occ) (:year occ) 0)) "-")))
         diff (int (Math/floor (/ (- (now-year) date) 10)))
         diff (if (> diff 5) 5 (if (< diff 0) 0 diff))]
     (if (<= date 1500) 0 (max (- 5 diff) 1))))

(defn media
  [occs fun] 
   (if (= 0 (count occs)) 0.0
     (double (/ (apply + (map fun occs)) (count occs)))))

(defn analyse
  [occurrences]
  (let [occurrences (filter #(and (not (nil? %)) (not (empty? %))) occurrences)]
    {:identification (media occurrences identification)
     :georeference (media occurrences georeference)
     :linage (media occurrences linage)
     :abundance (double (abundance occurrences))}
  )
)

