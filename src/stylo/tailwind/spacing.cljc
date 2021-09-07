(ns stylo.tailwind.spacing
  (:require
   [stylo.rule :refer [rule defrules]]
   [stylo.util :refer [as-unit] :as util]
   [garden.compiler :refer [render-css]]))


;; https://tailwindcss.com/docs/padding/#app

(def padding {:p (fn [x] {:padding (as-unit x)})
              :p-auto  {:padding "auto"}
              :p-px  {:padding (as-unit 1 :px)}

              :px  (fn [x] {:padding-left (as-unit x) :padding-right (as-unit x)})
              :px-auto  {:padding-left "auto" :padding-right "auto"}
              :px-px  {:padding-left (as-unit 1 :px) :padding-right (as-unit 1 :px)}

              :py  (fn [x] {:padding-top (as-unit x) :padding-bottom (as-unit x)})
              :py-auto  {:padding-top "auto" :padding-bottom "auto"}
              :py-px  {:padding-top (as-unit 1 :px) :padding-bottom (as-unit 1 :px)}

              :pt  (fn [x] {:padding-top (as-unit x)})
              :pt-auto  {:padding-top "auto"}
              :pt-px  {:padding-top (as-unit 1 :px)}

              :pl  (fn [x] {:padding-left (as-unit x)})
              :pl-auto  {:padding-left "auto"}
              :pl-px  {:padding-left (as-unit 1 :px)}

              :pb  (fn [x] {:padding-bottom (as-unit x)})
              :pb-auto  {:padding-bottom "auto"}
              :pb-px  {:padding-bottom (as-unit 1 :px)}

              :pr  (fn [x] {:padding-right (as-unit x)})
              :pr-auto  {:padding-right "auto"}
              :pr-px  {:padding-right (as-unit 1 :px)}})

(defrules padding)

(defmethod rule :pyx [_  y x]
  [[:& {:padding-top    (as-unit y)
        :padding-bottom (as-unit y)
        :padding-left   (as-unit x)
        :padding-right  (as-unit x)}]])

;; https://tailwindcss.com/docs/margin/#app
(def margin {:m  (fn [x] {:margin (as-unit x)})
             :m-auto  {:margin "auto"}
             :m-px  {:margin (as-unit 1 :px)}

             :mx  (fn [x] {:margin-left (as-unit x) :margin-right (as-unit x)})
             :mx-auto  {:margin-left "auto" :margin-right "auto"}
             :mx-px  (fn [x] {:margin-left (as-unit 1 :px) :margin-right (as-unit 1 :px)})
             :my  (fn [x] {:margin-top (as-unit x) :margin-bottom (as-unit x)})
             :my-auto  {:margin-top "auto" :margin-bottom "auto"}
             :my-px  {:margin-top (as-unit 1 :px) :margin-bottom (as-unit 1 :px)}

             :mt  (fn [x] {:margin-top (as-unit x)})
             :mt-auto  {:margin-top "auto"}
             :mt-px  {:margin-top (as-unit 1 :px)}

             :ml  (fn [x] {:margin-left (as-unit x)})
             :ml-auto  {:margin-left "auto"}
             :ml-px  {:margin-left (as-unit 1 :px)}

             :mb  (fn [x] {:margin-bottom (as-unit x)})
             :mb-auto  {:margin-bottom "auto"}
             :mb-px  {:margin-bottom (as-unit 1 :px)}

             :mr  (fn [x] {:margin-right (as-unit x)})
             :mr-auto  {:margin-right "auto"}
             :mr-px  {:margin-right (as-unit 1 :px)}})

(defrules margin)
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
