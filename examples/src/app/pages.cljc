(ns app.pages
  (:require [clojure.string :as str]))

(defonce pages (atom {}))

(defn reg-page
  [k component weight]
  (swap! pages assoc k {:cmp component :title (-> k
                                                    name
                                                    (str/split #"-")
                                               (->>
                                                (mapv str/capitalize)
                                                (str/join " ")))
                          :w weight}))
