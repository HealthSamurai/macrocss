(ns stylo.tailwind.effect
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [as-unit]]))


;; https://tailwindcss.com/docs/box-shadow/#app
(defmethod rule :shadow [_] [[:& {:box-shadow "0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06)"}]])
(defmethod rule :shadow-xs [_] [[:& {:box-shadow "0 0 0 1px rgba(0, 0, 0, 0.05)"}]])
(defmethod rule :shadow-sm [_] [[:& {:box-shadow "0 1px 2px 0 rgba(0, 0, 0, 0.05)"}]])
(defmethod rule :shadow-md [_] [[:& {:box-shadow "0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)"}]])
(defmethod rule :shadow-lg [_] [[:& {:box-shadow "0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05)"}]])
(defmethod rule :shadow-xl [_] [[:& {:box-shadow "0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)"}]])
(defmethod rule :shadow-2xl [_] [[:& {:box-shadow "0 25px 50px -12px rgba(0, 0, 0, 0.25)"}]])
(defmethod rule :shadow-inner [_] [[:& {:box-shadow "inset 0 2px 4px 0 rgba(0, 0, 0, 0.06)"}]])
(defmethod rule :shadow-outline [_] [[:& {:box-shadow "0 0 0 3px rgba(66, 153, 225, 0.5)"}]])
(defmethod rule :shadow-none [_] [[:& {:box-shadow "none"}]])


;; https://tailwindcss.com/docs/opacity/#app
(defmethod rule :opacity [_ x] [[:& {:opacity (as-unit x :percent)}]])
