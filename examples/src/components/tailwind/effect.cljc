(ns components.tailwind.effect
    (:require [components.hiccup :as h]
              [stylo.core :refer [c]]
              [stylo.tailwind.effect :as e]))

(defn effect []
  [:div (h/example-block "Box shadow"
                       "Utilities for controlling box shadow of element"
                       e/box-shadow)
   (h/example-block "Opacity"
                  "Utilities for controlling opacity of element"
                  (assoc e/opacity :opacity {:opacity "ENTER INTEGER, MEANS PERCENT"}))
   (h/navigation-arrows [:color :flex])])
