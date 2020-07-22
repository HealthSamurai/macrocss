(ns stylo.tailwind.transform
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [parse-str-ratio as-unit]]))


(defmethod rule :transform [_] [[:& {:--transform-rotate 0 :--transform-translate-x 0 :--transform-translate-y 0 :--transform-skew-x 0 :--transform-skew-y 0 :--transform-scale-x 1 :--transform-scale-y 1 :transform "translateX(var(--transform-translate-x)) translateY(var(--transform-translate-y)) rotate(var(--transform-rotate)) skewX(var(--transform-skew-x)) skewY(var(--transform-skew-y)) scaleX(var(--transform-scale-x)) scaleY(var(--transform-scale-y))"}]])
(defmethod rule :transform-none [_] [[:& {:transform "none"}]])


;; https://tailwindcss.com/docs/transform-origin/#app
(defmethod rule :origin-center [_] [[:& {:transform-origin "center"}]])
(defmethod rule :origin-top [_] [[:& {:transform-origin "top"}]])
(defmethod rule :origin-top-right [_] [[:& {:transform-origin "top right"}]])
(defmethod rule :origin-right [_] [[:& {:transform-origin "right"}]])
(defmethod rule :origin-bottom-right [_] [[:& {:transform-origin "bottom right"}]])
(defmethod rule :origin-bottom [_] [[:& {:transform-origin "bottom"}]])
(defmethod rule :origin-bottom-left [_] [[:& {:transform-origin "bottom left"}]])
(defmethod rule :origin-left [_] [[:& {:transform-origin "left"}]])
(defmethod rule :origin-top-left [_] [[:& {:transform-origin "top left"}]])


;; https://tailwindcss.com/docs/rotate/#app
(defmethod rule :rotate [_ x] [[:& {:--transform-rotate (as-unit x :deg)}]])


;; https://tailwindcss.com/docs/scale/#app
(defmethod rule :scale [_ x] [[:& {:--transform-scale-x (/ x 100.0) :--transform-scale-y (/ x 100.0)}]])
(defmethod rule :scale-x [_ x] [[:& {:--transform-scale-x (/ x 100.0)}]])
(defmethod rule :scale-y [_ x] [[:& {:--transform-scale-y (/ x 100.0)}]])


;; https://tailwindcss.com/docs/translate/#app
(defmethod rule :translate-x-px [_] [[:& {:--transform-translate-x (as-unit 1 :px)}]])
(defmethod rule :translate-x-full [_] [[:& {:--transform-translate-x (as-unit 100 :percent)}]])
(defmethod rule :translate-x [_ x] [[:& {:--transform-translate-x (as-unit x)}]])

(defmethod rule :translate-y-px [_] [[:& {:--transform-translate-y (as-unit 1 :px)}]])
(defmethod rule :translate-y-full [_] [[:& {:--transform-translate-y (as-unit 100 :percent)}]])
(defmethod rule :translate-y [_ x] [[:& {:--transform-translate-y (as-unit x)}]])


;; https://tailwindcss.com/docs/skew/#app
(defmethod rule :skew-x [_ x] [[:& {:--transform-skew-x (as-unit x :deg)}]])
(defmethod rule :skew-y [_ x] [[:& {:--transform-skew-y (as-unit x :deg)}]])
