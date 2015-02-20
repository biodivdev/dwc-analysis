(ns dwc-analysis.quality-test
  (:use [clojure.data.json :only [read-str write-str]])
  (:use dwc-analysis.quality)
  (:use midje.sweet))

(defn resource
  [file]
   (read-str (slurp (clojure.java.io/resource file)) :key-fn keyword))

(fact "Identification"
  (identification {}) => 0
  (identification {:occurrenceID 1}) => 1 ;; occurrenceID
  (identification {:day 1}) => 0
  (identification {:day 1 :month 10}) => 0
  (identification {:year 1992 :month 10}) => 0
  (identification {:day 1 :month 10 :year 1992}) => 1 ;; date
  (identification {:scientificName "Teste"}) => 0
  (identification {:scientificName "Teste teste"}) => 0
  (identification {:family "ACANTHACEAE" :scientificName "Teste teste"}) => 1
  (identification {:genus "Teste"}) => 0
  (identification {:family "ACANTHACEAE" :genus "Aphelandra"  :specificEpithet "longiflora"}) => 1
  (identification {:scientificname "Teste" :family "ACANTHACEAE" :genus "Aphelandra" :specificEpithet "longiflora" }) => 1  ;; taxonomy
  (identification {:recordedBy "me"}) => 0
  (identification {:recordNumber 1 }) => 0 
  (identification {:recordNumber 1 :recordedBy "me"}) => 1 ;; collector
  (identification {:identifiedBy "me"}) => 1 ;; identification
      )

(fact "Geographic"
  (georeference {}) => 0
  (georeference {:decimalLatitude 10.0 :decimalLongitude 0}) => 0 
  (georeference {:decimalLatitude 420 :decimalLongitude 10.0}) => 0 
  (georeference {:decimalLatitude 10.10 :decimalLongitude 20.20}) => 1 ;; lat/lng valid
  (georeference {:coordinateUncertaintyInMeters 10}) => 1
  (georeference {:coordinatePrecision 10}) => 1
  (georeference {:coordinateUncertaintyInMeters 10 :coordinatePrecision 10}) => 1 ;; precision
  (georeference {:geodeticDatum "WGS84"}) => 1 ;; datum
  (georeference {:locality "Here"}) => 1 ;; textual location
  (georeference {:country "Here" }) => 0 
  (georeference {:country "Here" :stateProvince "abc" :municipality "def"}) => 1  ;; more location...
      )

(fact "Abundance"
  (abundance []) => 0
  (abundance [{} {} {}]) => 3
  (abundance [{} {} {} {} {} {} {} {}]) => 5
      )

(fact "Linage"
  (linage {}) => 0
  (linage {:year 2015}) => 5
  (linage {:year 2010}) => 5
  (linage {:year 2009}) => 5
  (linage {:year 2000 :dateLastModified 2015}) => 5
  (linage {:dateLastModified "2015"}) => 5
  (linage {:dateLastModified "2015-10-5"}) => 5
  (linage {:dateLastModified "5"}) => 0
  (linage {:dateLastModified 5}) => 0
  (linage {:dateLastModified "5-10-2015"}) => 0
  (linage {:year (- 2015 51)}) => 1
  (linage {:year 2005}) => 4
  (linage {:year 2004}) => 4
      )

(fact "All"
  (analyse [{:occurrenceID "10"} {:occurrenceID "11" :family "Acanthaceae" :scientificName "Teste test"}])
     => (contains {:identification 1.5})
  (analyse [{:occurrenceID "10"} {:occurrenceID "11" :family "Acanthaceae" :scientificName "Teste test" :decimalLatitude 10.10 :decimalLongitude 20.20} ])
     => (contains {:identification 1.5 :georeference 0.5})
  (analyse [{:occurrenceID "10"} {:occurrenceID "11" :family "Acanthaceae" :scientificName "Teste test" :decimalLatitude 10.10 :decimalLongitude 20.20 :year 2015}  {:year 1950 :locality "Riverrun"}])
     => (contains {:identification 1.0 :linage 2.0 :abundance 3.0})
  (analyse []) => {:identification 0.0 :linage 0.0 :abundance 0.0 :georeference 0.0}
  (analyse [nil]) => {:identification 0.0 :linage 0.0 :abundance 0.0 :georeference 0.0}
  (analyse [nil {}]) => {:identification 0.0 :linage 0.0 :abundance 0.0 :georeference 0.0}
  (analyse [{}]) => {:identification 0.0 :linage 0.0 :abundance 0.0 :georeference 0.0}
      )

(fact "Real specie: Aphelandra longiflora"
  (write-str (analyse (resource "aphelandra_longiflora.json")) ))

