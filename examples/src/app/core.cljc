(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [re-frame.core :as rf]
            [stylo.core :refer [c]]
            [re-frame.db :as db]
            [app.pages]
            [app.intro]))

(defn href [x] (str "#" x))

(rf/reg-event-db
  ::on-hash-change
  (fn [db [_ hash]]
    (if (empty? (subs hash 1))
      ((set! (.-hash (.-location js/window)) "about")
       (assoc db ::hash :about))
        (assoc db ::hash (keyword (subs hash 1))))))

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
                (assoc v :id k
                         :href (str "#" (name k))
                         :clicked (= k (::hash db)))))
         (sort-by :w))))

(defn clicked-opacity
  [pred]
  (let [style (if pred
                  (c [:rounded :md]
                     [:inset 0]
                     [:duration 200]
                     :absolute
                     [:bg "#ecfeff"])
                  (c [:rounded :md]
                     [:inset 0]
                     [:duration 200]
                     :absolute
                     [:bg "#ecfeff"]
                     [:opacity 0]))]
  [:span {:class style}]))

(defn clicked-text-color [pred]
  (if pred
    (c :relative :block
       [:text "#0097a7"]
       :transition-colors
       [:px 3]
       [:py 2])
    (c [:pseudo :hover [:text :gray-900]]
       :relative :block
       :transition-colors
       [:px 3]
       [:py 2]
       [:text :gray-600])))

(defn side-menu
  []
  (let [m (rf/subscribe [::menu])]
    (fn []
      [:div {:class (c [:w 72] [:py 4] [:px 8])}
       [:div {:class (c [:mb 2] :flex)}
        [:div [:img {:class (c [:w 15] )
                     :src "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmedia-exp1.licdn.com%2Fdms%2Fimage%2FC560BAQHmyLQmsMqKJg%2Fcompany-logo_200_200%2F0%3Fe%3D2159024400%26v%3Dbeta%26t%3DZQvloyFokWovUhF-xj36Cc1Xfv9xHFS-4JwwXKxDd-c&f=1&nofb=1" }]]
        [:div {:class (c [:ml 1] [:mt 3]
                        :text-xl :font-extrabold)} "macroCSS"]]
       [:nav {:class (c :flex-column) }
        (for [{:keys [id href title clicked] :as item} @m
              :let [_ (println item)]]

          [:div {:class (c [:flex-grow 4])}
           (if-not (or (= "Documentation" title)
                       (= "Introduction" title))
             [:a {:href href
               :key id
               :class (clicked-text-color clicked)}
              (clicked-opacity clicked)
             [:span {:class (c :relative
                               [:line-height 16]
                               :text-sm
                               :font-normal)} title]]
             [:span {:class (c :text-sm
                               :relative
                                :font-semibold
                                [:line-height 16]
                                [:text :gray-900]
                                [:pt 2]
                                [:pb 2])} (str title ": ")])])]])))

(rf/reg-sub
  ::content
  (fn [db _]
    (let [id (or (get db ::hash) :about)]
      (get @app.pages/pages id))))

(defn page []
  (let [m @(rf/subscribe [::content])]
    (println "pages is: "@app.pages/pages)
    (if-let [c (:cmp m)]
      [c]
      [:div (pr-str "No compoment for " m)])))

(defn ui
  []
  [:div {:class (c :w-full)}
   [:div {:class (c [:w 340] :border :mx-auto :flex)}
    [side-menu]
    [:div {:class (c :flex-1)}
     [page]]
    [:div {:class (c :flex-1)}]]])

;; Re-frame machinery
(def compiler
  (r/create-compiler {:function-components true}))

(defn render [] (dom/render [ui] (js/document.getElementById "app") compiler))

(defn ^:dev/after-load clear-cache-and-render!
  []
  (rf/clear-subscription-cache!)
  (render))

(defn run [] (rf/dispatch-sync [:initialize]) (render))

(println "db is: " db/app-db)

(run)
