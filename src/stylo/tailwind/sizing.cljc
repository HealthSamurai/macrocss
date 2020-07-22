(ns stylo.tailwind.sizing
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [parse-str-ratio as-unit]]))


;; https://tailwindcss.com/docs/width/#app
(defmethod rule :w [_ x] [[:& {:width (as-unit x)}]])

(defmethod rule :w-auto [_] [[:& {:width "auto"}]])
(defmethod rule :w-px [_] [[:& {:width "1px"}]])
(defmethod rule :w-full [_] [[:& {:width "100%"}]])
(defmethod rule :w-screen [_] [[:& {:width "100vh"}]])


;; https://tailwindcss.com/docs/max-width/#app
(defmethod rule :w-max [_ x] [[:& {:max-width (as-unit x)}]])

(defmethod rule :w-max-none [_] [[:& {:max-width "none"}]])
(defmethod rule :w-max-full [_] [[:& {:max-width "100%"}]])
(defmethod rule :w-max-screen [_] [[:& {:max-width "100vh"}]])

(defmethod rule :w-max-sm [_] [[:& {:max-width "24rem"}]])
(defmethod rule :w-max-md [_] [[:& {:max-width "28rem"}]])
(defmethod rule :w-max-lg [_] [[:& {:max-width "32rem"}]])
(defmethod rule :w-max-xl [_] [[:& {:max-width "36rem"}]])
(defmethod rule :w-max-2xl [_] [[:& {:max-width "42rem"}]])
(defmethod rule :w-max-3xl [_] [[:& {:max-width "48rem"}]])
(defmethod rule :w-max-4xl [_] [[:& {:max-width "56rem"}]])
(defmethod rule :w-max-5xl [_] [[:& {:max-width "64rem"}]])
(defmethod rule :w-max-6xl [_] [[:& {:max-width "72rem"}]])

(defmethod rule :w-max-screen-sm [_] [[:& {:max-width "640px"}]])
(defmethod rule :w-max-screen-md [_] [[:& {:max-width "768px"}]])
(defmethod rule :w-max-screen-lg [_] [[:& {:max-width "1024px"}]])
(defmethod rule :w-max-screen-xl [_] [[:& {:max-width "1280px"}]])


;; https://tailwindcss.com/docs/min-width/#app
(defmethod rule :w-min [_ x] [[:& {:min-width (as-unit x)}]])

(defmethod rule :w-min-none [_] [[:& {:min-width "none"}]])
(defmethod rule :w-min-full [_] [[:& {:min-width "100%"}]])
(defmethod rule :w-min-screen [_] [[:& {:min-width "100vh"}]])


;; https://tailwindcss.com/docs/height/#app
(defmethod rule :h [_ x] [[:& {:height (as-unit x)}]])

(defmethod rule :h-auto [_] [[:& {:height "auto"}]])
(defmethod rule :h-px [_] [[:& {:height "1px"}]])
(defmethod rule :h-full [_] [[:& {:height "100%"}]])
(defmethod rule :h-screen [_] [[:& {:height "100vh"}]])


;; https://tailwindcss.com/docs/max-height/#app
(defmethod rule :h-max [_ x] [[:& {:max-height (as-unit x)}]])

(defmethod rule :h-max-none [_] [[:& {:max-height "none"}]])
(defmethod rule :h-max-full [_] [[:& {:max-height "100%"}]])
(defmethod rule :h-max-screen [_] [[:& {:max-height "100vh"}]])


;; https://tailwindcss.com/docs/min-height/#app
(defmethod rule :h-min [_ x] [[:& {:min-height (as-unit x)}]])

(defmethod rule :h-min-none [_] [[:& {:min-height "none"}]])
(defmethod rule :h-min-full [_] [[:& {:min-height "100%"}]])
(defmethod rule :h-min-screen [_] [[:& {:min-height "100vh"}]])
