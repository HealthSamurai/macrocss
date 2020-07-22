(ns stylo.tailwind.transition
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [parse-str-ratio as-unit]]))


;; https://tailwindcss.com/docs/transition-property/#app
(defmethod rule :transition-none [_] [[:& {:transition-property "none"}]])
(defmethod rule :transition-all [_] [[:& {:transition-property "all"}]])
(defmethod rule :transition [_] [[:& {:transition-property "background-color, border-color, color, fill, stroke, opacity, box-shadow, transform"}]])
(defmethod rule :transition-colors [_] [[:& {:transition-property "background-color, border-color, color, fill, stroke"}]])
(defmethod rule :transition-opacity [_] [[:& {:transition-property "opacity"}]])
(defmethod rule :transition-shadow [_] [[:& {:transition-property "box-shadow"}]])
(defmethod rule :transition-transform [_] [[:& {:transition-property "transform"}]])


;; https://tailwindcss.com/docs/transition-duration/#app
(defmethod rule :duration [_ x] [[:& {:transition-duration (as-unit x :ms)}]])


;; https://tailwindcss.com/docs/transition-timing-function/#app
(defmethod rule :ease-linear [_] [[:& {:transition-timing-function "linear"}]])
(defmethod rule :ease-in [_] [[:& {:transition-timing-function "cubic-bezier(0.4, 0, 1, 1)"}]])
(defmethod rule :ease-out [_] [[:& {:transition-timing-function "cubic-bezier(0, 0, 0.2, 1)"}]])
(defmethod rule :ease-in-out [_] [[:& {:transition-timing-function "cubic-bezier(0.4, 0, 0.2, 1)"}]])


;; https://tailwindcss.com/docs/transition-delay/#app
(defmethod rule :delay [_ x] [[:& {:transition-delay (as-unit x :ms)}]])
