(ns components.tailwind.sizing
  (:require [components.hiccup :as h]
            [stylo.tailwind.sizing :as s]))

(defn sizing []
  [:div (h/example-block "Width"
                         "Utilities for setting the width of an element"
                         (assoc s/width
                                :w
                                (h/unit :width)))
   (h/example-block "Max-Width"
                    "Utilities for setting maximum width of an element"
                    (assoc s/max-width :w-max (h/unit :max-width)))
   (h/example-block "Min-Width"
                    "Utilities for setting minimum width of an element"
                    (assoc s/min-width :w-min (h/unit :w-min)))
   (h/example-block "Height"
                    "Utilities for setting height of an element"
                    (assoc s/height :h (h/unit :height)))
   (h/example-block "Max-Height"
                    "Utilities for setting maximum height of an element"
                    (assoc s/max-height :h-max (h/unit :max-height)))
   (h/example-block "Min-Height"
                    "Utilities for setting minumum height of an element"
                    (assoc s/min-height :h-min (h/unit :min-height)))
   (h/navigation-arrows [:preflight :spacing])])
