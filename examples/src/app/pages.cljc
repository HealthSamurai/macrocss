(ns app.pages
  (:require [clojure.string :as str]))

(defonce pages (atom {}))

(defn create-title [k]
  (-> k
      name
      (str/split #"-")
      (->>
       (mapv str/capitalize)
       (str/join " ")
       ((fn [x] (if (>= 3 (count x))
                 (str/upper-case x)
                 x))))))

(defn reg-page
  ([k w]
   (reg-page k nil nil w))
  ([k c w]
   (reg-page k c nil w))
  ([k component sublinks weight]
   (swap! pages assoc k {:cmp component
                         :title (create-title k)
                         :w weight
                         :sub sublinks})))
