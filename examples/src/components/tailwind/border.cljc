(ns components.tailwind.border
  (:require [components.hiccup :refer [h1 p1 example-block
                                       block navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.border :as b]))



(defn borders []
  [:div
   (block (h1 "Borders")
               (p1 "Styling borders. Adjusting size, radius, color and other border properties."))
   (example-block "Rounded. Border. Divide."
                  "Default values for border styling. All of them can accept custom value as arguments."
                  b/borders-default)
   (navigation-arrows [:background :color])])
