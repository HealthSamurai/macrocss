(ns stylo.tailwind.table
  (:require
   [stylo.rule :refer [defrules]]))


;; https://tailwindcss.com/docs/display/#table


(def table {:table  {:display "table"}
            :table-caption  {:display "table-caption"}
            :table-cell  {:display "table-cell"}
            :table-column  {:display "table-column"}
            :table-column-group  {:display "table-column-group"}
            :table-footer-group  {:display "table-footer-group"}
            :table-header-group  {:display "table-header-group"}
            :table-row-group  {:display "table-row-group"}
            :table-row  {:display "table-row"}})

(defrules table)

;; https://tailwindcss.com/docs/table-layout
(def table-layout {:table-auto  {:table-layout "auto"}
                   :table-fixed  {:table-layout "fixed"}})

(defrules table-layout)
;; https://tailwindcss.com/docs/border-collapse/#app
(def border-collapse {:border-collapse  {:border-collapse "collapse"}
                      :border-separate  {:border-collapse "separate"}})

(defrules border-collapse)
