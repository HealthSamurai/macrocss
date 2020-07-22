(ns stylo.tailwind.accessibility
  (:require
    [stylo.rule :refer [rule]]))


(defmethod rule :sr-only [_]
  [[:& {:position "absolute"
        :width "1px"
        :height "1px"
        :padding "0"
        :margin "-1px"
        :overflow "hidden"
        :clip "rect(0 0 0 0)"
        :white-space "nowrap"
        :border-width "0"}]])


(defmethod rule :not-sr-only
  [_]
  [[:& {:position "static"
        :width "auto"
        :height "auto"
        :padding "0"
        :margin "0"
        :overflow "visible"
        :clip "auto"
        :white-space "normal"}]])


