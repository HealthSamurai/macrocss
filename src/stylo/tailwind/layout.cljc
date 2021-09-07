(ns stylo.tailwind.layout
  (:require
   [stylo.rule :refer [rule defrules]]
   [stylo.util :refer [as-unit]]))


;; https://tailwindcss.com/docs/box-sizing/#app

(def box-sizing {:box-border  {:box-sizing "border-box"}
                 :box-content  {:box-sizing "content-box"}})

(defrules box-sizing)


;; https://tailwindcss.com/docs/display/#app


(def display {:hidden  {:display "none"}
              :block  {:display "block"}
              :flow-root  {:display "flow-root"}
              :inline-block  {:display "inline-block"}
              :inline  {:display "inline"}
              :inline-flex  {:display "inline-flex"}
              :inline-grid  {:display "inline-grid"}})

(defrules display)

;; https://tailwindcss.com/docs/float/#app
(def css-floats {:float-right  {:float "right"}
            :float-left  {:float "left"}
            :float-none  {:float "none"}})

(defrules css-floats)

(def floats-clearfix {:clearfix {:content "\"\"" :display "table" :clear "both"}})

(defrules floats-clearfix :after)

;; https://tailwindcss.com/docs/clear/#app
(def clear {:clear-left  {:clear "left"}
            :clear-right  {:clear "right"}
            :clear-both  {:clear "both"}
            :clear-none  {:clear "none"}})

(defrules clear)

;; https://tailwindcss.com/docs/object-fit/#app
(def object-fit {:object-contain  {:object-fit "contain"}
                 :object-cover  {:object-fit "cover"}
                 :object-fill  {:object-fit "fill"}
                 :object-none  {:object-fit "none"}
                 :object-scale-down  {:object-fit "scale-down"}})

(defrules object-fit)

;; https://tailwindcss.com/docs/object-position/#app
(def object-position {:object-bottom  {:object-position "bottom"}
                      :object-center  {:object-position "center"}
                      :object-left  {:object-position "left"}
                      :object-left-bottom  {:object-position "left bottom"}
                      :object-left-top  {:object-position "left top"}
                      :object-right  {:object-position "right"}
                      :object-right-bottom  {:object-position "right bottom"}
                      :object-right-top  {:object-position "right top"}
                      :object-top  {:object-position "top"}})
(defrules object-position)

;; https://tailwindcss.com/docs/overflow/#app
(def overflow {:overflow-auto  {:overflow "auto"}
               :overflow-hidden  {:overflow "hidden"}
               :overflow-visible  {:overflow "visible"}
               :overflow-scroll  {:overflow "scroll"}
               :overflow-x-auto  {:overflow-x "auto"}
               :overflow-y-auto  {:overflow-y "auto"}
               :overflow-x-hidden  {:overflow-x "hidden"}
               :overflow-y-hidden  {:overflow-y "hidden"}
               :overflow-x-visible  {:overflow-x "visible"}
               :overflow-y-visible  {:overflow-y "visible"}
               :overflow-x-scroll  {:overflow-x "scroll"}
               :overflow-y-scroll  {:overflow-y "scroll"}
               :scrolling-touch  {:-webkit-overflow-scrolling "touch"}
               :scrolling-auto  {:-webkit-overflow-scrolling "auto"}})

(defrules overflow)

;; https://tailwindcss.com/docs/position/#app
(def position {:static  {:position "static"}
               :fixed  {:position "fixed"}
               :absolute  {:position "absolute"}
               :relative  {:position "relative"}
               :sticky  {:position "sticky"}})

(defrules position)

;; https://tailwindcss.com/docs/top-right-bottom-left/#app
(def top-right-bottom-left {:inset-auto {:top "auto"
                                         :right "auto"
                                         :bottom "auto"
                                         :left "auto"}
                            :inset (fn [x] {:top (as-unit x)
                                            :right (as-unit x)
                                            :bottom (as-unit x)
                                            :left (as-unit x)})
                            :inset-x-auto  {:left "auto" :right "auto"}
                            :inset-x (fn [x] {:left (as-unit x)
                                              :right (as-unit x)})
                            :inset-y-auto  {:top "auto" :bottom "auto"}
                            :inset-y (fn [x] {:top (as-unit x)
                                              :bottom (as-unit x)})
                            :top (fn [x] {:top (as-unit x)})
                            :top-auto  {:top "auto"}
                            :right (fn [x] {:right (as-unit x)})
                            :right-auto {:right "auto"}
                            :bottom  (fn [x] {:bottom (as-unit x)})
                            :bottom-auto  {:bottom "auto"}
                            :left (fn [x] {:left (as-unit x)})
                            :left-auto  {:left "auto"}})

(defrules top-right-bottom-left)

;; https://tailwindcss.com/docs/visibility/#app
(def visibility {:visible  {:visibility "visible"}
                 :invisible  {:visibility "hidden"}})

(defrules visibility)

;; https://tailwindcss.com/docs/z-index/#app
(def z-index {:z-auto  {:z-index "auto"}
              :z (fn [x] {:z-index x})})

(defrules z-index)
