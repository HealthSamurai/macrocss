(ns components.tailwind.transition
  (:require [components.hiccup :as h]
            [stylo.tailwind.transition :as t]))


(defn transition []
  [:div
   (h/example-block "Transition"
                         "Utilities for controlling the transition"
                         (assoc t/transition-property
                                :duration (h/unit :transition-duration)))
   (h/example-block "Transition timing function"
                    "Utilities for controlling transition timing"
                    t/transition-timing)
   (h/example-block "Transition delay"
                    "Utilities for controlling transition delay"
                    {:delay {:transition-delay "YOUR_INT MEANS MS"}})
   (h/navigation-arrows [:table :variant])])
