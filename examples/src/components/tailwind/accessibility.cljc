(ns components.tailwind.accessibility
  (:require [components.hiccup :as h]
            [stylo.tailwind.accessibility :as a]
            [stylo.core :refer [c]]))

(def headers ["Class" "Properties"])

(def td-c (c [:px 2] [:py 1] :border-b :align-top))

(defn accessibility []
  [:div {:class (c :border [:px 4] [:py 2])}
   [:h3 "Screen Readers"]
   [:p  "Utilities for improving accessibility with screen readers."]
   [:table
    [:thead
     [:tr
      [:th {:class td-c} "Prop"]
      [:th {:class td-c} "Prop2"]
      ]]
    [:tbody
     (for [[k v] a/accessibility-data]
       [:tr
        [:td {:class [td-c (c :whitespace-no-wrap)]} (pr-str k)]
        [:td {:class td-c} (for [[k v] v]
                             [:div [:b k ":"] (pr-str v)])]])]]])
