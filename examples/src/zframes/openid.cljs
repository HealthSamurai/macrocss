(ns zframes.openid
  (:require
   [re-frame.core :as rf]
   [clojure.string :as str]
   [goog.crypt.base64 :refer [encodeString decodeString]]))

(defn parse-id-token [jwt]
  (let [[header payload sig] (str/split jwt ".")]
    (js->clj (.parse js/JSON (js/atob payload))
             :keywordize-keys true)))

(defn check-token []
  (let [hash (when-let [h (.. js/window -location -hash)] (str/replace h  #"^#" ""))]
    (when (or (str/index-of hash "id_token")
              (str/index-of hash "access_token"))
      (let [tokens (->> (str/split hash "&")
                        (map (fn [x] (str/split x "=")))
                        (reduce (fn [acc [tname tkn]] (assoc acc (keyword tname) tkn)) {}))
            id-token (get tokens :id_token)
            access-token (get tokens :access_token)]
        (when (or id-token access-token)
          (set! (.. js/window -location -hash) "")
          {:token (or access-token id-token)
           :id-token id-token
           :user (when id-token
                   (parse-id-token id-token))})))))


(rf/reg-cofx
 ::jwt
 (fn [coeffects]
   (assoc-in coeffects [:jwt]  (check-token))))
