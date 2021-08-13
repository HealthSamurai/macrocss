(ns figcss.core
  (:require [reagent.dom :as rd]))

(enable-console-print!)

(println "We test Figwheel and MacroCSS library")

(defn hello-world []
  [:div "Hello World!"])

(rd/render [hello-world]
          (.getElementById js/document "app"))