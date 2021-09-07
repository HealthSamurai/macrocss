(ns stylo.tailwind.transition
  (:require
   [stylo.rule :refer [defrules]]
   [stylo.util :refer [parse-str-ratio as-unit]]))


;; https://tailwindcss.com/docs/transition-property/#app


(def transition-property  {:transition-none   {:transition-property "none"}
                           :transition-all   {:transition-property "all"}
                           :transition   {:transition-property "background-color, border-color, color, fill, stroke, opacity, box-shadow, transform"}
                           :transition-colors   {:transition-property "background-color, border-color, color, fill, stroke"}
                           :transition-opacity   {:transition-property "opacity"}
                           :transition-shadow   {:transition-property "box-shadow"}
                           :transition-transform   {:transition-property "transform"}})

(defrules transition-property)
;; https://tailwindcss.com/docs/transition-duration/#app
(def transition-duration {:duration (fn [x] {:transition-duration (as-unit x :ms)})})

(defrules transition-duration)

;; https://tailwindcss.com/docs/transition-timing-function/#app
(def transition-timing {:ease-linear   {:transition-timing-function "linear"}
                        :ease-in   {:transition-timing-function "cubic-bezier(0.4, 0, 1, 1)"}
                        :ease-out   {:transition-timing-function "cubic-bezier(0, 0, 0.2, 1)"}
                        :ease-in-out   {:transition-timing-function "cubic-bezier(0.4, 0, 0.2, 1)"}})

(defrules transition-timing)

;; https://tailwindcss.com/docs/transition-delay/#app
(def transition-delay {:delay (fn [x] {:transition-delay (as-unit x :ms)})})

(defrules transition-delay)
