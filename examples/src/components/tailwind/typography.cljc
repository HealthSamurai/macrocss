(ns components.tailwind.typography
  (:require [components.hiccup :refer [h1 p1
                                       block example-block
                                       navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.typography :as t]
            [clojure.string :as str]))


(defn fw []
  (example-block "Font weight"
                 "Utilities for controlling the font weight of an element."
                 t/font-weight))

(defn ff []
  (example-block "Font family"
    "Utilities for controlling the font family of an element."
     t/font-family))

(defn fs []
  (example-block "Font size"
          "Utilities for controlling the font size of an element."
          t/font-size))

(defn fsm []
  (example-block "Font smoothing"
          "Utilities for controlling the font smoothing of an element."
          t/font-smoothing))

(defn fst []
  (example-block "Font style"
          "Utilities for controlling the style of text."
          t/font-style))

(defn ls []
  (example-block "Letter pacing"
          "Utilities for controlling the tracking (letter spacing) of an element."
          t/letter-spacing))

(defn lsp []
  (example-block "List style type"
          "Utilities for controlling the bullet/number style of a list."
          t/list-style-type))

(defn ph []
  (example-block "Placeholder color and opacity"
         "Utilities for controlling the color and opacity of placeholder text."
         (-> t/placeholder-color-opacity
             (assoc-in [:placeholder :color] "HEX CODE OR PREDEFINED COLOR KEY")
             (assoc-in [:placeholder-opacity :--placeholder-opacity] "ENTER INTEGER, MEANS PERCENT"))))

(defn ta []
 (example-block "Text align"
          "Utilities for controlling the alignment of text."
          t/text-align))

(defn tco []
  (example-block "Text color and opacity"
          "Utilities for controlling color and opacity of text"
          (-> t/text-color-opacity
              (assoc-in [:text :color] "HEX CODE OR PREDEFINED COLOR KEY")
              (assoc :text-opacity "ENTER INTEGER, MEANS PERCENT"))))

(defn td []
  (example-block "Text decoration"
         "Utililites for decoration of text"
         t/text-decoration))

(defn ttf []
  (example-block "Text transform"
          "Utilities for trasformation of text"
         t/text-transform))

(defn wb []
  (example-block "Word break"
                 "Utilities for word breaks in an element"
                 t/word-break))

(defn typography []
(block (h1 "Typography")
               (p1 "Styling text. Adjusting size, weight, style and other text properties.")

   (ff)
   (fw)
   (fs)
   (fsm)
   (fst)
   (ls)
   (lsp)
   (ph)
   (ta)
   (wb)
   (navigation-arrows [:accessibility :background])))
