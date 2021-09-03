(ns stylo.tailwind.typography
  (:require
   [stylo.rule :refer [rule defrules]]
   [stylo.tailwind.color :refer [colors]]
   [stylo.util :refer [with-alpha as-unit]]))


;; https://tailwindcss.com/docs/font-family/#app


(def font-family
  {:font-sans {:font-family "system-ui, -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, \"Noto Sans\", sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Noto Color Emoji\""}
   :font-serif {:font-family "Georgia, Cambria, \"Times New Roman\", Times, serif"}
   :font-mono {:font-family "Menlo, Monaco, Consolas, \"Liberation Mono\", \"Courier New\", monospace"}})

(defrules font-family)


;; https://tailwindcss.com/docs/font-weight/#app


(def font-weight {:font-hairline {:font-weight 100}
                  :font-thin {:font-weight 200}
                  :font-light {:font-weight 300}
                  :font-normal {:font-weight 400}
                  :font-medium {:font-weight 500}
                  :font-semibold  {:font-weight 600}
                  :font-bold {:font-weight 700}
                  :font-extrabold {:font-weight 800}
                  :font-black {:font-weight 900}})

(defrules font-weight)

;; https://tailwindcss.com/docs/font-size/#app

(def font-size {:text-xs {:font-size ".75rem"}
                :text-sm {:font-size ".875rem"}
                :text-base {:font-size "1rem"}
                :text-lg {:font-size "1.125rem"}
                :text-xl {:font-size "1.25rem"}
                :text-2xl {:font-size "1.5rem"}
                :text-3xl {:font-size "1.875rem"}
                :text-4xl {:font-size "2.25rem"}
                :text-5xl {:font-size "3rem"}
                :text-6xl {:font-size "4rem"}})

(defrules font-size)

;; https://tailwindcss.com/docs/font-smoothing/#app

(def font-smoothing
  {:antialiased {:-webkit-font-smoothing "antialiased" :-moz-osx-font-smoothing "grayscale"}
   :subpixel-antialiased {:-webkit-font-smoothing "auto" :-moz-osx-font-smoothing "auto"}})

(defrules font-smoothing)

;; https://tailwindcss.com/docs/font-style/#app

(def font-style {:italic {:font-style "italic"}
                 :not-italic {:font-style "normal"}})

(defrules font-style)

;; https://tailwindcss.com/docs/letter-spacing/#app
(def letter-spacing {:tracking-tighter  {:letter-spacing "-0.05em"}
                     :tracking-tight   {:letter-spacing "-0.025em"}
                     :tracking-normal {:letter-spacing "0"}
                     :tracking-wide  {:letter-spacing "0.025em"}
                     :tracking-wider  {:letter-spacing "0.05em"}
                     :tracking-widest {:letter-spacing "0.1em"}})

(defrules letter-spacing)


;; https://tailwindcss.com/docs/line-height/#app


(def line-height {:leading-none  {:line-height 1}
                  :leading-tight {:line-height 1.25}
                  :leading-snug  {:line-height 1.375}
                  :leading-normal  {:line-height 1.5}
                  :leading-relaxed {:line-height 1.625}
                  :leading-loose  {:line-height 2}
                  :leading (fn [x] {:line-height (as-unit x)})})

(defrules line-height)

;; https://tailwindcss.com/docs/list-style-position/#app


(defmethod rule :list-inside [_] [[:& {:list-style-position "inside"}]])
(defmethod rule :list-outside [_] [[:& {:list-style-position "outside"}]])


;; https://tailwindcss.com/docs/list-style-type/#app


(defmethod rule :list-none [_] [[:& {:list-style "none"}]])
(defmethod rule :list-disc [_] [[:& {:list-style "disc"}]])
(defmethod rule :list-decimal [_] [[:& {:list-style "decimal"}]])


;; https://tailwindcss.com/docs/placeholder-color/#app


(defmethod rule :placeholder [_ x] [["&::placeholder" {:color (with-alpha (colors x) :--placeholder-opacity) :--placeholder-opacity 1}]])


;; https://tailwindcss.com/docs/placeholder-opacity/#app


(defmethod rule :placeholder-opacity [_ x] [["&::placeholder" {:--placeholder-opacity (as-unit x :percent)}]])


;; https://tailwindcss.com/docs/text-align/#app


(defmethod rule :text-left [_] [[:& {:text-align "left"}]])
(defmethod rule :text-center [_] [[:& {:text-align "center"}]])
(defmethod rule :text-right [_] [[:& {:text-align "right"}]])
(defmethod rule :text-justify [_] [[:& {:text-align "justify"}]])


;; https://tailwindcss.com/docs/text-color/#app


(defmethod rule :text [_ x] [[:& {:color (with-alpha (cond
                                                       (keyword? x) (colors x)
                                                       (string? x) x) :--text-opacity) :--text-opacity 1}]])


;; https://tailwindcss.com/docs/text-opacity/#app


(defmethod rule :text-opacity [_ x] [[:& {:--text-opacity (as-unit x :percent)}]])


;; https://tailwindcss.com/docs/text-decoration/#app


(defmethod rule :underline [_] [[:& {:text-decoration "underline"}]])
(defmethod rule :line-through [_] [[:& {:text-decoration "line-through"}]])
(defmethod rule :no-underline [_] [[:& {:text-decoration "none"}]])


;; https://tailwindcss.com/docs/text-transform/#app


(defmethod rule :uppercase [_] [[:& {:text-transform "uppercase"}]])
(defmethod rule :lowercase [_] [[:& {:text-transform "lowercase"}]])
(defmethod rule :capitalize [_] [[:& {:text-transform "capitalize"}]])
(defmethod rule :normal-case [_] [[:& {:text-transform "none"}]])


;; https://tailwindcss.com/docs/vertical-align/#app


(defmethod rule :align-baseline [_] [[:& {:vertical-align "baseline"}]])
(defmethod rule :align-top [_] [[:& {:vertical-align "top"}]])
(defmethod rule :align-middle [_] [[:& {:vertical-align "middle"}]])
(defmethod rule :align-bottom [_] [[:& {:vertical-align "bottom"}]])
(defmethod rule :align-text-top [_] [[:& {:vertical-align "text-top"}]])
(defmethod rule :align-text-bottom [_] [[:& {:vertical-align "text-bottom"}]])


;; https://tailwindcss.com/docs/whitespace/#app


(defmethod rule :whitespace-normal [_] [[:& {:white-space "normal"}]])
(defmethod rule :whitespace-no-wrap [_] [[:& {:white-space "nowrap"}]])
(defmethod rule :whitespace-pre [_] [[:& {:white-space "pre"}]])
(defmethod rule :whitespace-pre-line [_] [[:& {:white-space "pre-line"}]])
(defmethod rule :whitespace-pre-wrap [_] [[:& {:white-space "pre-wrap"}]])


;; https://tailwindcss.com/docs/word-break/#app


(defmethod rule :break-normal [_] [[:& {:overflow-wrap "normal" :word-break "normal"}]])
(defmethod rule :break-words [_] [[:& {:overflow-wrap "break-word"}]])
(defmethod rule :break-all [_] [[:& {:word-break "break-all"}]])
(defmethod rule :truncate [_] [[:& {:overflow "hidden" :text-overflow "ellipsis" :white-space "nowrap"}]])
