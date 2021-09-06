(ns stylo.tailwind.grid
  (:require
   [stylo.rule :refer [rule defrules]]
   [stylo.util :refer [as-unit] :as util]))

(def grid {:grid  {:display "grid"}})

(defrules grid)

;; https://tailwindcss.com/docs/gap/#app
(def gap {:gap  (fn [x] {:gap (as-unit x)})
          :gap-px {:gap (as-unit 1 :px)}
          :row-gap  (fn [x] {:row-gap (as-unit x)})
          :row-gap-px   {:row-gap (as-unit 1 :px)}
          :col-gap  (fn [x] {:column-gap (as-unit x)})
          :col-gap-px {:column-gap (as-unit 1 :px)}})



(defrules gap)

;; https://tailwindcss.com/docs/grid-columns
(def grid-columns {:grid-cols-none  {:grid-template-columns "none"}
                   :grid-cols  (fn [x] {:grid-template-columns (util/format "repeat(%d, minmax(0, 1fr))" (int x))})
                   :col-auto  {:grid-column "auto"}
                   :col-span  (fn [x] {:grid-column (util/format "span %d / span %d" (int x) (int x))})
                   :col-start  (fn [x] {:grid-column-start x})
                   :col-start-auto  {:grid-column-start "auto"}
                   :col-end (fn [x] {:grid-column-end x})
                   :col-end-auto  {:grid-column-end "auto"}})

(defrules grid-columns)

;; https://tailwindcss.com/docs/grid-template-rows
(def grid-rows
  {:grid-rows-none  {:grid-template-rows "none"}
   :grid-rows  (fn [x] {:grid-template-rows (util/format "repeat(%d, minmax(0, 1fr))" (int x))})
   :row-auto  {:grid-row "auto"}
   :row-span  (fn [x] {:grid-row (util/format "span %d / span %d" (int x) (int x))})
   :row-start  (fn [x] {:grid-row-start x})
   :row-start-auto {:grid-row-start "auto"}
   :row-end  (fn [x] {:grid-row-end x})
   :row-end-auto  {:grid-row-end "auto"}})

(defrules grid-rows)

;; https://tailwindcss.com/docs/grid-auto-flow/#app
(def grid-flow
  {:grid-flow-row  {:grid-auto-flow "row"}
   :grid-flow-col  {:grid-auto-flow "column"}
   :grid-flow-row-dense  {:grid-auto-flow "row dense"}
   :grid-flow-col-dense  {:grid-auto-flow "column dense"}})

(defrules grid-flow)
