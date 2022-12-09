(ns stylo.shadow
  (:require
    [stylo.core]
    [clojure.java.io :as io]
    [clojure.xml :as xml]))


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
  (let [env-js (->> (xml/parse "../pom.xml")
                    :content
                    (filterv (comp #{:version} :tag))
                    (#(get-in % [0 :content 0]))
                    (format "\n var STYLO_VERSION = \"%s\" ; \n" ))
        f (io/file app-js)]
    (io/make-parents f)
    (spit f env-js :append true)
     build-state))
