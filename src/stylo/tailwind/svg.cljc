(ns stylo.tailwind.svg
  (:require
    [stylo.rule :refer [rule]]
    [stylo.tailwind.color :refer [colors]]))


;; https://tailwindcss.com/docs/fill/#app
(defmethod rule :fill [_ x] [[:& {:fill (colors x)}]])


;; https://tailwindcss.com/docs/stroke/#app
(defmethod rule :stroke [_ x] (if (int? x) [[:& {:stroke-width x}]] [[:& {:stroke (colors x)}]]))
