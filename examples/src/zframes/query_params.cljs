(ns zframes.query-string
  (:refer-clojure :exclude [get set!])
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))

(defn parse-querystring [s]
  (-> (str/replace s #"^\?" "")
      (str/split #"&")
      (->>
       (reduce (fn [acc kv]
                 (let [[k v] (str/split kv #"=" 2)]
                   (assoc acc (keyword k) (js/decodeURIComponent v))))
               {}))))

(defn get-query-string []
  (let [raw (.. js/window -location -search)]
    (parse-querystring raw)))

(defn gen-query-string [params]
  (->> params
       (map (fn [[k v]] (str (name k) "=" (js/encodeURIComponent v))))
       (str/join "&")
       (str "?")))

(rf/reg-cofx
 ::query-string
 (fn [coeffects key]
   (assoc-in coeffects [:query-string] (get-query-string))))
