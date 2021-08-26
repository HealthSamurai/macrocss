(ns zframes.window-location
  (:refer-clojure :exclude [get set!])
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))

#?(:cljs 
   (defn url-decode [x]
     (js/decodeURIComponent x)))

#?(:clj 
   (defn url-decode [x] x))

#?(:cljs
   (defn url-encode [x]
     (js/encodeURIComponent x)))

#?(:clj 
   (defn url-encode [x] x))


(defn parse-querystring [s]
  (-> (str/replace s #"^\?" "")
      (str/split #"&")
      (->>
       (reduce (fn [acc kv]
                 (let [[k v] (str/split kv #"=" 2)]
                   (assoc acc (keyword k)
                          (cond
                            (str/ends-with? k "*") (into #{} (str/split v #","))
                            :else (url-decode v)))))
               {}))))

(defn gen-query-string [params]
  (->> params
       (map (fn [[k v]]
              (cond
                (set? v) (str (name k) "=" (str/join "," v))
                :else (str (name k) "=" (url-encode v)))))
       (str/join "&")
       (str "?")))

#?(:cljs
   (defn get-location []
     (let [loc    (.. js/window -location)
           href   (.. loc -href)
           hash   (str/replace (or (.. loc -hash) "") #"^#" "")
           hash-params (try
                         (parse-querystring hash)
                         (catch js/Error e
                           (js/console.log e)))
           search (.. loc -search)]
       {:href href
        :query-string (parse-querystring search)
        :url (first (str/split href #"#"))
        :hash hash
        :hash-params hash-params
        :host  (.. loc -host)
        :origin (.. loc -origin)
        :protocol (.. loc -protocol)
        :hostname (.. loc -hostname)
        :search search})))



#?(:clj (def location* (atom {})))

#?(:clj
   (defn get-location []
     (let [loc    @location*
           href   (:href loc)
           search (:search loc)]
       {:href href
        :query-string (parse-querystring search)
        :url (first (str/split href #"#"))
        :hash (str/replace (or (:hash loc) "") #"^#" "")
        :host  (:host loc)
        :origin (:origin loc)
        :protocol (:protocol loc)
        :hostname (:hostname loc)
        :search search})))

(defn window-location [coef & opts]
  (assoc coef :location (get-location)))

(rf/reg-cofx :window-location window-location)
