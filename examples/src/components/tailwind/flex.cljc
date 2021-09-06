(ns components.tailwind.flex
    (:require [components.hiccup :as h]
              [stylo.tailwind.flex :as f]))

(defn flex []
  [:div
   (h/example-block "Flex"
                    "Utilities for controlling how flex items both grow and shrink."
                    f/flex)
   (h/example-block "Flex direction"
                    "Utilities for controlling the direction of flex items"
                    f/flex-direction)
   (h/example-block "Flex shrink and grow"
                  "Utilities for controlling how flex items shrink or grow"
                  {:flex-shrink {:flex-shrink "ENTER INTEGER, DEFAULTS TO 1"}
                   :flex-grow {:flex-grow "ENTER INTEGER, DEFAULTS TO 1"}})
   (h/example-block "Flex wrap"
                    "Utilities for controlling how flex items wrap"
                    f/flex-wrap)
   (h/example-block "Order"
                    "Utilities for controlling the order items in flexbox appear"
                    (assoc f/flex-order :order {:order "ENTER INTEGER, DEFAULTS TO 1"}))
   (h/example-block "Align items"
                    "Utilities for controlling how flex and grid items are positioned along a container's cross axis."
                    f/align-items)
   (h/example-block "Align content"
                    "Utilities for controlling how rows are positioned in multi-row flex and grid containers."
                    f/align-content)
   (h/example-block "Align self"
                    "Utilities for controlling how an individual flex or grid item is positioned along its container's cross axis."
                    f/align-self)
   (h/example-block "Justify content"
                    "Utilities for controlling how flex and grid items are positioned along a container's main axis."
                    f/justify-content)
      (h/navigation-arrows [:effect :grid])])
