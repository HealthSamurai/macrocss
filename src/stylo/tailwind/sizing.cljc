(ns stylo.tailwind.sizing
  (:require
   [stylo.rule :refer [defrules]]
   [stylo.util :refer [parse-str-ratio as-unit]]))


;; https://tailwindcss.com/docs/width/#app

(def width {:w (fn [x] {:width (as-unit x)})
            :w-auto  {:width "auto"}
            :w-px  {:width "1px"}
            :w-full  {:width "100%"}
            :w-screen  {:width "100vh"}})

(defrules width)

;; https://tailwindcss.com/docs/max-width/#app

(def max-width {:w-max  (fn [x] {:max-width (as-unit x)})
                :w-max-none  {:max-width "none"}
                :w-max-full  {:max-width "100%"}
                :w-max-screen  {:max-width "100vh"}
                :w-max-sm  {:max-width "24rem"}
                :w-max-md  {:max-width "28rem"}
                :w-max-lg  {:max-width "32rem"}
                :w-max-xl  {:max-width "36rem"}
                :w-max-2xl  {:max-width "42rem"}
                :w-max-3xl  {:max-width "48rem"}
                :w-max-4xl  {:max-width "56rem"}
                :w-max-5xl  {:max-width "64rem"}
                :w-max-6xl  {:max-width "72rem"}
                :w-max-screen-sm  {:max-width "640px"}
                :w-max-screen-md  {:max-width "768px"}
                :w-max-screen-lg  {:max-width "1024px"}
                :w-max-screen-xl  {:max-width "1280px"}})

(defrules max-width)

;; https://tailwindcss.com/docs/min-width/#app

(def min-width {:w-min   (fn [x] {:min-width (as-unit x)})
                :w-min-none  {:min-width "none"}
                :w-min-full  {:min-width "100%"}
                :w-min-screen  {:min-width "100vh"}})

(defrules min-width)
;; https://tailwindcss.com/docs/height/#app

(def height {:h   (fn [x] {:height (as-unit x)})
             :h-auto  {:height "auto"}
             :h-px  {:height "1px"}
             :h-full  {:height "100%"}
             :h-screen  {:height "100vh"}})

(defrules height)
;; https://tailwindcss.com/docs/max-height/#app

(def max-height {:h-max   (fn [x] {:max-height (as-unit x)})
                 :h-max-none  {:max-height "none"}
                 :h-max-full  {:max-height "100%"}
                 :h-max-screen  {:max-height "100vh"}})

(defrules max-height)

;; https://tailwindcss.com/docs/min-height/#app
;;
(def min-height {:h-min   (fn [x] {:min-height (as-unit x)})
                  :h-min-none  {:min-height "none"}
                  :h-min-full  {:min-height "100%"}
                  :h-min-screen  {:min-height "100vh"}})

(defrules min-height)
