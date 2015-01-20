(use 'dwc-analysis.aoo)

(println "Go")
(Thread/sleep 5000)

#_(println "Half world grid")
#_(time (make-grid 0 9000 0 18000))
#_(println (count (make-grid 0 9000 0 18000)))

#_(println "Full world grid")
#_(time (make-grid -9000 9000 -18000 18000))
#_(println (count (make-grid -9000 9000 -18000 18000)))

#_(println "Half world AOO")
(def half-world-occs 
  [ 
   {:decimalLatitude -90 :decimalLongitude -180}
   {:decimalLatitude 0 :decimalLongitude 0} 
  ] )
#_(time (aoo half-world-occs))

#_(println "Full world AOO")
(def full-world-occs 
  [ 
   {:decimalLatitude -90 :decimalLongitude -180}
   {:decimalLatitude 0 :decimalLongitude 0} 
   {:decimalLatitude 90 :decimalLongitude 180}
  ] )
#_(time (aoo full-world-occs))

(println "Some brazil spps")

(time
(println
(:area (aoo [
    {:decimalLatitude -12.966667  :decimalLongitude -41.333333}
    {:decimalLatitude -12.564514  :decimalLongitude -41.544109}
    {:decimalLatitude -13 :decimalLongitude -41.4}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.47  :decimalLongitude -41.43}
    {:decimalLatitude -12.46  :decimalLongitude -41.46}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -13.337144  :decimalLongitude -41.43961}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -13.337144  :decimalLongitude -41.43961}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.466667  :decimalLongitude -41.433333}
    {:decimalLatitude -12.551035  :decimalLongitude -41.572546}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -13 :decimalLongitude -41}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.453889  :decimalLongitude -41.403611}
    {:decimalLatitude -13.337144  :decimalLongitude -41.43961}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.466667  :decimalLongitude -41.433333}
    {:decimalLatitude -12.447222  :decimalLongitude -41.420556}
   ])) 
)
)

(time
(println
(:area (aoo [
  {:decimalLatitude -20.568944  :decimalLongitude -41.784721}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -20.568944  :decimalLongitude -41.784721}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.935203  :decimalLongitude -43.471631}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
  {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -20.568944  :decimalLongitude -41.784721}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.398194  :decimalLongitude -44.634236}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
  {:decimalLatitude -22.531347  :decimalLongitude -44.568832}
  {:decimalLatitude -22.356565  :decimalLongitude -44.660074}
         ]))
)
)

(println "A big BR genus")

