(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [stylo.core :refer [c]]))

(def compiler
  (r/create-compiler {:function-components true}))

(defn app
  []
  [:div {:class (c [:text :purple-500] :extrabold :text-3xl :underline)} "Hello World!"])

(defn render
  []
  #?(:cljs
     (dom/render
      [app]
      (js/document.getElementById "app")
      compiler)))

(defn init
  []
  (render))
