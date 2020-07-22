(ns stylo.tailwind.border
  (:require
    [stylo.rule :refer [rule]]
    [stylo.tailwind.color :refer [colors]]
    [stylo.util :refer [with-alpha as-unit] :as util]
    [garden.compiler :refer [render-css]]))


(def rounded-size
  {:none "0" :sm "0.125rem" :md "0.375rem" :lg "0.5rem" :full "9999px"})


(defn rounded
  [x & keys]
  (let [x (cond
            (nil? x) "0.25rem"
            (int? x) (as-unit x :px)
            :else (rounded-size x))]
    (zipmap keys (repeat x))))


;; https://tailwindcss.com/docs/border-radius/#app
(defmethod rule :rounded
  ([_] [[:& (rounded nil :border-radius)]])
  ([_ x] [[:& (rounded x :border-radius)]]))

(defmethod rule :rounded-t
  ([_] [[:& (rounded nil :border-top-left-radius :border-top-right-radius)]])
  ([_ x] [[:& (rounded x :border-top-left-radius :border-top-right-radius)]]))

(defmethod rule :rounded-r
  ([_] [[:& (rounded nil :border-top-right-radius :border-bottom-right-radius)]])
  ([_ x] [[:& (rounded x :border-top-right-radius :border-bottom-right-radius)]]))

(defmethod rule :rounded-b
  ([_] [[:& (rounded nil :border-bottom-right-radius :border-bottom-left-radius)]])
  ([_ x] [[:& (rounded x :border-bottom-right-radius :border-bottom-left-radius)]]))

(defmethod rule :rounded-l
  ([_] [[:& (rounded nil :border-top-left-radius :border-bottom-left-radius)]])
  ([_ x] [[:& (rounded x :border-top-left-radius :border-bottom-left-radius)]]))

(defmethod rule :rounded-tl
  ([_] [[:& (rounded nil :border-top-left-radius)]])
  ([_ x] [[:& (rounded x :border-top-left-radius)]]))

(defmethod rule :rounded-tr
  ([_] [[:& (rounded nil :border-top-right-radius)]])
  ([_ x] [[:& (rounded x :border-top-right-radius)]]))

(defmethod rule :rounded-br
  ([_] [[:& (rounded nil :border-bottom-right-radius)]])
  ([_ x] [[:& (rounded x :border-bottom-right-radius)]]))

(defmethod rule :rounded-bl
  ([_] [[:& (rounded nil :border-bottom-left-radius)]])
  ([_ x] [[:& (rounded x :border-bottom-left-radius)]]))


;; https://tailwindcss.com/docs/border-width/#app
(defmethod rule :border
  ([_] (rule :border 1))
  ([_ & props]
   [[:& (->> props
          (reduce (fn [acc x]
                    (if (int? x)
                      (assoc acc :border-width (as-unit x :px))
                      (assoc acc :border-color (with-alpha (colors x) :--border-opacity) :--border-opacity 1)))
                  {:border-width (as-unit 1 :px)}))]]))

;; TODO: add colors
(defmethod rule :border-t
  ([_] (rule :border-t 1))
  ([_ x] [[:& {:border-top-width (as-unit x :px)}]]))

(defmethod rule :border-r
  ([_] (rule :border-r 1))
  ([_ x] [[:& {:border-right-width (as-unit x :px)}]]))

(defmethod rule :border-b
  ([_] (rule :border-b 1))
  ([_ & props]
   [[:& (->> props
             (reduce (fn [acc x]
                       (if (int? x)
                         (assoc acc :border-bottom-width (as-unit x :px))
                         (assoc acc :border-color (with-alpha (colors x) :--border-opacity) :--border-opacity 1)))
                     {:border-bottom-width (as-unit 1 :px)}))]]))

(defmethod rule :border-l
  ([_] (rule :border-l 1))
  ([_ x] [[:& {:border-left-width (as-unit x :px)}]]))


;; https://tailwindcss.com/docs/border-opacity/#app
(defmethod rule :border-opacity [_ x] [[:& {:--border-opacity (as-unit x :percent)}]])


;; https://tailwindcss.com/docs/border-style/#app
(defmethod rule :border-solid [_] [[:& {:border-style "solid"}]])
(defmethod rule :border-dashed [_] [[:& {:border-style "dashed"}]])
(defmethod rule :border-dotted [_] [[:& {:border-style "dotted"}]])
(defmethod rule :border-double [_] [[:& {:border-style "double"}]])
(defmethod rule :border-none [_] [[:& {:border-style "none"}]])


;; https://tailwindcss.com/docs/divide-color/#app
(defmethod rule :divide [_ x] [["&>*+*" {:border-color (with-alpha (colors x) :--divide-opacity) :--divide-opacity 1}]])


;; https://tailwindcss.com/docs/divide-opacity/#app
(defmethod rule :divide-opacity [_ x] [[:& {:--divide-opacity (as-unit x :percent)}]])


;; https://tailwindcss.com/docs/divide-width/#app
(defmethod rule :divide-x
  ([_] (rule :divide-x 1))
  ([_ x] [["&>*+*" {:--divide-x-reverse 0
                    :border-right-width (util/format "calc(%s * var(--divide-x-reverse))" (render-css (as-unit x :px)))
                    :border-left-width  (util/format "calc(%s * calc(1 - var(--divide-x-reverse)))" (render-css (as-unit x :px)))}]]))

(defmethod rule :divide-y
  ([_] (rule :divide-y 1))
  ([_ x] [["&>*+*" {:--divide-y-reverse  0
                    :border-top-width    (util/format "calc(%s * calc(1 - var(--divide-y-reverse)))" (render-css (as-unit x :px)))
                    :border-bottom-width (util/format "calc(%s * var(--divide-y-reverse))" (render-css (as-unit x :px)))}]]))
