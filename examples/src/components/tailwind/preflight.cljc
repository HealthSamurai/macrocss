(ns components.tailwind.preflight
  (:require [components.hiccup :as h]
            [stylo.tailwind.layout :as l]))

(defn preflight []
  [:div (h/block (h/h1 "Preflight")
                 (h/p1 "An opinionated set of base styles for Tailwind projects"))
   (h/block (h/p1 "Preflight is a huge bunch of pre-defined styles.")
            (h/p1 "Exhaustive guide is official "
                  (h/a "https://tailwindcss.com/docs/preflight" "Tailwind Preflight doc"))
            (h/navigation-arrows [:layout :sizing]))])
