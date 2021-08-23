(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [stylo.core :refer [c c?]]
            [clojure.string :as str]
            [re-frame.core :as rf]
            [app.render :as rn]))

(rf/reg-event-db :initialize
                 (fn [_ _] {:current-component :about, :menu-showable false}))

(def pages {:default [:about :installation :documentation]
            :doc [:accessibility :background :border :color :container :effect :flex :grid
   :interactivity :layout :preflight :sizing :spacing :svg :table :transform
   :transition :typography :variant]})

(defn contains-item? [k item]
  (->> pages
       k
       (some #{item})))

(defn default-menu-item? [item]
  (contains-item? :default item))

(defn documentation-item? [item]
  (contains-item? :doc item))

(defn menu-showable? [db item]
  (cond (= item :documentation) (-> db
                                    :menu-showable
                                    not)
        (default-menu-item? item) false
        (documentation-item? item) true))

(rf/reg-event-fx :clicked-component
                 (fn [{:keys [db]} [_ item]]
                   {:db (-> db
                       (assoc :menu-showable (menu-showable? db item))
                       (assoc :current-component item))}))

(rf/reg-sub :current-component (fn [db _] (:current-component db)))

(rf/reg-sub :menu-showable (fn [db _] (:menu-showable db)))

(rf/reg-sub :content
  :<- [:current-component]
  (fn [c _ ]
    (cond (default-menu-item? c) (rn/render-default c)
          (documentation-item? c) (rn/render-doc c))))

(defn clicked? [current-component k]
  (if (= current-component k)
    (c [:text :blue-600] [:pseudo :hover [:text :blue-300]])
    (c [:pseudo :hover [:text :blue-300]])))

(defn menu-view
  ([current-component k]
   (menu-view current-component k
                            (-> k
                                name
                                str/capitalize)))
  ([current-component k description]
   [:li
    [:a
    {:class (clicked? current-component k)
     :on-click #(rf/dispatch [:clicked-component k])}
     description]]))

(defn create-menu [current-component items]
  (apply conj [:ul]
         (mapv (partial menu-view current-component) items)))

(defn default-menu [current-component]
  (create-menu current-component (:default pages)))

(defn extended-menu [current-component]
  (create-menu current-component (->> pages
                                      vals
                                      (apply concat)
                                      flatten
                                      vec)))

(rf/reg-sub :menu
  :<- [:current-component]
  :<- [:menu-showable]
  (fn [[c m] _]
    (if-not m
      (default-menu c)
      (extended-menu c))))

(defn side-menu
  []
  [:nav {:class (c :flex :flex-column [:mx 5] [:mt 8])}
   @(rf/subscribe [:menu])])

(defn current-component [] [:div @(rf/subscribe [:content])])

(defn ui
  []
  [:div {:class (c :flex :flex-row)}
   [side-menu]
   [current-component]])

(def compiler
  (r/create-compiler {:function-components true}))

(defn render [] (dom/render [ui] (js/document.getElementById "app") compiler))

(defn ^:dev/after-load clear-cache-and-render!
  []
  (rf/clear-subscription-cache!)
  (render))

(defn run [] (rf/dispatch-sync [:initialize]) (render))
