(ns stylo.tailwind.table
  (:require
    [stylo.rule :refer [rule]]))


;; https://tailwindcss.com/docs/display/#table
(defmethod rule :table [_] [[:& {:display "table"}]])
(defmethod rule :table-caption [_] [[:& {:display "table-caption"}]])
(defmethod rule :table-cell [_] [[:& {:display "table-cell"}]])
(defmethod rule :table-column [_] [[:& {:display "table-column"}]])
(defmethod rule :table-column-group [_] [[:& {:display "table-column-group"}]])
(defmethod rule :table-footer-group [_] [[:& {:display "table-footer-group"}]])
(defmethod rule :table-header-group [_] [[:& {:display "table-header-group"}]])
(defmethod rule :table-row-group [_] [[:& {:display "table-row-group"}]])
(defmethod rule :table-row [_] [[:& {:display "table-row"}]])


;; https://tailwindcss.com/docs/table-layout
(defmethod rule :table-auto [_] [[:& {:table-layout "auto"}]])
(defmethod rule :table-fixed [_] [[:& {:table-layout "fixed"}]])


;; https://tailwindcss.com/docs/border-collapse/#app
(defmethod rule :border-collapse [_] [[:& {:border-collapse "collapse"}]])
(defmethod rule :border-separate [_] [[:& {:border-collapse "separate"}]])
