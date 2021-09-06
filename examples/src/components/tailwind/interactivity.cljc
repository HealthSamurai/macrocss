(ns components.tailwind.interactivity
  (:require [components.hiccup :as h]
            [stylo.tailwind.interactivity :as i]
            [stylo.util :as u]))

(defn interactivity []
  [:div
   (h/example-block "Appearance"
                    "Utlities for adjusting appearance"
                    i/appearance)

   (h/example-block "Cursor"
                    "Utilities for styling cursor"
                    i/cursor)
   (h/example-block "Outline"
                    "Utilities for adjusting outline"
                    i/outline)
   (h/example-block "Pointer events"
                    "Utilities for adjusting pointer events"
                    i/pointer-events)
   (h/example-block "Resize"
                    "Utilities for resizing"
                    i/resize)
   (h/navigation-arrows [:grid :layout])])
