(ns stylo.tailwind.grid
  (:require
    [stylo.rule :refer [rule]]
    [stylo.util :refer [as-unit] :as util]))


(defmethod rule :grid [_] [[:& {:display "grid"}]])


;; https://tailwindcss.com/docs/gap/#app
(defmethod rule :gap [_ x] [[:& {:gap (as-unit x)}]])
(defmethod rule :gap-px [_ ] [[:& {:gap (as-unit 1 :px)}]])

(defmethod rule :row-gap [_ x] [[:& {:row-gap (as-unit x)}]])
(defmethod rule :row-gap-px [_ ] [[:& {:row-gap (as-unit 1 :px)}]])

(defmethod rule :col-gap [_ x] [[:& {:column-gap (as-unit x)}]])
(defmethod rule :col-gap-px [_ ] [[:& {:column-gap (as-unit 1 :px)}]])


;; https://tailwindcss.com/docs/grid-template-columns
(defmethod rule :grid-cols-none [_] [[:& {:grid-template-columns "none"}]])
(defmethod rule :grid-cols [_ x] [[:& {:grid-template-columns (util/format "repeat(%d, minmax(0, 1fr))" (int x))}]])


;; https://tailwindcss.com/docs/grid-template-rows
(defmethod rule :grid-rows-none [_] [[:& {:grid-template-rows "none"}]])
(defmethod rule :grid-rows [_ x] [[:& {:grid-template-rows (util/format "repeat(%d, minmax(0, 1fr))" (int x))}]])


;; https://tailwindcss.com/docs/grid-column/#app
(defmethod rule :col-auto [_] [[:& {:grid-column "auto"}]])
(defmethod rule :col-span [_ x] [[:& {:grid-column (util/format "span %d / span %d" (int x) (int x))}]])

(defmethod rule :col-start [_ x] [[:& {:grid-column-start x}]])
(defmethod rule :col-start-auto [_] [[:& {:grid-column-start "auto"}]])

(defmethod rule :col-end [_ x] [[:& {:grid-column-end x}]])
(defmethod rule :col-end-auto [_] [[:& {:grid-column-end "auto"}]])


;; https://tailwindcss.com/docs/grid-row/#app
(defmethod rule :row-auto [_] [[:& {:grid-row "auto"}]])
(defmethod rule :row-span [_ x] [[:& {:grid-row (util/format "span %d / span %d" (int x) (int x))}]])

(defmethod rule :row-start [_ x] [[:& {:grid-row-start x}]])
(defmethod rule :row-start-auto [_] [[:& {:grid-row-start "auto"}]])

(defmethod rule :row-end [_ x] [[:& {:grid-row-end x}]])
(defmethod rule :row-end-auto [_] [[:& {:grid-row-end "auto"}]])


;; https://tailwindcss.com/docs/grid-auto-flow/#app
(defmethod rule :grid-flow-row [_] [[:& {:grid-auto-flow "row"}]])
(defmethod rule :grid-flow-col [_] [[:& {:grid-auto-flow "column"}]])

(defmethod rule :grid-flow-row-dense [_] [[:& {:grid-auto-flow "row dense"}]])
(defmethod rule :grid-flow-col-dense [_] [[:& {:grid-auto-flow "column dense"}]])
