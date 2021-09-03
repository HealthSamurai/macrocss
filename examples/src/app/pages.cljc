(ns app.pages
  (:require [clojure.string :as str]))

(defonce pages (atom {}))

(defn reg-page
  ([k w]
   (reg-page k nil nil w))
  ([k c w]
   (reg-page k c nil w))
  ([k component sublinks weight]
  (swap! pages assoc k {:cmp component :title (-> k
                                                    name
                                                    (str/split #"-")
                                               (->>
                                                (mapv str/capitalize)
                                                (str/join " ")))
                        :w weight
                        :sub sublinks})))
