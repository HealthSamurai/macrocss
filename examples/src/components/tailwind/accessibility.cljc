(ns components.tailwind.accessibility
  (:require [components.hiccup :refer [h1 h3
                                       p1 p3 a
                                       pre-bash code
                                       code-span
                                       block table]
             :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.accessibility :as a]))

(def headers ["Class" "Properties"])

(def td-c (c [:px 2] [:py 1] :border-b :align-top))

(defn accessibility []
  [:div
   (block
     (h1 "Screen Readers")
     (p1 "Utilities for improving accessibility with screen readers."))
   [:table {:class (c :w-full :text-left :border-collapse)}
    (h/create-table-heading ["Class " "Properties"])
    (h/create-table-cells a/accessibility-data)]

   (block (h3 "Usage")
          (p3 "Use " (code-span "'sr-only'") " to hide an element visually without hiding it from screen readers:")
          (code " [:a {:href \"#\"} \n   [:svg \"some.svg\"] \n   [:span {:class (c :sr-only)} \"Settings\"]]")
          (p3 "Use " (code-span "'not-sr-only'") " to undo sr-only, making an element visible to sighted users as well as screen readers.\n
 This can be useful when you want to visually hide something on small screens but show it on larger screens for example:")
          )])
