(ns components.hiccup
  (:require [stylo.core :refer [c]]))

(defn gen-key [] (gensym "key-"))

(defn k [] {:key (gen-key)})

(defn with-key [m] (merge (k) m))

(defn p
  ([content]
   [:p (with-key {:class (c [:mt 1] :text-base :text-gray-500)}) content])
  ([content & other-content]
   [:p (with-key {:class (c [:mt 1] :text-base :text-gray-500)}) content
    other-content]))

(defn pre-bash
  [content]
  [:div
   (with-key
     {:style {:background-color :black, :width :min-content, :border-radius "6px"},
      :class (c [:m 1])})
   [:pre (with-key {:class (c [:text :white] [:mt 1] :text-base)}) content]])

(defn heading
  [& content]
  [:div (with-key {:class (c :content-center [:mt 8])})
   [:div
    (with-key {:class (c :box-border [:pb 10] [:mb 10] [:border-b :gray-200])})
    [:h1
     (with-key {:class (c [:m 1]
                          :text-3xl
                          :inline-block
                          :extrabold
                          [:text :gray-900]
                          :tracking-tight)}) content]]])


(defn hash-link? [link]
 (= "/" (-> link
            first
           str)))

(defn href [path]
  (str "#" path))

(defn create-link [link]
(if (hash-link? link)
      (href link)
      link))

(defn a
  ([link] (a link link))
  ([link description]
   [:a (with-key {:href (create-link link), :class (c [:text :blue-300] :underline)})
    description]))
