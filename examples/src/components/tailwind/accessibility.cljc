(ns components.tailwind.accessibility
  (:require [components.hiccup :refer [h1 h3
                                       p1 p3 a
                                       pre-bash code
                                       block table]
             :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.accessibility :as a]))

(def headers ["Class" "Properties"])

(def td-c (c [:px 2] [:py 1] :border-b :align-top))

(defn accessibility []
  [:div
   [:div {:class (c [:mt 8] [:mb 10])}
     (h1 "Screen Readers")
     (p1 "Utilities for improving accessibility with screen readers.")]
   [:table {:class (c :w-full :text-left :border-collapse)}
    (h/create-table-heading ["Class " "Properties"])
    (h/create-table-cells a/accessibility-data)]])