(def br-occs [
    {:decimalLatitude -12.608004  :decimalLongitude -41.879892}
    {:decimalLatitude -15.849769  :decimalLongitude -49.010191}
    {:decimalLatitude -13.581801  :decimalLongitude -41.131719}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.6   :decimalLongitude -41.4833}
    {:decimalLatitude -14.574814  :decimalLongitude -47.423994}
    {:decimalLatitude -14.133333  :decimalLongitude -47.516667}
    {:decimalLatitude -19.653653  :decimalLongitude -46.914828}
    {:decimalLatitude -13.683333  :decimalLongitude -41.3}
    {:decimalLatitude -16.85  :decimalLongitude -43}
    {:decimalLatitude -20.116667  :decimalLongitude -44.183333}
    {:decimalLatitude -12.716667  :decimalLongitude -47.066667}
    {:decimalLatitude -17.733333  :decimalLongitude -48.616667}
    {:decimalLatitude -13.166667  :decimalLongitude -41.4}
    {:decimalLatitude -24.783333  :decimalLongitude -50}
    {:decimalLatitude -20.066667  :decimalLongitude -43.433333}
    {:decimalLatitude -20.057198  :decimalLongitude -43.373434}
    {:decimalLatitude -14.15  :decimalLongitude -48.066667}
    {:decimalLatitude -14.15  :decimalLongitude -48.066667}
    {:decimalLatitude -18.066667  :decimalLongitude -43.466667}
    {:decimalLatitude -18.483333  :decimalLongitude -54.75}
    {:decimalLatitude -11.616667  :decimalLongitude -46.816667}
    {:decimalLatitude -13.416667  :decimalLongitude -42.133333}
    {:decimalLatitude -20.533333  :decimalLongitude -47.416667}
    {:decimalLatitude -12.608004  :decimalLongitude -41.879892}
    {:decimalLatitude -12.608004  :decimalLongitude -41.879892}
    {:decimalLatitude -12.608004  :decimalLongitude -41.879892}
    {:decimalLatitude -16.55  :decimalLongitude -42.883333}
    {:decimalLatitude -13.4   :decimalLongitude -41.266667}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -11.55  :decimalLongitude -41.15}
    {:decimalLatitude -12.966667  :decimalLongitude -41.333333}
    {:decimalLatitude -12.966667  :decimalLongitude -41.333333}
    {:decimalLatitude -13 :decimalLongitude -41.366667}
    {:decimalLatitude -20.5   :decimalLongitude -43.683333}
    {:decimalLatitude -20.466667  :decimalLongitude -43.716667}
    {:decimalLatitude -12.514997  :decimalLongitude -41.562424}
    {:decimalLatitude -16.023349  :decimalLongitude -41.270469}
    {:decimalLatitude -13.15  :decimalLongitude -41.766667}
    {:decimalLatitude -16.4   :decimalLongitude -51.8}
    {:decimalLatitude -15.85  :decimalLongitude -48.95}
    {:decimalLatitude -25.089962  :decimalLongitude -50.157327}
    {:decimalLatitude -10.7   :decimalLongitude -48.416667}
    {:decimalLatitude -13.583333  :decimalLongitude -41.8}
    {:decimalLatitude -13.583333  :decimalLongitude -41.8}
    {:decimalLatitude -11.077796  :decimalLongitude -41.745559}
    {:decimalLatitude -20.25  :decimalLongitude -46.35}
    {:decimalLatitude -21.466667  :decimalLongitude -47.533333}
    {:decimalLatitude -12.416667  :decimalLongitude -41.766667}
    {:decimalLatitude -22.4   :decimalLongitude -42.95}
    {:decimalLatitude -10.366667  :decimalLongitude -41.166667}
    {:decimalLatitude -13.307288  :decimalLongitude -41.807119}
    {:decimalLatitude -11.566667  :decimalLongitude -47.166667}
    {:decimalLatitude -14.133333  :decimalLongitude -47.516667}
    {:decimalLatitude -14.133333  :decimalLongitude -47.516667}
    {:decimalLatitude -14.133333  :decimalLongitude -47.516667}
    {:decimalLatitude -14.133333  :decimalLongitude -47.516667}
    {:decimalLatitude -13.581801  :decimalLongitude -41.131719}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -14.203681  :decimalLongitude -47.523045}
    {:decimalLatitude -15.883333  :decimalLongitude -52.25}
    {:decimalLatitude -19.088333  :decimalLongitude -48.1425}
    {:decimalLatitude -15.78  :decimalLongitude -47.93}
    {:decimalLatitude -15.78  :decimalLongitude -47.93}
    {:decimalLatitude -14.13  :decimalLongitude -47.51}
    {:decimalLatitude -12.433333  :decimalLongitude -41.45}
    {:decimalLatitude -13 :decimalLongitude -42}
    {:decimalLatitude -12.433333  :decimalLongitude -41.45}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -15.78  :decimalLongitude -47.93}
    {:decimalLatitude -14.5833333 :decimalLongitude -48.55}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -13.33  :decimalLongitude -41.85}
    {:decimalLatitude -13.3166667 :decimalLongitude -41.85}
    {:decimalLatitude -13.33  :decimalLongitude -41.85}
    {:decimalLatitude -12.433333  :decimalLongitude -41.45}
    {:decimalLatitude -12.5   :decimalLongitude -41.333333}
    {:decimalLatitude -12.46  :decimalLongitude -41.42}
    {:decimalLatitude -12.6   :decimalLongitude -41.483333}
    {:decimalLatitude -12.6   :decimalLongitude -41.483333}
    {:decimalLatitude -11.633333  :decimalLongitude -41.033333}
    {:decimalLatitude -11.48  :decimalLongitude -41.08}
    {:decimalLatitude -12.56  :decimalLongitude -41.39}
    {:decimalLatitude -11.633333  :decimalLongitude -41.033333}
    {:decimalLatitude -11.550278  :decimalLongitude -41.156111}
    {:decimalLatitude -11.55  :decimalLongitude -41.16}
    {:decimalLatitude -13.916667  :decimalLongitude -41.377778}
    {:decimalLatitude -13 :decimalLongitude -41.383333}
    {:decimalLatitude -13.25  :decimalLongitude -41.66}
    {:decimalLatitude -12.53  :decimalLongitude -41.56}
    {:decimalLatitude -13.07  :decimalLongitude -41.92}
    {:decimalLatitude -11 :decimalLongitude -41}
    {:decimalLatitude -11.744722  :decimalLongitude -40.730833}
    {:decimalLatitude -11.638611  :decimalLongitude -41.002222}
    {:decimalLatitude -11.333333  :decimalLongitude -40.516667}
    {:decimalLatitude -11.638611  :decimalLongitude -41.002222}
    {:decimalLatitude -11.466667  :decimalLongitude -41.083333}
    {:decimalLatitude -11 :decimalLongitude -41}
    {:decimalLatitude -11 :decimalLongitude -41}
    {:decimalLatitude -11.466667  :decimalLongitude -41.083333}
    {:decimalLatitude -11.666667  :decimalLongitude -41.016667}
    {:decimalLatitude -13.5333333 :decimalLongitude -41.8833333}
    {:decimalLatitude -13.524167  :decimalLongitude -41.946944}
    {:decimalLatitude -13.55  :decimalLongitude -41.95}
    {:decimalLatitude -13.55  :decimalLongitude -41.95}
    {:decimalLatitude -12.42  :decimalLongitude -41.77}
    {:decimalLatitude -19.100833  :decimalLongitude -48.126667}
    {:decimalLatitude -19.088333  :decimalLongitude -48.1425}
    {:decimalLatitude -19.100833  :decimalLongitude -48.126667}
    {:decimalLatitude -19.088333  :decimalLongitude -48.1425}
    {:decimalLatitude -19.088333  :decimalLongitude -48.1425}
    {:decimalLatitude -19.088333  :decimalLongitude -48.1425}
    {:decimalLatitude -19.100833  :decimalLongitude -48.126667}
    {:decimalLatitude -15.9441666 :decimalLongitude -47.8852777}
    {:decimalLatitude -13.27  :decimalLongitude -41.9}
    {:decimalLatitude -13.27  :decimalLongitude -41.9}
    {:decimalLatitude -13.55  :decimalLongitude -41.95}
    {:decimalLatitude -13.25  :decimalLongitude -41.9}
    {:decimalLatitude -13.016667  :decimalLongitude -41.4}
    {:decimalLatitude -12.466667  :decimalLongitude -41.433333}
    {:decimalLatitude -13.55  :decimalLongitude -41.95}
    {:decimalLatitude -13.28  :decimalLongitude -41.87}
    {:decimalLatitude -13.55  :decimalLongitude -41.95}
    {:decimalLatitude -12.47  :decimalLongitude -41.43}
    {:decimalLatitude -13.02  :decimalLongitude -41.4}
    {:decimalLatitude -13.53  :decimalLongitude -41.9}
    {:decimalLatitude -12.6   :decimalLongitude -41.48}
    {:decimalLatitude -11.5780556 :decimalLongitude -41.1077778}
    {:decimalLatitude -11.63  :decimalLongitude -41.03}
    {:decimalLatitude -13.2666667 :decimalLongitude -41.9}
    {:decimalLatitude -11.55  :decimalLongitude -41.15}
    {:decimalLatitude -17.583333  :decimalLongitude -44.966667}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -11.45  :decimalLongitude -41.1166667}
    {:decimalLatitude -22.25  :decimalLongitude -47.816667}
    {:decimalLatitude -16.643186  :decimalLongitude -55.284771}
    {:decimalLatitude -11.494167  :decimalLongitude -41.331389}
    {:decimalLatitude -12.3   :decimalLongitude -47.233333}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -13.3   :decimalLongitude -41.87}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -13.563186  :decimalLongitude -41.728721}
    {:decimalLatitude -12.456389  :decimalLongitude -41.422222}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.433333  :decimalLongitude -41.45}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -13.286991  :decimalLongitude -41.850368}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -13.27  :decimalLongitude -41.9}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.581802  :decimalLongitude -41.13172}
    {:decimalLatitude -13.337144  :decimalLongitude -41.439611}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -11.452222  :decimalLongitude -40.523333}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -13 :decimalLongitude -42}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -13.581802  :decimalLongitude -41.13172}
    {:decimalLatitude -14.574815  :decimalLongitude -47.423995}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -14.203681  :decimalLongitude -47.523045}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12.5   :decimalLongitude -41.333333}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -13.563186  :decimalLongitude -41.728721}
    {:decimalLatitude -13.3166667 :decimalLongitude -41.8333333}
    {:decimalLatitude -14.471572  :decimalLongitude -48.406595}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.3166667 :decimalLongitude -41.85}
    {:decimalLatitude -13.2833333 :decimalLongitude -41.8666667}
    {:decimalLatitude -13.286991  :decimalLongitude -41.850368}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.6   :decimalLongitude -47.883333}
    {:decimalLatitude -13.040471  :decimalLongitude -41.882805}
    {:decimalLatitude -14.133333  :decimalLongitude -48.066667}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -14.203681  :decimalLongitude -47.523045}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -14.203681  :decimalLongitude -47.523045}
    {:decimalLatitude -14.203681  :decimalLongitude -47.523045}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -13 :decimalLongitude -42}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.783675  :decimalLongitude -47.528884}
    {:decimalLatitude -13 :decimalLongitude -42}
    {:decimalLatitude -13.783675  :decimalLongitude -47.528884}
    {:decimalLatitude -21.467174  :decimalLongitude -47.603045}
    {:decimalLatitude -18.055177  :decimalLongitude -43.65238}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -13.783675  :decimalLongitude -47.528884}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -11.45  :decimalLongitude -41.12}
    {:decimalLatitude -13.563186  :decimalLongitude -41.728721}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -11.675278  :decimalLongitude -40.861111}
    {:decimalLatitude -12 :decimalLongitude -41}
    {:decimalLatitude -11 :decimalLongitude -41}
    {:decimalLatitude -14.203681  :decimalLongitude -47.523045}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -15.583333  :decimalLongitude -56.083333}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -15.480278  :decimalLongitude -56.055833}
    {:decimalLatitude -13.857858  :decimalLongitude -41.40002}
    {:decimalLatitude -12.45  :decimalLongitude -41.466667}
    {:decimalLatitude -13.307288  :decimalLongitude -41.807119}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -11.55  :decimalLongitude -41.16}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -11.45  :decimalLongitude -41.12}
    {:decimalLatitude -11.55  :decimalLongitude -41.16}
    {:decimalLatitude -12.95  :decimalLongitude -41.3333333}
    {:decimalLatitude -13.075278  :decimalLongitude -41.378056}
    {:decimalLatitude -11.470278  :decimalLongitude -41.068889}
    {:decimalLatitude -13.25  :decimalLongitude -41.9}
    {:decimalLatitude -12.95  :decimalLongitude -41.333333}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -18.216667  :decimalLongitude -43.6}
    {:decimalLatitude -12.95  :decimalLongitude -41.33}
    {:decimalLatitude -13.283333  :decimalLongitude -41.833333}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -13.084722  :decimalLongitude -41.377778}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -11 :decimalLongitude -41}
    {:decimalLatitude -11 :decimalLongitude -41}
    {:decimalLatitude -13.47  :decimalLongitude -41.83}
    {:decimalLatitude -11.65  :decimalLongitude -39.08}
    {:decimalLatitude -13.75  :decimalLongitude -42.42}
    {:decimalLatitude -13.2666667 :decimalLongitude -41.8666667}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -13.83  :decimalLongitude -42.35}
    {:decimalLatitude -11.150839  :decimalLongitude -63.548377}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -12.53  :decimalLongitude -41.56}
    {:decimalLatitude -13.307288  :decimalLongitude -41.807119}
    {:decimalLatitude -13.583333  :decimalLongitude -41.8}
    {:decimalLatitude -13.307288  :decimalLongitude -41.807119}
    {:decimalLatitude -13.15  :decimalLongitude -41.766667}
    {:decimalLatitude -13.28  :decimalLongitude -41.87}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -13.077868  :decimalLongitude -41.452698}
    {:decimalLatitude -19.155657  :decimalLongitude -48.38911}
    {:decimalLatitude -16.35  :decimalLongitude -50.7}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.3   :decimalLongitude -41.87}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -13 :decimalLongitude -42}
    {:decimalLatitude -13.266666  :decimalLongitude -41.9}
    {:decimalLatitude -13.583333  :decimalLongitude -41.8}
    {:decimalLatitude -12.433333  :decimalLongitude -41.45}
    {:decimalLatitude -18.07  :decimalLongitude -43.47}
    {:decimalLatitude -13.680833  :decimalLongitude -41.311944}
    {:decimalLatitude -13.581802  :decimalLongitude -41.13172}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -11.525812  :decimalLongitude -41.182406}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -12.35681   :decimalLongitude -41.312036}
    {:decimalLatitude -13.040471  :decimalLongitude -41.882805}
    {:decimalLatitude -13.33  :decimalLongitude -41.85}
    {:decimalLatitude -12.452999  :decimalLongitude -41.462248}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -13.040471  :decimalLongitude -41.882805}
    {:decimalLatitude -13.276347  :decimalLongitude -41.748908}
    {:decimalLatitude -19.019656  :decimalLongitude -48.360579}
    {:decimalLatitude -12.941367  :decimalLongitude -41.281258}
    {:decimalLatitude -14.471572  :decimalLongitude -48.406595}
    {:decimalLatitude -14.133333  :decimalLongitude -47.533333}
    {:decimalLatitude -13 :decimalLongitude -42}
    {:decimalLatitude -21.116667  :decimalLongitude -56.466667}
    {:decimalLatitude -11.55  :decimalLongitude -41.16}
    {:decimalLatitude -11.55  :decimalLongitude -41.16}
    {:decimalLatitude -11.48  :decimalLongitude -41.08}
    {:decimalLatitude -13.527138  :decimalLongitude -41.972012}
    {:decimalLatitude -13.307288  :decimalLongitude -41.807119}
    {:decimalLatitude -13.719444  :decimalLongitude -41.378056}
    {:decimalLatitude -13.084722  :decimalLongitude -41.377778}
    {:decimalLatitude -15.655526  :decimalLongitude -55.632316}
    {:decimalLatitude -13.48  :decimalLongitude -41.82}
    {:decimalLatitude -12.6   :decimalLongitude -41.483333}
    {:decimalLatitude -11.63  :decimalLongitude -41.03}
    {:decimalLatitude -10.3666667 :decimalLongitude -41.3333333}
    {:decimalLatitude -11.63  :decimalLongitude -41.03}
    {:decimalLatitude -12.46  :decimalLongitude -41.47}
    {:decimalLatitude -13.28  :decimalLongitude -41.87}
    {:decimalLatitude -14.13  :decimalLongitude -47.51}
    {:decimalLatitude -14.133333  :decimalLongitude -47.516667}
      ])
