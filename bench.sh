#/bin/bash

time java -server -Xmx4G -cp "'$(lein classpath)'" clojure.main bench.clj

