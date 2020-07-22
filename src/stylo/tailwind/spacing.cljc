(ns stylo.tailwind.spacing
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [as-unit] :as util]
    [garden.compiler :refer [render-css]]))


;; https://tailwindcss.com/docs/padding/#app
(defmethod rule :p [_ x] [[:& {:padding (as-unit x)}]])
(defmethod rule :p-auto [_] [[:& {:padding "auto"}]])
(defmethod rule :p-px [_] [[:& {:padding (as-unit 1 :px)}]])

(defmethod rule :px [_ x] [[:& {:padding-left (as-unit x) :padding-right (as-unit x)}]])
(defmethod rule :px-auto [_] [[:& {:padding-left "auto" :padding-right "auto"}]])
(defmethod rule :px-px [_] [[:& {:padding-left (as-unit 1 :px) :padding-right (as-unit 1 :px)}]])

(defmethod rule :py [_ x] [[:& {:padding-top (as-unit x) :padding-bottom (as-unit x)}]])
(defmethod rule :py-auto [_] [[:& {:padding-top "auto" :padding-bottom "auto"}]])
(defmethod rule :py-px [_] [[:& {:padding-top (as-unit 1 :px) :padding-bottom (as-unit 1 :px)}]])

(defmethod rule :pt [_ x] [[:& {:padding-top (as-unit x)}]])
(defmethod rule :pt-auto [_] [[:& {:padding-top "auto"}]])
(defmethod rule :pt-px [_] [[:& {:padding-top (as-unit 1 :px)}]])

(defmethod rule :pl [_ x] [[:& {:padding-left (as-unit x)}]])
(defmethod rule :pl-auto [_] [[:& {:padding-left "auto"}]])
(defmethod rule :pl-px [_] [[:& {:padding-left (as-unit 1 :px)}]])

(defmethod rule :pb [_ x] [[:& {:padding-bottom (as-unit x)}]])
(defmethod rule :pb-auto [_] [[:& {:padding-bottom "auto"}]])
(defmethod rule :pb-px [_] [[:& {:padding-bottom (as-unit 1 :px)}]])

(defmethod rule :pr [_ x] [[:& {:padding-right (as-unit x)}]])
(defmethod rule :pr-auto [_] [[:& {:padding-right "auto"}]])
(defmethod rule :pr-px [_] [[:& {:padding-right (as-unit 1 :px)}]])

(defmethod rule :pyx [_  y x]
  [[:& {:padding-top    (as-unit y)
        :padding-bottom (as-unit y)
        :padding-left   (as-unit x)
        :padding-right  (as-unit x)}]])

;; https://tailwindcss.com/docs/margin/#app
(defmethod rule :m [_ x] [[:& {:margin (as-unit x)}]])
(defmethod rule :m-auto [_] [[:& {:margin "auto"}]])
(defmethod rule :m-px [_] [[:& {:margin (as-unit 1 :px)}]])

(defmethod rule :mx [_ x] [[:& {:margin-left (as-unit x) :margin-right (as-unit x)}]])
(defmethod rule :mx-auto [_] [[:& {:margin-left "auto" :margin-right "auto"}]])
(defmethod rule :mx-px [_] [[:& {:margin-left (as-unit 1 :px) :margin-right (as-unit 1 :px)}]])

(defmethod rule :my [_ x] [[:& {:margin-top (as-unit x) :margin-bottom (as-unit x)}]])
(defmethod rule :my-auto [_] [[:& {:margin-top "auto" :margin-bottom "auto"}]])
(defmethod rule :my-px [_] [[:& {:margin-top (as-unit 1 :px) :margin-bottom (as-unit 1 :px)}]])

(defmethod rule :mt [_ x] [[:& {:margin-top (as-unit x)}]])
(defmethod rule :mt-auto [_] [[:& {:margin-top "auto"}]])
(defmethod rule :mt-px [_] [[:& {:margin-top (as-unit 1 :px)}]])

(defmethod rule :ml [_ x] [[:& {:margin-left (as-unit x)}]])
(defmethod rule :ml-auto [_] [[:& {:margin-left "auto"}]])
(defmethod rule :ml-px [_] [[:& {:margin-left (as-unit 1 :px)}]])

(defmethod rule :mb [_ x] [[:& {:margin-bottom (as-unit x)}]])
(defmethod rule :mb-auto [_] [[:& {:margin-bottom "auto"}]])
(defmethod rule :mb-px [_] [[:& {:margin-bottom (as-unit 1 :px)}]])

(defmethod rule :mr [_ x] [[:& {:margin-right (as-unit x)}]])
(defmethod rule :mr-auto [_] [[:& {:margin-right "auto"}]])
(defmethod rule :mr-px [_] [[:& {:margin-right (as-unit 1 :px)}]])


;; https://tailwindcss.com/docs/space/#app
(defmethod rule :space-x
  ([_] (rule :space-x 1))
  ([_ x] [["&>*+*" {:--space-x-reverse 0
                    :margin-right      (util/format "calc(%s * var(--space-x-reverse))" (render-css (as-unit x)))
                    :margin-left       (util/format "calc(%s * calc(1 - var(--space-x-reverse)))" (render-css (as-unit x)))}]]))
(defmethod rule :space-y
  ([_] (rule :space-y 1))
  ([_ x] [["&>*+*" {:--space-y-reverse 0
                    :margin-bottom     (util/format "calc(%s * var(--space-y-reverse))" (render-css (as-unit x)))
                    :margin-top        (util/format "calc(%s * calc(1 - var(--space-y-reverse)))" (render-css (as-unit x)))}]]))

(defmethod rule :space-x-reverse [_] [["&>*+*" {:--space-x-reverse 1}]])
(defmethod rule :space-y-reverse [_] [["&>*+*" {:--space-y-reverse 1}]])


(defmethod rule :myx [_ y x]
  [[:& {:margin-left   (as-unit x)
        :margin-right  (as-unit x)
        :margin-top    (as-unit y)
        :margin-bottom (as-unit y)}]])
