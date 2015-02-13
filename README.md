# dwc-analysis

A clojure library to perform fast analysis on darwincore occurrences.

Best used with [dwc](http://github.com/diogok/dwc) lib.

## Usage

Using leningen:

    [dwc-analysis "0.0.13"]

All functions expect and vector of hash-maps that represent the occurrences, such as:

  [
    {:occurrenceID "foo1" :decimalLatitude 10.10 :decimalLongitude 20.20}
    {:occurrenceID "foo2" :decimalLatitude 10.12 :decimalLongitude 20.24}
  ]

And must of them return GeoJSON conforming objects on the "geo" propertie, ready to put on a map.

### AOO

Calculates area of occupancy (in kilometers) using a grid-size (default to 2 kilometers), and the polygons cells that match with the count of matches per cell in geojson format.

  (use 'dwc-analysis.aoo)

  (aoo occurrences) 
  (aoo occurrences 10) (comment "grid-size of 10km")
  => {:area 100000
      :geo {
        :type "FeatureCollection",
        :features [
          {:type "Feature",
           :geometry {
             :type "Polygon",
             :coordinates [[ ... ]]
           }
          }
        ]
      }
     }

### EOO

Calculates the extent of occurrencce and returns the mininum convex polygon and area in kilometers:

  (use 'dwc-analysis.eoo)
   
  (eoo occurrences)
  => {:area 100000
      :geo {
        :type "FeatureCollection",
        :features [
          {:type "Feature",
           :geometry {
             :type "Polygon",
             :coordinates [[ ... ]]
           }
          }
        ]
      }
    } 

### Clusters 

Performs clustering of occurrences identifing conglomerates by following:

- Identify maximum distance between points
- Buffer all points with radius as 10% of maximum distance
- Union all buffers that overlaps

  (use 'dwc-analysis.clusters)
  (clusters occurrences)
  => {:count 10
      :area 10000
      :buffers {
        :type "FeatureCollection",
        :features [
          {:type "Feature",
           :geometry {
             :type "Polygon",
             :coordinates [[ ... ]]
           }
          }
        ]
      }
      :geo {
        :type "FeatureCollection",
        :features [
          {:type "Feature",
           :geometry {
             :type "Polygon",
             :coordinates [[ ... ]]
           }
          }
        ]
      }
    }

Note: The radius calculation is to be changed to a mean distance based of a minimum spaning tree.


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

  (assess {:aoo 4 :eoo 256 :locations 1 :decline true})
    => [{ :category "X" :criteria "Xxn" :reason "Locations" } ... ]

It returns all possible classifications, ordered by bigger risk, all parameters are optional.

### ALL

Perform all analysis, also dividing the occurrences by historic (> 50 years old) and recent.

Usage:
    
   (use 'dwc-analysis.all)

   (all-analysis occurrences)
    => {
      :occurrences {
        :all [],
        :count 10,
        :recent [],
        :count_recent 0,
        :historic [],
        :count_historic 0
      }
      :points {
        :all [],
        :count 10,
        :recent [],
        :count_recent 0,
        :historic [],
        :count_historic 0
        :geo {:type "FeatureCollection" :features []}
      },
      :eoo {
        :all {},
        :historic {},
        :recent {}
      }
      :aoo {
        :all {},
        :historic {},
        :recent {}
      }
      :clusters {
        :all {},
        :historic {},
        :recent {}
      }
      :risk-assessment []
    }

## License

MIT

