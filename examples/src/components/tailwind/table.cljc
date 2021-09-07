(ns components.tailwind.table
  (:require [components.hiccup :as h]
            [stylo.tailwind.table :as t]))


(defn table []
  [:div (h/example-block "Table"
                         "Utilities for controlling the table layout"
                         (merge t/table t/table-layout))
   (h/example-block "Table borders"
                    "Utilities for controlling table borders"
                    t/border-collapse)
      (h/navigation-arrows [:svg :transition])])
