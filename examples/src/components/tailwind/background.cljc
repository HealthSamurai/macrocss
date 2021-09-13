(ns components.tailwind.background
  (:require [components.hiccup :refer [h1 p1 example-block
                                       block navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.background :as b]))

(defn ba []
  (example-block "Background attachment"
                 "Utilities for controlling how a background image behaves when scrolling."
                 b/background-attachment))

(defn bco []
  (example-block "Background color and opacity"
                 "Utilities for contolling background color and opacity"
                 (-> b/background-color-opacity
                     (assoc :bg {:background-color "HEX CODE OR PREDEFINED COLOR KEY"
                                 :--bg-opacity 1})
                     (assoc :bg-opacity {:--bg-opacity "ENTER INTEGER, MEANS PERCENT"}))))

(defn bp []
  (example-block "Background position"
                 "Utilities for controlling background position"
                 b/background-position))

(defn background []
  [:div
   (block
    (h1 "Background")
    (p1 "Styling background. Adjusting attachments, color, opacity, size and position"))
   (ba)
   (bco)
   (bp)
   (navigation-arrows [:accessibility :borders])])
