(ns components.tailwind.accessibility
  (:require [components.hiccup :as h]
            [stylo.tailwind.accessibility :as a]))

(def headers ["Class" "Properties"])

(defn accessibility []
  [:div (h/heading "Screen Readers"
                   (h/p "Utilities for improving accessibility with screen readers."))
   (h/table headers a/accessibility-data)])
