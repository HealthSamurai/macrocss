(ns components.tailwind.accessibility
  (:require [components.hiccup :refer [heading
                                       p
                                       a
                                       pre-bash]]))

(defn accessibility []
  (heading "Screen Readers"
       (p "Utilities for improving accessibility with screen readers.")))
