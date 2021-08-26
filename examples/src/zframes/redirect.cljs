(ns zframes.redirect
  (:require [re-frame.core :as rf]
            [zframes.window-location :as window-location]
            [zframes.routing]
            [clojure.string :as str]))

(defn page-redirect [url]
  (set! (.-href (.-location js/window)) url))

(defn redirect [url]
  (set! (.-hash (.-location js/window)) url))

(rf/reg-fx
 ::redirect
 (fn [opts]
   (redirect (str (:uri opts)
                       (when-let [params (:params opts)]
                         (window-location/gen-query-string params))))))
(rf/reg-event-fx
 ::redirect
 (fn [fx [_ opts]]
   {::redirect opts}))

(rf/reg-fx
 ::page-redirect
 (fn [opts]
   (page-redirect (str (:uri opts)
                       (when-let [params (:params opts)]
                         (->> params
                              (map (fn [[k v]] (str (name k) "=" (js/encodeURIComponent v))))
                              (str/join "&")
                              (str "?")))))))

(rf/reg-fx
 ::set-query-string
 (fn [params]
   (let [loc (.. js/window -location)]
     (.pushState
      js/history
      #js{} (:title params)
      (str (window-location/gen-query-string (dissoc params :title)) (.-hash loc)))
     (zframes.routing/dispatch-context nil))))

(rf/reg-event-fx
 ::merge-params
 (fn [{db :db} [_ params]]
   (let [pth (get db :fragment-path)
         nil-keys (reduce (fn [acc [k v]]
                            (if (nil? v) (conj acc k) acc)) [] params)
         old-params (or (get-in db [:fragment-params :params]) {})]
     {::redirect {:uri pth
                  :params (apply dissoc (merge old-params params)
                                 nil-keys)}})))
(rf/reg-event-fx
 :redirect/set-params
 (fn [{db :db} [_ params]]
   (let [pth (get db :fragment-path)]
     {::redirect {:uri pth
                  :params params}})))
