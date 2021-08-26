(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [stylo.core :refer [c c?]]
            [clojure.string :as str]
            [re-frame.core :as rf]
            [app.render :as rn]
            [zframes.routing :as routing]
            [zframes.redirect]
            [re-frame.db :as db])


(def pages {:default [:about :installation :documentation]
            :doc [:accessibility :background :border :color :container :effect :flex :grid
   :interactivity :layout :preflight :sizing :spacing :svg :table :transform
   :transition :typography :variant]})

(defn route-path [page]
  (->> page
       name
       (str "/")))

(defn create-routes [ks]
  (reduce (fn [acc k] (assoc acc k (route-path k))) {} ks))

(def routes
    (->> pages
     vals
     (apply concat)
     create-routes))

(defn contains-item? [k item]
  (->> pages
       k
       (some #{item})))

(defn clicked? [page k]
  (if (= page k)
    (c [:text :blue-600] [:pseudo :hover [:text :blue-300]])
    (c [:pseudo :hover [:text :blue-300]])))

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

(defn menu-item
  ([page k]
   (menu-item page k
                            (-> k
                                name
                                str/capitalize)))
  ([page k description]
   [:li
    [:a
    {:class (clicked? page k)
     :on-click #(rf/dispatch [:clicked-component k])}
     description]]))

(defn create-menu [page items]
  (apply conj [:ul]
         (mapv (partial menu-item page) items)))

(defn default-menu [page]
  (let [default-menu-items (:default pages)]
    (create-menu page default-menu-items)))

(defn extended-menu
  [page]
  (let [default-and-doc-items (->> pages
                                   vals
                                   (apply concat)
                                   flatten
                                   vec)]
    (create-menu page default-and-doc-items)))

(defn redirect [url]
  (set! (.-hash (.-location js/window)) url))

;; Events

(rf/reg-event-fx
 :initialize
 (fn [_ _]
   {:db {:page :about
         :menu-showable false}
    :redirect "/about"}))

(rf/reg-event-fx :clicked-component
                 (fn [{:keys [db]} [_ item]]
                   (let [path (item routes)]
                     {:db (-> db
                       (assoc :menu-showable (menu-showable? db item))
                       (assoc :page item))
                      :redirect path})))

;; Subscriptions


(rf/reg-sub :page (fn [db _] (:page db)))

(rf/reg-sub :menu-showable (fn [db _] (:menu-showable db)))

(rf/reg-sub :route (fn [db _] (:route db)))

(rf/reg-sub :content
  :<- [:page]
  (fn [c _ ]
    (cond (default-menu-item? c) (rn/render-default c)
          (documentation-item? c) (rn/render-doc c))))

(rf/reg-sub :menu
  :<- [:page]
  :<- [:menu-showable]
  (fn [[c m] _]
    (if-not m
      (default-menu c)
      (extended-menu c))))

;; Effects

(rf/reg-fx
 :redirect
 (fn [path] (redirect path)))

;; Components

(defn side-menu
  []
  [:nav (rn/with-key {:class (c :flex :flex-column [:mx 5] [:mt 8])})
   @(rf/subscribe [:menu])])

(defn page []
  [:div (rn/k) @(rf/subscribe [:content])])

(defn ui
  []
  [:div (rn/with-key {:class (c :flex :flex-row)})
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

(println "current app state is " @db/app-db)

(println "hash is:" (-> (.. js/window -location -hash)
                        routing/parse-fragment
                        :path))
(println "app state after route dispatch: " @db/app-db)
