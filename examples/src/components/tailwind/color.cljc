(ns components.tailwind.color
  (:require [components.hiccup :refer [h1 p1
                                       block example-block
                                       navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.color :as clr]
            [clojure.string :as str]))


(defn color []
  [:div
   (example-block "Colors." "Table of predefined colors." (->> clr/colors-data
                                                               (reduce (fn [acc [k v]]
                                                                         (assoc acc k {:color v})) {})
                                                               (into (sorted-map))))
   (navigation-arrows [:borders :container])])
