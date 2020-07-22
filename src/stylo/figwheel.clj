(ns stylo.figwheel
  (:require
    [cljs.repl]
    [stylo.core]
    [figwheel.main.api :as figwheel]
    [cheshire.core :as json]))


(defn make-reload-hook [build-id]
  (fn [& _]
    (when-let [env (figwheel/repl-env build-id)]
      (cljs.repl/evaluate env "<stylo>" 1
                          (str "document.getElementById('stylo').innerHTML = "
                               (json/generate-string (stylo.core/get-styles)))))))

(comment
  ((make-reload-hook "dev")))
