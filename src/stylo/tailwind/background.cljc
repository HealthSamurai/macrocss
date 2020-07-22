(ns stylo.tailwind.background
  (:require
    [stylo.rule :refer [rule]]
    [stylo.tailwind.color :refer [colors]]
    [stylo.util :refer [with-alpha as-unit]]))


;; https://tailwindcss.com/docs/background-attachment/#app
(defmethod rule :bg-fixed [_] [[:& {:background-attachment "fixed"}]])
(defmethod rule :bg-local [_] [[:& {:background-attachment "local"}]])
(defmethod rule :bg-scroll [_] [[:& {:background-attachment "scroll"}]])


;; https://tailwindcss.com/docs/background-color/#app
(defmethod rule :bg [_ x] [[:& {:background-color (with-alpha (colors x) :--bg-opacity) :--bg-opacity 1}]])


;; https://tailwindcss.com/docs/background-opacity/#app
(defmethod rule :bg-opacity [_ x] [[:& {:--bg-opacity (as-unit x :percent)}]])


;; https://tailwindcss.com/docs/background-position/#app
(defmethod rule :bg-center [_] [[:& {:background-position "50%"}]])
(defmethod rule :bg-bottom [_] [[:& {:background-position "50% 100%"}]])
(defmethod rule :bg-left [_] [[:& {:background-position "0"}]])
(defmethod rule :bg-left-bottom [_] [[:& {:background-position "0 100%"}]])
(defmethod rule :bg-left-top [_] [[:& {:background-position "0 0"}]])
(defmethod rule :bg-right [_] [[:& {:background-position "100%"}]])
(defmethod rule :bg-right-bottom [_] [[:& {:background-position "100% 100%"}]])
(defmethod rule :bg-right-top [_] [[:& {:background-position "100% 0"}]])
(defmethod rule :bg-top [_] [[:& {:background-position "top"}]])


;; https://tailwindcss.com/docs/background-position/#app
(defmethod rule :bg-repeat [_] [[:& {:background-repeat "repeat"}]])
(defmethod rule :bg-no-repeat [_] [[:& {:background-repeat "no-repeat"}]])
(defmethod rule :bg-repeat-x [_] [[:& {:background-repeat "repeat-x"}]])
(defmethod rule :bg-repeat-y [_] [[:& {:background-repeat "repeat-y"}]])
(defmethod rule :bg-repeat-round [_] [[:& {:background-repeat "round"}]])
(defmethod rule :bg-repeat-space [_] [[:& {:background-repeat "space"}]])


;; https://tailwindcss.com/docs/background-size/#app
(defmethod rule :bg-auto [_] [[:& {:background-size "auto"}]])
(defmethod rule :bg-cover [_] [[:& {:background-size "cover"}]])
(defmethod rule :bg-contain [_] [[:& {:background-size "contain"}]])
