(ns stylo.shadow
  (:require
    [stylo.core]
    [clojure.java.io :as io]))


(defn reload
  {:shadow.build/stage :flush}
  [build-state output-file]
  (future
    (let [f (io/file output-file)]
      (io/make-parents f)
      (spit f (stylo.core/compile-styles @stylo.core/styles))))
  build-state)
