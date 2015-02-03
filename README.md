# dwc-analysis

A clojure library to perform fast analysis on darwincore occurrences.

Best used with [dwc](http://github.com/diogok/dwc) lib.

## Usage

All functions expect and vector of hash-maps that represent the occurrences, such as:

  [
    {:occurrenceID "foo1" :decimalLatitude 10.10 :decimalLongitude 20.20}
    {:occurrenceID "foo2" :decimalLatitude 10.12 :decimalLongitude 20.24}
  ]

### AOO

Calculates area of occupancy (in kilometers) using a grid-size (default to 2 kilometers), and the polygons cells that match with the count of matches per cell in geojson format.

  (use 'dwc-analysis.aoo)

  (aoo occurrences) 
  (aoo occurrences 10) (comment "grid-size of 10km")
  => {:area 100000
      :grid [
        {:type "Polygon" :coordinates [] :count 1}
        {:type "Polygon" :coordinates [] :count 2}
      ]
     }

### EOO

Calculates the extent of occurrencce and returns the mininum convex polygon and area in kilometers:

  (use 'dwc-analysis.eoo)
   
  (eoo occurrences)
  => {:area 100000
      :polygon {:type "Polygon" :coordinates []}}

### Populations



### Risk Analysis

Perform an automated risk analysis based on IUCN methodology, applying only geospatial distribution data:

Categories:

- NE
- DD
- LC
- NT
- VU
- EN
- CR
- EW
- EX

From the IUCN Red List Categories and Criteria:

Criteria B 
- B1 (EOO)
-- EOO < 100km² = CR
-- EOO < 5000km² = EN
-- EOO < 20000km² = VU
- B2 (AOO)
-- AOO < 10km² = CR
-- AOO < 500km² = EN
-- AOO < 2000km² = VU
- Must also:
-- Locations = 1 for CR
-- Locations <= 5 for EN
-- Locations <= 10 for VU
-- AND one of (but we cant assess that due to lack of data)
--- decline of population, eoo or aoo
--- extreme fluctuation of population, eoo or aoo

VU D2
- (AOO < 20km² OR Locations <= 5) AND a threat (but we cant assess threats yet).

Usage:

  (require 'dwc-analysis.risk)

  (assess {:aoo 4 :eoo 256 :locations 1})
    => {}

## License

MIT

