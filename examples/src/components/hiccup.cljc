(ns components.hiccup
  (:require [stylo.core :refer [c]]
            [clojure.string :as str]))

(defn gen-key [] (gensym "key-"))

(defn k [] {:key (gen-key)})

(defn with-key [m] (merge (k) m))

(defn p
  ([content]
   [:p (with-key {:class (c [:mt 1] :text-base [:text :gray-500])}) content])
  ([content & other-content]
   [:p (with-key {:class (c [:mt 1] :text-base [:text :gray-500])}) content
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

(def table-heading-style
  {:class (c [:z 20] :sticky
             [:top 0] :text-sm
             :font-semibold [:text :gray-600]
             [:bg :white]
             [:p 0])
   :key (gen-key)})

(def table-content-style
  {:class (c [:pb 2] [:pr 2] :border-b [:border :gray-200])})

(defn right-cell-content [cell]
  (reduce (fn [acc [k v]]
            (conj acc [:p (-> k
                              name
                              (str ":")
                              (str "  '" v "'; "))]))
          [:div]
          cell))

(defn create-table-heading [keyseq]
  [:thead (reduce (fn [acc v] (conj acc [:th table-heading-style
                                         [:div table-content-style
                                          v]]))
                  [:tr] keyseq)])

(defn create-left-cell [k]
  [:td {:class (c [:py 2] [:pr 2]
                  :font-mono :text-xs
                  [:text :purple-600]
                  [:bg :white]
                  :whitespace-no-wrap)}
   k])

(defn create-right-cell [v]
  [:td {:class (c [:py 2] [:pl 2]
                  :font-mono :text-xs
                  [:text :blue-300]
                  :whitespace-pre)}
   (right-cell-content v)])

(defn create-table-cells [rule-data]
  (reduce (fn [acc [k v]] (conj acc [:tr (create-left-cell k)
                                     (create-right-cell v)]))
          [:tbody {:class (c :align-baseline)}] rule-data))



(defn table [heading-names rule-data]
[:div {:class (c :border-b
                   [:border :gray-200]
                   :overflow-hidden
                   :relative)}
   [:div {:class (c :overflow-y-auto)}
    [:table {:class (c :w-full :text-left :border-collapse)}
     (create-table-heading heading-names)
     (create-table-cells rule-data)]]])
