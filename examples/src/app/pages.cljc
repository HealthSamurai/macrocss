(ns app.pages
  (:require [clojure.string :as str]))

(defonce pages (atom {}))

(defn reg-page [key comp w]
  (swap! pages assoc key {:cmp comp :title (-> key
                                               name
                                               str/capitalize) :w w}))
