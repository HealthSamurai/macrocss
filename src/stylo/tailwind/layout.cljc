(ns stylo.tailwind.layout
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [as-unit]]))


;; https://tailwindcss.com/docs/box-sizing/#app
(defmethod rule :box-border [_] [[:& {:box-sizing "border-box"}]])
(defmethod rule :box-content [_] [[:& {:box-sizing "content-box"}]])


;; https://tailwindcss.com/docs/display/#app
(defmethod rule :hidden [_] [[:& {:display "none"}]])
(defmethod rule :block [_] [[:& {:display "block"}]])
(defmethod rule :flow-root [_] [[:& {:display "flow-root"}]])
(defmethod rule :inline-block [_] [[:& {:display "inline-block"}]])
(defmethod rule :inline [_] [[:& {:display "inline"}]])
(defmethod rule :inline-flex [_] [[:& {:display "inline-flex"}]])
(defmethod rule :inline-grid [_] [[:& {:display "inline-grid"}]])


;; https://tailwindcss.com/docs/float/#app
(defmethod rule :float-right [_] [[:& {:float "right"}]])
(defmethod rule :float-left [_] [[:& {:float "left"}]])
(defmethod rule :float-none [_] [[:& {:float "none"}]])
(defmethod rule :clearfix [_] [["&::after" {:content "\"\"" :display "table" :clear "both"}]])


;; https://tailwindcss.com/docs/clear/#app
(defmethod rule :clear-left [_] [[:& {:clear "left"}]])
(defmethod rule :clear-right [_] [[:& {:clear "right"}]])
(defmethod rule :clear-both [_] [[:& {:clear "both"}]])
(defmethod rule :clear-none [_] [[:& {:clear "none"}]])


;; https://tailwindcss.com/docs/object-fit/#app
(defmethod rule :object-contain [_] [[:& {:object-fit "contain"}]])
(defmethod rule :object-cover [_] [[:& {:object-fit "cover"}]])
(defmethod rule :object-fill [_] [[:& {:object-fit "fill"}]])
(defmethod rule :object-none [_] [[:& {:object-fit "none"}]])
(defmethod rule :object-scale-down [_] [[:& {:object-fit "scale-down"}]])


;; https://tailwindcss.com/docs/object-position/#app
(defmethod rule :object-bottom [_] [[:& {:object-position "bottom"}]])
(defmethod rule :object-center [_] [[:& {:object-position "center"}]])
(defmethod rule :object-left [_] [[:& {:object-position "left"}]])
(defmethod rule :object-left-bottom [_] [[:& {:object-position "left bottom"}]])
(defmethod rule :object-left-top [_] [[:& {:object-position "left top"}]])
(defmethod rule :object-right [_] [[:& {:object-position "right"}]])
(defmethod rule :object-right-bottom [_] [[:& {:object-position "right bottom"}]])
(defmethod rule :object-right-top [_] [[:& {:object-position "right top"}]])
(defmethod rule :object-top [_] [[:& {:object-position "top"}]])


;; https://tailwindcss.com/docs/overflow/#app
(defmethod rule :overflow-auto [_] [[:& {:overflow "auto"}]])
(defmethod rule :overflow-hidden [_] [[:& {:overflow "hidden"}]])
(defmethod rule :overflow-visible [_] [[:& {:overflow "visible"}]])
(defmethod rule :overflow-scroll [_] [[:& {:overflow "scroll"}]])
(defmethod rule :overflow-x-auto [_] [[:& {:overflow-x "auto"}]])
(defmethod rule :overflow-y-auto [_] [[:& {:overflow-y "auto"}]])
(defmethod rule :overflow-x-hidden [_] [[:& {:overflow-x "hidden"}]])
(defmethod rule :overflow-y-hidden [_] [[:& {:overflow-y "hidden"}]])
(defmethod rule :overflow-x-visible [_] [[:& {:overflow-x "visible"}]])
(defmethod rule :overflow-y-visible [_] [[:& {:overflow-y "visible"}]])
(defmethod rule :overflow-x-scroll [_] [[:& {:overflow-x "scroll"}]])
(defmethod rule :overflow-y-scroll [_] [[:& {:overflow-y "scroll"}]])
(defmethod rule :scrolling-touch [_] [[:& {:-webkit-overflow-scrolling "touch"}]])
(defmethod rule :scrolling-auto [_] [[:& {:-webkit-overflow-scrolling "auto"}]])


;; https://tailwindcss.com/docs/position/#app
(defmethod rule :static [_] [[:& {:position "static"}]])
(defmethod rule :fixed [_] [[:& {:position "fixed"}]])
(defmethod rule :absolute [_] [[:& {:position "absolute"}]])
(defmethod rule :relative [_] [[:& {:position "relative"}]])
(defmethod rule :sticky [_] [[:& {:position "sticky"}]])


;; https://tailwindcss.com/docs/top-right-bottom-left/#app
(defmethod rule :inset-auto [_] [[:& {:top "auto" :right "auto" :bottom "auto" :left "auto"}]])
(defmethod rule :inset [_ x] [[:& {:top (as-unit x) :right (as-unit x) :bottom (as-unit x) :left (as-unit x)}]])
(defmethod rule :inset-x-auto [_] [[:& {:left "auto" :right "auto"}]])
(defmethod rule :inset-x [_ x] [[:& {:left (as-unit x) :right (as-unit x)}]])
(defmethod rule :inset-y-auto [_] [[:& {:top "auto" :bottom "auto"}]])
(defmethod rule :inset-y [_ x] [[:& {:top (as-unit x) :bottom (as-unit x)}]])
(defmethod rule :top [_ x] [[:& {:top (as-unit x)}]])
(defmethod rule :top-auto [_] [[:& {:top "auto"}]])
(defmethod rule :right [_ x] [[:& {:right (as-unit x)}]])
(defmethod rule :right-auto [_] [[:& {:right "auto"}]])
(defmethod rule :bottom [_ x] [[:& {:bottom (as-unit x)}]])
(defmethod rule :bottom-auto [_] [[:& {:bottom "auto"}]])
(defmethod rule :left [_ x] [[:& {:left (as-unit x)}]])
(defmethod rule :left-auto [_] [[:& {:left "auto"}]])


;; https://tailwindcss.com/docs/visibility/#app
(defmethod rule :visible [_] [[:& {:visibility "visible"}]])
(defmethod rule :invisible [_] [[:& {:visibility "hidden"}]])


;; https://tailwindcss.com/docs/z-index/#app
(defmethod rule :z-auto [_] [[:& {:z-index "auto"}]])
(defmethod rule :z [_ x] [[:& {:z-index x}]])
