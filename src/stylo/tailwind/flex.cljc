(ns stylo.tailwind.flex
  (:require
   [stylo.rule :refer [rule defrules]]))


;; https://tailwindcss.com/docs/display/#flex


(def flex  {:flex  {:display "flex"}
            :flex-initial  {:flex "0 1 auto"}
            :flex-1  {:flex "1 1 0%"}
            :flex-auto  {:flex "1 1 auto"}
            :flex-none  {:flex "none"}})

(defrules flex)

;; https://tailwindcss.com/docs/flex-direction/#app
(def flex-direction {:flex-row  {:flex-direction "row"}
                     :flex-row-reverse  {:flex-direction "row-reverse"}
                     :flex-col  {:flex-direction "column"}
                     :flex-col-reverse  {:flex-direction "column-reverse"}})

(defrules flex-direction)

;; https://tailwindcss.com/docs/flex-grow/#app
(defmethod rule :flex-grow
  ([_] [[:& {:flex-grow 1}]])
  ([_ x] [[:& {:flex-grow x}]]))


;; https://tailwindcss.com/docs/flex-shrink/#app


(defmethod rule :flex-shrink
  ([_] [[:& {:flex-shrink 1}]])
  ([_ x] [[:& {:flex-shrink x}]]))


;; https://tailwindcss.com/docs/flex-wrap/#app


(def flex-wrap {:flex-no-wrap  {:flex-wrap "nowrap"}
                :flex-wrap  {:flex-wrap "wrap"}
                :flex-:wrap-reverse  {:flex-wrap "wrap-reverse"}})

(defrules flex-wrap)

;; https://tailwindcss.com/docs/order/#app


(def flex-order {:order-first  {:order -9999}
                 :order-last  {:order 9999}
                 :order-none  {:order 0}
                 :order (fn [x] {:order (or x 1)})})

(defrules flex-order)

;; https://tailwindcss.com/docs/align-items/#app
(def align-items {:items-start  {:align-items "flex-start"}
                  :items-end  {:align-items "flex-end"}
                  :items-center  {:align-items "center"}
                  :items-baseline  {:align-items "baseline"}
                  :items-stretch  {:align-items "stretch"}})

(defrules align-items)

;; https://tailwindcss.com/docs/align-content/#app
(def align-content {:content-center  {:align-content "center"}
                    :content-start  {:align-content "flex-start"}
                    :content-end  {:align-content "flex-start"}
                    :content-between  {:align-content "space-between"}
                    :content-around  {:align-content "space-around"}})

(defrules align-content)

;; https://tailwindcss.com/docs/align-self/#app
(def align-self {:self-auto  {:align-self "auto"}
                 :self-start  {:align-self "flex-start"}
                 :self-end  {:align-self "flex-end"}
                 :self-center  {:align-self "center"}
                 :self-stretch  {:align-self "stretch"}})

(defrules align-self)

;; https://tailwindcss.com/docs/justify-content/#app
(def justify-content {:justify-start  {:justify-content "flex-start"}
                      :justify-center  {:justify-content "center"}
                      :justify-end  {:justify-content "flex-end"}
                      :justify-between  {:justify-content "space-between"}
                      :justify-around  {:justify-content "space-around"}})

(defrules justify-content)
