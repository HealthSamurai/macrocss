(ns routing.core
  (:require [app.pages :as p]))

(defn route-path [page]
  (->> page
       name
       (str "/")))

(defn create-routes [ks]
  (reduce (fn [acc k] (assoc acc k (route-path k))) {} ks))

(def routes
    (create-routes p/all-pages))

(defn redirect [url]
  (set! (.-hash (.-location js/window)) url))

(defn on-hash-change [dispatch-fn]
  (aset js/window "onhashchange" dispatch-fn))
