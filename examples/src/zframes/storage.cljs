(ns zframes.storage
  (:require [re-frame.core :as rf]))

(defn keywordize [x]
  (js->clj x :keywordize-keys true))

(defn remove-item
  [key]
  (.removeItem (.-localStorage js/window) key))

(defn set-item
  [key val]
  (->> val
       clj->js
       (.stringify js/JSON)
       js/encodeURIComponent
       (.setItem (.-localStorage js/window) (name key))))



(defn get-item
  [key]
  (try (->> key
            name
            (.getItem (.-localStorage js/window))
            js/decodeURIComponent
            (.parse js/JSON)
            (keywordize))
       (catch js/Object e (do (remove-item key) nil))))

(rf/reg-cofx
 :storage/get
 (fn [coeffects keys]
   (reduce (fn [coef k]
             (assoc-in coef [:storage k] (get-item k)))
           coeffects keys)))

(rf/reg-fx
 :storage/set
 (fn [items]
   (doseq [[k v] items]
     (set-item k v))))

(rf/reg-fx
 :storage/remove
 (fn [keys]
   (doseq [k keys]
     (remove-item (name k)))))
