(ns components.tailwind.svg
  (:require [components.hiccup :as h]
            [stylo.tailwind.svg :as s]))
(defn svg []
  [:div (h/example-block "SVG"
                         "Utilities for styling SVG images"

                         {:stroke {:stroke "HEX CODE OR PREDEFINED COLOR KEY"}
                          :fill {:fill "HEX CODE OR PREDEFINED COLOR KEY"}
                          :stroke-width {:stroke-width "YOUR_INT"}})

   (h/navigation-arrows [:spacing :table])])
