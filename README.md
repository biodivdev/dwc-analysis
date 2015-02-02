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

## License

MIT

