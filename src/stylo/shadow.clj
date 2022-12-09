(ns stylo.shadow
  (:require [stylo.core]
    [clojure.java.io :as io]
    [clojure.xml :as xml]
    [clj-http.client :as client]
    [clojure.string :as str]))

(defn reload
  {:shadow.build/stage :flush}
  [build-state output-file]
  (future
    (let [f (io/file output-file)]
      (io/make-parents f)
      (spit f (stylo.core/compile-styles @stylo.core/styles
                                         @stylo.core/media-styles))))
  build-state)

(defn add-version-env
  {:shadow.build/stage :flush}
  [build-state app-js]
  (let [http-resp (-> "https://clojars.org/com.health-samurai/macrocss"
                      client/get
                      :body)
        version (-> (re-find #"com.health-samurai:macrocss:\d+\.\d+\.\d+" http-resp)
                    (str/split #":")
                    last)

        env-js (format "\n var STYLO_VERSION = \"%s\" ; \n %s" version (slurp app-js))
        f (io/file app-js)]
    (spit f env-js)
    build-state))
