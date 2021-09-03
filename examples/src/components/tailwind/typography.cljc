(ns components.tailwind.typography
  (:require [components.hiccup :refer [h1 h3
                                       p1 p3 a
                                       pre-bash code
                                       code-span
                                       block table
                                       navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.typography :as t]))

(defn fw []
  (block (h1 "Font weight")
         (p1 "Utilities for controlling the font weight of an element.")
         (h/table t/font-weight)))

(defn ff []
  (block (h1 "Font family")
          (p1 "Utilities for controlling the font family of an element.")
   (h/table t/font-family)))

(defn typography []
  [:div (block (h1 "Typography")
               (p1 "Styling text. Adjusting size, weight, style and other text properties."))

   (ff)
   (fw)])
