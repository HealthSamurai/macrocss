(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [re-frame.core :as rf]
            [re-frame.db :as db]
            [stylo.core :refer [c c?]]
            [clojure.string :as str]
            [components.core :as c]
            [components.hiccup :refer [k with-key]]
            [routing.core :as routing]))


;; Dispatching functions


(defn dispatch-routes [_]
  (let [fragment (.. js/window -location -hash)
        component  (->> fragment
                        (drop 2)
                        str/join
                        keyword)]
    (rf/dispatch [:clicked-component component])))

;; Events

(rf/reg-event-fx
 :initialize
 (fn [_ _]
   {:db {:page :about
         :menu-showable false}
    :redirect "/about"
    :history {}}))

(rf/reg-event-fx :clicked-component
                 (fn [{:keys [db]} [_ item]]
                   (let [path (item routing/routes)
                         new-db (-> db
                                    (assoc :menu-showable (c/menu-showable? db item))
                                    (assoc :page item))]
                     {:db new-db
                      :redirect path})))

(rf/reg-event-db :doc-clicked
                 (fn [db _]
                   (update db :menu-showable not)))

;; Subscriptions


(rf/reg-sub :page (fn [db _] (:page db)))

(rf/reg-sub :menu-showable (fn [db _] (:menu-showable db)))

(rf/reg-sub :route (fn [db _] (:route db)))

(rf/reg-sub :content
            :<- [:page]
            (fn [c _]
              (c/render-page c)))

(rf/reg-sub :menu
            :<- [:page]
            :<- [:menu-showable]
            (fn [[c m] _]
              (c/render-menu c m)))

;; Effects

(rf/reg-fx
 :redirect
 (fn [path] (routing/redirect path)))

(rf/reg-fx
 :history
 (fn [_]
   (routing/on-hash-change dispatch-routes)
   (dispatch-routes nil)))

;; View Functions

(defn side-menu
  []
  [:nav (with-key {:class (c :flex :flex-column [:mx 5] [:mt 8])})
   @(rf/subscribe [:menu])])

(defn page []
  [:div (k) @(rf/subscribe [:content])])

(defn ui
  []
  [:div (with-key {:class (c :flex :flex-row)})
   [side-menu]
   [page]])

;; Re-frame machinery

(def compiler
  (r/create-compiler {:function-components true}))

(defn render [] (dom/render [ui] (js/document.getElementById "app") compiler))

(defn ^:dev/after-load clear-cache-and-render!
  []
  (rf/clear-subscription-cache!)
  (render))

(defn run [] (rf/dispatch-sync [:initialize]) (render))

(run)
