(defproject dwc-analysis "0.0.5"
  :description "Analysis on darwincore occurrences: EOO, AOO and Populations."
  :url "http://github.com/CNCFlora/dwc-analysis"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.5"]
                 [diogok/cljts "0.4.3"]]
  :repositories [["clojars" {:sign-releases false}]]
  :profiles {:uberjar {:aot :all}
             :jar {:aot :all}
             :dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.3"]]}})
