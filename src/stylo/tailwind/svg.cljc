(ns stylo.tailwind.svg
  (:require
    [stylo.rule :refer [rule]]
    [stylo.tailwind.color :refer [colors]]))


;; https://tailwindcss.com/docs/fill/#app
(defmethod rule :fill [_ x] [[:& {:fill (colors x)}]])


;; https://tailwindcss.com/docs/stroke/#app
(defmethod rule :stroke [_ x] [[:& {:stroke (colors x)}]])

(defmethod rule :stroke-width [_ x] [[:& {:stroke-width x}]])
