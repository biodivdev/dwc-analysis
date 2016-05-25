(defproject dwc-analysis "0.0.29"
  :description "Analysis on darwincore occurrences: EOO, AOO and Populations."
  :url "http://github.com/CNCFlora/dwc-analysis"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [prismatic/plumbing "0.5.3"]
                 [org.clojars.diogok/cljts "0.4.7"]]
  :repositories [["osgeo" "http://download.osgeo.org/webdav/geotools/"]
                 ["clojars" {:sign-releases false}]]
  :profiles {:dev {:dependencies [[midje "1.8.3"]]
                   :plugins [[lein-midje "3.2"]]}})
