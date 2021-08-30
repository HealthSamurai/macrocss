(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [re-frame.core :as rf]
            [stylo.core :refer [c]]
            [app.pages]
            [app.intro]))

(defn href [x] (str "#" x))

(rf/reg-event-db
  ::on-hash-change
  (fn [db [_ hash]]
    (println ::hash (subs hash 1))
    (assoc db ::hash (keyword (subs hash 1)))))

(defn on-hash-change []
  (let [disp-fn #(rf/dispatch [::on-hash-change (.-hash (.-location js/window))])]
    (aset js/window "onhashchange" disp-fn)
    (disp-fn)))


(rf/reg-event-fx
 :initialize
 (fn [_ _]
   ;; effect
   (on-hash-change)
   {:init-routes {}}))

(rf/reg-sub
  ::menu
  (fn [db _]
    (->> @app.pages/pages
         (map (fn [[k v]]
                (assoc v :id k :href (str "#" (name k)))))
         (sort-by :w))))

(defn side-menu
  []
  (let [m (rf/subscribe [::menu])]
    (fn []
      [:div {:class (c [:w 60] [:py 4] [:px 8])}
       [:div {:class (c [:mb 4] :text-bold)} "LOGO"]
       [:nav
        (for [item @m]
          [:a {:href  (:href item)
               :key (:id item)
               :class (c :block [:py 2] [:px 3]
                         [:text :gray-700] [:hover [:text :black]])}
           (:title item)])]])))

(rf/reg-sub
  ::content
  (fn [db _]
    (let [id (or (get db ::hash) :about)]
      (get @app.pages/pages id))))

(defn page []
  (let [m @(rf/subscribe [::content])]
    (if-let [c (:cmp m)]
      [c]
      [:div (pr-str "No compoment for " m)])))

(defn ui
  []
  [:div {:class (c :w-full )}
   [:div {:class (c [:w 340] :border :mx-auto :flex)}
    [side-menu]
    [:div {:class (c :flex-1)}
     [page]]]])

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
