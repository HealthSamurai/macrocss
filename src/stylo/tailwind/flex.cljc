(ns stylo.tailwind.flex
  (:require
    [stylo.rule :refer [rule]]))


;; https://tailwindcss.com/docs/order/#app
(defmethod rule :order-first [_] [[:& {:order -9999}]])
(defmethod rule :order-last [_] [[:& {:order 9999}]])
(defmethod rule :order-none [_] [[:& {:order 0}]])
(defmethod rule :order [_ x] [[:& {:order x}]])


;; https://tailwindcss.com/docs/align-items/#app
(defmethod rule :items-start [_] [[:& {:align-items "flex-start"}]])
(defmethod rule :items-end [_] [[:& {:align-items "flex-end"}]])
(defmethod rule :items-center [_] [[:& {:align-items "center"}]])
(defmethod rule :items-baseline [_] [[:& {:align-items "baseline"}]])
(defmethod rule :items-stretch [_] [[:& {:align-items "stretch"}]])


;; https://tailwindcss.com/docs/align-content/#app
(defmethod rule :content-center [_] [[:& {:align-content "center"}]])
(defmethod rule :content-start [_] [[:& {:align-content "flex-start"}]])
(defmethod rule :content-end [_] [[:& {:align-content "flex-start"}]])
(defmethod rule :content-between [_] [[:& {:align-content "space-between"}]])
(defmethod rule :content-around [_] [[:& {:align-content "space-around"}]])


;; https://tailwindcss.com/docs/align-self/#app
(defmethod rule :self-auto [_] [[:& {:align-self "auto"}]])
(defmethod rule :self-start [_] [[:& {:align-self "flex-start"}]])
(defmethod rule :self-end [_] [[:& {:align-self "flex-end"}]])
(defmethod rule :self-center [_] [[:& {:align-self "center"}]])
(defmethod rule :self-stretch [_] [[:& {:align-self "stretch"}]])


;; https://tailwindcss.com/docs/display/#flex
(defmethod rule :flex [_] [[:& {:display "flex"}]])


;; https://tailwindcss.com/docs/justify-content/#app
(defmethod rule :justify-start [_] [[:& {:justify-content "flex-start"}]])
(defmethod rule :justify-center [_] [[:& {:justify-content "center"}]])
(defmethod rule :justify-end [_] [[:& {:justify-content "flex-end"}]])
(defmethod rule :justify-between [_] [[:& {:justify-content "space-between"}]])
(defmethod rule :justify-around [_] [[:& {:justify-content "space-around"}]])


;; https://tailwindcss.com/docs/flex/#app
(defmethod rule :flex-initial [_] [[:& {:flex "0 1 auto"}]])
(defmethod rule :flex-1 [_] [[:& {:flex "1 1 0%"}]])
(defmethod rule :flex-auto [_] [[:& {:flex "1 1 auto"}]])
(defmethod rule :flex-none [_] [[:& {:flex "none"}]])


;; https://tailwindcss.com/docs/flex-direction/#app
(defmethod rule :flex-row [_] [[:& {:flex-direction "row"}]])
(defmethod rule :flex-row-reverse [_] [[:& {:flex-direction "row-reverse"}]])
(defmethod rule :flex-col [_] [[:& {:flex-direction "column"}]])
(defmethod rule :flex-col-reverse [_] [[:& {:flex-direction "column-reverse"}]])


;; https://tailwindcss.com/docs/flex-grow/#app
(defmethod rule :flex-grow
  ([_] [[:& {:flex-grow 1}]])
  ([_ x] [[:& {:flex-grow x}]]))


;; https://tailwindcss.com/docs/flex-shrink/#app
(defmethod rule :flex-shrink
  ([_] [[:& {:flex-shrink 1}]])
  ([_ x] [[:& {:flex-shrink x}]]))


;; https://tailwindcss.com/docs/flex-wrap/#app
(defmethod rule :flex-no-wrap [_] [[:& {:flex-wrap "nowrap"}]])
(defmethod rule :flex-wrap [_] [[:& {:flex-wrap "wrap"}]])
(defmethod rule :flex-:wrap-reverse [_] [[:& {:flex-wrap "wrap-reverse"}]])
