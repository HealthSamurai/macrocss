(ns components.core
  (:require [app.pages :refer [pages all-pages]]
            [stylo.core :refer [c]]
            [components.templates :as t]
            [clojure.string :as str]
            [routing.core :refer [routes]]
            [components.hiccup :refer [href]]
            [re-frame.core :as rf]))

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
  (cond (= item :documentation) true
        (default-menu-item? item)  false
        (documentation-item? item) true))

(defn dispatch-doc-click [k]
  (when (= k :documentation)
    (fn [] (rf/dispatch [:doc-clicked]))))

(defn menu-item
  ([page k]
   (menu-item page k (-> k
                         name
                         str/capitalize)))
  ([page k description]
   [:li
    [:a
     {:class (clicked? page k)
      :href (-> k
                routes
                href)
      :on-click (dispatch-doc-click k)}
     description]]))

(defn create-menu [page items]
  (apply conj [:ul]
         (mapv (partial menu-item page) items)))

(defn default-menu [page]
  (let [default-menu-items (:default pages)]
    (create-menu page default-menu-items)))

(defn extended-menu
  [page]
  (create-menu page all-pages))

(def components {:about (t/about)
                 :installation (t/installation)
                 :documentation (t/documentation)})

(defn render-default [k]
  (k components))

(defn render-doc [k]
  (:installation components))

(defn render-page [c]
  (cond (default-menu-item? c) (render-default c)
        (documentation-item? c) (render-doc c)))

(defn render-menu [c m]
  (if-not m
    (default-menu c)
    (extended-menu c)))