(time (println (:area (aoo br-occs))))

(def vicia300
[{:decimalLatitude 35.21442,:decimalLongitude 24.56912},
{:decimalLatitude 47.26343,:decimalLongitude -2.34057},
{:decimalLatitude 49.04083,:decimalLongitude 1.69055},
{:decimalLatitude 50.58078,:decimalLongitude 9.04385},
{:decimalLatitude 59.31551,:decimalLongitude 10.78931},
{:decimalLatitude 49.98434,:decimalLongitude 9.73603},
{:decimalLatitude 52.27304,:decimalLongitude 10.58116},
{:decimalLatitude 52.26582,:decimalLongitude 10.57777},
{:decimalLatitude 59.31625,:decimalLongitude 10.78942},
{:decimalLatitude 59.31647,:decimalLongitude 10.78961},
{:decimalLatitude 59.31551,:decimalLongitude 10.78931},
{:decimalLatitude 59.31602,:decimalLongitude 10.78942},
{:decimalLatitude 59.31585,:decimalLongitude 10.78935},
{:decimalLatitude 59.31635,:decimalLongitude 10.78944},
{:decimalLatitude 59.3164,:decimalLongitude 10.78957},
{:decimalLatitude 57.9831,:decimalLongitude 15.658},
{:decimalLatitude -43.07355,:decimalLongitude 172.68496},
{:decimalLatitude 56.0421,:decimalLongitude 12.847},
{:decimalLatitude 49.98434,:decimalLongitude 9.73603},
{:decimalLatitude 58.4437,:decimalLongitude 15.6379},
{:decimalLatitude 52.67188,:decimalLongitude -2.75801},
{:decimalLatitude 36.7093,:decimalLongitude -4.62119},
{:decimalLatitude 43.38465,:decimalLongitude 3.00892},
{:decimalLatitude 58.6877,:decimalLongitude 16.5877},
{:decimalLatitude 39.72673,:decimalLongitude 3.2347},
{:decimalLatitude 53.48294,:decimalLongitude -3.03991},
{:decimalLatitude 56.653,:decimalLongitude 16.4916},
{:decimalLatitude 49.13641,:decimalLongitude 8.91403},
{:decimalLatitude 60.65379,:decimalLongitude 23.89923},
{:decimalLatitude 60.65379,:decimalLongitude 23.89923},
{:decimalLatitude 60.65379,:decimalLongitude 23.89923},
{:decimalLatitude 48.12389,:decimalLongitude 16.29028},
{:decimalLatitude 52.21639,:decimalLongitude 6.72102},
{:decimalLatitude 52.21193,:decimalLongitude 6.71796},
{:decimalLatitude 59.4946,:decimalLongitude 17.8336},
{:decimalLatitude 57.7927,:decimalLongitude 12.0986},
{:decimalLatitude 58.5604,:decimalLongitude 15.9665},
{:decimalLatitude 59.356,:decimalLongitude 17.0531},
{:decimalLatitude -34.35233145283094,:decimalLongitude 138.7738712532468},
{:decimalLatitude 50.60966,:decimalLongitude 6.62922},
{:decimalLatitude -34.534282907590814,:decimalLongitude 138.714703925306},
{:decimalLatitude 48.84145,:decimalLongitude 9.08535},
{:decimalLatitude 58.563,:decimalLongitude 13.3984},
{:decimalLatitude 56.4277,:decimalLongitude 16.5461},
{:decimalLatitude 60.65385,:decimalLongitude 23.89929},
{:decimalLatitude 60.65385,:decimalLongitude 23.89929},
{:decimalLatitude 60.65385,:decimalLongitude 23.89929},
{:decimalLatitude 58.7337,:decimalLongitude 5.66724},
{:decimalLatitude 59.03566,:decimalLongitude 10.05416},
{:decimalLatitude 58.4431,:decimalLongitude 15.6256},
{:decimalLatitude 59.18202,:decimalLongitude 10.96549},
{:decimalLatitude 55.8645,:decimalLongitude 12.8539},
{:decimalLatitude 59.18202,:decimalLongitude 10.96549},
{:decimalLatitude 59.25948,:decimalLongitude 11.11819},
{:decimalLatitude 59.0478,:decimalLongitude 15.9109},
{:decimalLatitude 58.9906,:decimalLongitude 16.5362},
{:decimalLatitude 58.9531,:decimalLongitude 16.5705},
{:decimalLatitude 52.41205,:decimalLongitude 1.45581},
{:decimalLatitude 52.74352,:decimalLongitude -2.73333},
{:decimalLatitude 52.49282,:decimalLongitude 1.7569},
{:decimalLatitude 52.49282,:decimalLongitude 1.7569},
{:decimalLatitude 52.2091,:decimalLongitude -6.38204},
{:decimalLatitude 52.2282,:decimalLongitude 1.588},
{:decimalLatitude 48.5307,:decimalLongitude 5.14464},
{:decimalLatitude 52.4104,:decimalLongitude -2.86674},
{:decimalLatitude 58.9557,:decimalLongitude 17.5828},
{:decimalLatitude 57.9771,:decimalLongitude 14.9142},
{:decimalLatitude 56.3788,:decimalLongitude 13.9071},
{:decimalLatitude 60.35411,:decimalLongitude 21.91226},
{:decimalLatitude 52.34861,:decimalLongitude -6.43051},
{:decimalLatitude 52.27824,:decimalLongitude -6.55022},
{:decimalLatitude 52.6181,:decimalLongitude -6.42089},
{:decimalLatitude 52.47824,:decimalLongitude -6.74971},
{:decimalLatitude 52.22361,:decimalLongitude -6.49346},
{:decimalLatitude 52.24195,:decimalLongitude -6.52212},
{:decimalLatitude 52.47382,:decimalLongitude -6.7572},
{:decimalLatitude 52.74532,:decimalLongitude -2.33334},
{:decimalLatitude 48.39912,:decimalLongitude 9.97868},
{:decimalLatitude 63.79181,:decimalLongitude 23.1506},
{:decimalLatitude 56.6965,:decimalLongitude 16.1773},
{:decimalLatitude 56.1848,:decimalLongitude 14.7213},
{:decimalLatitude 56.1846,:decimalLongitude 14.7206},
{:decimalLatitude 52.06496,:decimalLongitude 0.99109},
{:decimalLatitude 51.31544,:decimalLongitude -2.39463},
{:decimalLatitude 52.06496,:decimalLongitude 0.99109},
{:decimalLatitude 52.66213,:decimalLongitude -2.8059},
{:decimalLatitude 44.27247,:decimalLongitude 1.03356},
{:decimalLatitude 48.1653,:decimalLongitude 4.38035},
{:decimalLatitude 48.3603,:decimalLongitude 5.11512},
{:decimalLatitude 53.98018,:decimalLongitude -0.3987},
{:decimalLatitude 56.6772,:decimalLongitude 16.5072},
{:decimalLatitude 56.6978,:decimalLongitude 12.8757},
{:decimalLatitude -29.7754900259543,:decimalLongitude 150.01861000000002},
{:decimalLatitude -29.774630025952888,:decimalLongitude 150.01858},
{:decimalLatitude -29.774630025952888,:decimalLongitude 150.01858},
{:decimalLatitude -29.7754900259543,:decimalLongitude 150.01861000000002},
{:decimalLatitude 52.48322,:decimalLongitude -6.75325},
{:decimalLatitude 47.899,:decimalLongitude 2.76884},
{:decimalLatitude 51.72181,:decimalLongitude -4.24428},
{:decimalLatitude 51.72181,:decimalLongitude -4.24428},
{:decimalLatitude 52.61371,:decimalLongitude -6.42843},
{:decimalLatitude 52.13885,:decimalLongitude -7.935},
{:decimalLatitude 52.13885,:decimalLongitude -7.935},
{:decimalLatitude 52.24653,:decimalLongitude -6.52929},
{:decimalLatitude 51.49396,:decimalLongitude -2.68429},
{:decimalLatitude 52.49282,:decimalLongitude 1.7569},
{:decimalLatitude 52.3532,:decimalLongitude -6.43769},
{:decimalLatitude 52.27328,:decimalLongitude -6.54819},
{:decimalLatitude 52.64218,:decimalLongitude -1.77829},
{:decimalLatitude 52.49282,:decimalLongitude 1.7569},
{:decimalLatitude 51.31539,:decimalLongitude -2.40898},
{:decimalLatitude 53.71579,:decimalLongitude -0.86342},
{:decimalLatitude 52.46237,:decimalLongitude -1.7792},
{:decimalLatitude 48.7463,:decimalLongitude 4.16409},
{:decimalLatitude 52.40767,:decimalLongitude 1.60256},
{:decimalLatitude 47.8902,:decimalLongitude 5.55086},
{:decimalLatitude 53.35875,:decimalLongitude -1.1735},
{:decimalLatitude 52.40767,:decimalLongitude 1.60256},
{:decimalLatitude 48.83026,:decimalLongitude 2.36596},
{:decimalLatitude 51.86211,:decimalLongitude 5.85902},
{:decimalLatitude 52.46237,:decimalLongitude -1.7792},
{:decimalLatitude 43.76224,:decimalLongitude 5.85906},
{:decimalLatitude 52.55228,:decimalLongitude -1.77875},
{:decimalLatitude 36.83376,:decimalLongitude -2.63996},
{:decimalLatitude 49.22901,:decimalLongitude 8.45659},
{:decimalLatitude 52.22819,:decimalLongitude -6.50063},
{:decimalLatitude 51.32453,:decimalLongitude -2.366},
{:decimalLatitude 63.7448,:decimalLongitude 20.2797},
{:decimalLatitude 59.3233,:decimalLongitude 14.4396},
{:decimalLatitude 64.66614,:decimalLongitude 24.55683},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 63.6828,:decimalLongitude 20.3363},
{:decimalLatitude 49.1406,:decimalLongitude 4.89594},
{:decimalLatitude 57.1852,:decimalLongitude 12.4135},
{:decimalLatitude 52.12966,:decimalLongitude -7.77439},
{:decimalLatitude 52.40767,:decimalLongitude 1.60256},
{:decimalLatitude 52.27023,:decimalLongitude -7.09983},
{:decimalLatitude 52.40767,:decimalLongitude 1.60256},
{:decimalLatitude 52.12966,:decimalLongitude -7.77439},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 52.3265,:decimalLongitude 1.30232},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.4974,:decimalLongitude 1.6099},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 52.23255,:decimalLongitude 1.44184},
{:decimalLatitude 52.3265,:decimalLongitude 1.30232},
{:decimalLatitude 52.4974,:decimalLongitude 1.6099},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.23255,:decimalLongitude 1.44184},
{:decimalLatitude 52.40767,:decimalLongitude 1.60256},
{:decimalLatitude 52.40767,:decimalLongitude 1.60256},
{:decimalLatitude 36.96263,:decimalLongitude -2.14989},
{:decimalLatitude 52.15095,:decimalLongitude 1.14307},
{:decimalLatitude 52.15095,:decimalLongitude 1.14307},
{:decimalLatitude 48.2889,:decimalLongitude 3.24547},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.15837,:decimalLongitude 0.85111},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.15837,:decimalLongitude 0.85111},
{:decimalLatitude 52.03097,:decimalLongitude -7.89145},
{:decimalLatitude 59.19394,:decimalLongitude 11.14889},
{:decimalLatitude 52.02603,:decimalLongitude -7.89073},
{:decimalLatitude 52.03097,:decimalLongitude -7.89145},
{:decimalLatitude 52.02603,:decimalLongitude -7.89073},
{:decimalLatitude 45.24012,:decimalLongitude 6.49505},
{:decimalLatitude 42.69194,:decimalLongitude -84.49},
{:decimalLatitude 57.0962,:decimalLongitude 12.3845},
{:decimalLatitude 57.0962,:decimalLongitude 12.3845},
{:decimalLatitude 57.6441,:decimalLongitude 12.0592},
{:decimalLatitude 50.23,:decimalLongitude 20.51},
{:decimalLatitude 57.096,:decimalLongitude 12.3852},
{:decimalLatitude 50.22,:decimalLongitude 19.86},
{:decimalLatitude 57.0962,:decimalLongitude 12.3845},
{:decimalLatitude 57.0927,:decimalLongitude 12.3947},
{:decimalLatitude 57.0961,:decimalLongitude 12.3844},
{:decimalLatitude 50.59,:decimalLongitude 21.18},
{:decimalLatitude 50.1,:decimalLongitude 20.87},
{:decimalLatitude 53.35875,:decimalLongitude -1.1735},
{:decimalLatitude 46.9776,:decimalLongitude 4.82942},
{:decimalLatitude 52.13846,:decimalLongitude 1.58077},
{:decimalLatitude 52.13846,:decimalLongitude 1.58077},
{:decimalLatitude 50.72,:decimalLongitude 21.46},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 52.89255,:decimalLongitude -4.15562},
{:decimalLatitude 52.18465,:decimalLongitude -0.61029},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 52.41205,:decimalLongitude 1.45581},
{:decimalLatitude 51.8233,:decimalLongitude -0.47632},
{:decimalLatitude 52.2282,:decimalLongitude 1.588},
{:decimalLatitude 52.18465,:decimalLongitude -0.61029},
{:decimalLatitude 52.2282,:decimalLongitude 1.588},
{:decimalLatitude 52.89255,:decimalLongitude -4.15562},
{:decimalLatitude 52.23255,:decimalLongitude 1.44184},
{:decimalLatitude 52.3265,:decimalLongitude 1.30232},
{:decimalLatitude 52.41205,:decimalLongitude 1.45581},
{:decimalLatitude 52.23255,:decimalLongitude 1.44184},
{:decimalLatitude 52.3265,:decimalLongitude 1.30232},
{:decimalLatitude 51.82133,:decimalLongitude -0.33127},
{:decimalLatitude 43.72945,:decimalLongitude 7.356},
{:decimalLatitude 43.70785,:decimalLongitude 7.33416},
{:decimalLatitude 43.09639,:decimalLongitude 6.07166},
{:decimalLatitude 43.15856,:decimalLongitude 6.36098},
{:decimalLatitude 39.76721999907218,:decimalLongitude -121.77444000000001},
{:decimalLatitude 52.41205,:decimalLongitude 1.45581},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.19281,:decimalLongitude -2.07315},
{:decimalLatitude 51.92037,:decimalLongitude -1.20018},
{:decimalLatitude 51.07711,:decimalLongitude 0.92689},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.99527,:decimalLongitude -0.73342},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 43.37196,:decimalLongitude 6.21637},
{:decimalLatitude 43.37196,:decimalLongitude 6.21637},
{:decimalLatitude 52.23673,:decimalLongitude 1.29565},
{:decimalLatitude 51.92253,:decimalLongitude -1.63643},
{:decimalLatitude 52.42412,:decimalLongitude 1.01536},
{:decimalLatitude 52.19281,:decimalLongitude -2.07315},
{:decimalLatitude 51.92037,:decimalLongitude -1.20018},
{:decimalLatitude 52.19281,:decimalLongitude -2.07315},
{:decimalLatitude 51.07711,:decimalLongitude 0.92689},
{:decimalLatitude 52.99919,:decimalLongitude -1.18038},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.24073,:decimalLongitude 1.14942},
{:decimalLatitude 38.13796,:decimalLongitude -5.03323},
{:decimalLatitude 52.15475,:decimalLongitude 0.99711},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.72562,:decimalLongitude -0.74125},
{:decimalLatitude 52.23255,:decimalLongitude 1.44184},
{:decimalLatitude 52.23255,:decimalLongitude 1.44184},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.2282,:decimalLongitude 1.588},
{:decimalLatitude 52.6805,:decimalLongitude -2.74707},
{:decimalLatitude 52.25163,:decimalLongitude 0.71054},
{:decimalLatitude 52.24073,:decimalLongitude 1.14942},
{:decimalLatitude 52.15475,:decimalLongitude 0.99711},
{:decimalLatitude 52.24073,:decimalLongitude 1.14942},
{:decimalLatitude 52.60064,:decimalLongitude -2.55374},
{:decimalLatitude 52.15475,:decimalLongitude 0.99711},
{:decimalLatitude 52.32231,:decimalLongitude 1.44881},
{:decimalLatitude 52.2282,:decimalLongitude 1.588},
{:decimalLatitude 52.24073,:decimalLongitude 1.14942},
{:decimalLatitude 47.2897,:decimalLongitude 4.40417},
{:decimalLatitude 52.25163,:decimalLongitude 0.71054},
{:decimalLatitude 47.7983,:decimalLongitude -0.0222},
{:decimalLatitude 51.66542,:decimalLongitude 0.58138},
{:decimalLatitude 51.38088,:decimalLongitude -1.20962},
{:decimalLatitude 53.53738,:decimalLongitude -2.98088},
{:decimalLatitude 51.92306,:decimalLongitude -2.07272},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 53.2649,:decimalLongitude -0.72546},
{:decimalLatitude 51.92037,:decimalLongitude -1.20018},
{:decimalLatitude 52.00487,:decimalLongitude -0.61586},
{:decimalLatitude 52.16508,:decimalLongitude 0.55902},
{:decimalLatitude 52.1428,:decimalLongitude 1.43491},
{:decimalLatitude 52.1428,:decimalLongitude 1.43491},
{:decimalLatitude 52.19281,:decimalLongitude -2.07315},
{:decimalLatitude 51.8375,:decimalLongitude 11.47917},
{:decimalLatitude 52.27272,:decimalLongitude -0.46095},
{:decimalLatitude 52.41205,:decimalLongitude 1.45581},
{:decimalLatitude 53.53452,:decimalLongitude -0.71738},
{:decimalLatitude 53.25924,:decimalLongitude -0.27582},
{:decimalLatitude 52.0888,:decimalLongitude -0.17532},
{:decimalLatitude 52.09476,:decimalLongitude -0.61308},
{:decimalLatitude 52.41205,:decimalLongitude 1.45581},
{:decimalLatitude 50.26516,:decimalLongitude -5.01715},
{:decimalLatitude 52.99919,:decimalLongitude -1.18038},
{:decimalLatitude 51.81528,:decimalLongitude 12.39861},
{:decimalLatitude 51.92306,:decimalLongitude -2.07272},
{:decimalLatitude 56.69569,:decimalLongitude -6.49259},
{:decimalLatitude 47.5563,:decimalLongitude 4.73673},
{:decimalLatitude 52.73209,:decimalLongitude -1.77784},
{:decimalLatitude 52.51757,:decimalLongitude 0.87432},
{:decimalLatitude 48.83512,:decimalLongitude 2.41694},
{:decimalLatitude 48.1651,:decimalLongitude 3.59359},
{:decimalLatitude 51.33494,:decimalLongitude 1.37412}]
)
(println "Vicia faba with 300 points")
(time (aoo vicia300))

(shutdown-agents)
(println "Done")
