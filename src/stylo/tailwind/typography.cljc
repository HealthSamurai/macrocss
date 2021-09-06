(ns stylo.tailwind.typography
  (:require
   [stylo.rule :refer [rule defrules]]
   [stylo.tailwind.color :refer [colors]]
   [stylo.util :refer [with-alpha as-unit]]
   [clojure.string :as str]))


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

(def list-style-position {:list-inside {:list-style-position "inside"}
                          :list-outside {:list-style-position "outside"}})

(defrules list-style-position)

;; https://tailwindcss.com/docs/list-style-type/#app

(def list-style-type
  {:list-none  {:list-style "none"}
   :list-disc {:list-style "disc"}
   :list-decimal {:list-style "decimal"}})

(defrules list-style-type)

;; https://tailwindcss.com/docs/placeholder-color/#app
;; https://tailwindcss.com/docs/placeholder-opacity/#app

(def placeholder-color-opacity {:placeholder {:color (fn [x] (with-alpha (colors x) :--placeholder-opacity)) :--placeholder-opacity 1}
                                :placeholder-opacity {:--placeholder-opacity (fn [x] (as-unit x :percent))}})

(defrules placeholder-color-opacity :placeholder)

 ;; https://tailwindcss.com/docs/text-align/#app


(def text-align {:text-left  {:text-align "left"}
                 :text-center  {:text-align "center"}
                 :text-right  {:text-align "right"}
                 :text-justify  {:text-align "justify"}})

(defrules text-align)

;; https://tailwindcss.com/docs/text-color/#app
;; https://tailwindcss.com/docs/text-opacity/#app

(def text-color-opacity {:text (fn [x] {:color (with-alpha (colors x) :--text-opacity) :--text-opacity 1})
:text-opacity (fn [x] {:--text-opacity (as-unit x :percent)})})

(defrules text-color-opacity)

;; https://tailwindcss.com/docs/text-decoration/#app


(def text-decoration
  {:underline {:text-decoration "underline"}
   :line-through {:text-decoration "line-through"}
   :no-underline  {:text-decoration "none"}})

(defrules text-decoration)

;; https://tailwindcss.com/docs/text-transform/#app

(def text-transform {:uppercase  {:text-transform "uppercase"}
                     :lowercase  {:text-transform "lowercase"}
                     :capitalize  {:text-transform "capitalize"}
                     :normal-case {:text-transform "none"}})

(defrules text-transform)

;; https://tailwindcss.com/docs/vertical-align/#app

(def vertical-align {:align-baseline {:vertical-align "baseline"}
                     :align-top {:vertical-align "top"}
                     :align-middle  {:vertical-align "middle"}
                     :align-bottom  {:vertical-align "bottom"}
                     :align-text-top  {:vertical-align "text-top"}
                     :align-text-bottom  {:vertical-align "text-bottom"}})

(defrules vertical-align)

;; https://tailwindcss.com/docs/whitespace/#app

(def whitespace {:whitespace-normal {:white-space "normal"}
                 :whitespace-no-wrap {:white-space "nowrap"}
                 :whitespace-pre  {:white-space "pre"}
                 :whitespace-pre-line {:white-space "pre-line"}
                 :whitespace-pre-wrap {:white-space "pre-wrap"}})

(defrules whitespace)

;; https://tailwindcss.com/docs/word-break/#app

(def word-break
  {:break-normal  {:overflow-wrap "normal" :word-break "normal"}
   :break-words  {:overflow-wrap "break-word"}
   :break-all {:word-break "break-all"}
   :truncate  {:overflow "hidden" :text-overflow "ellipsis" :white-space "nowrap"}})

(defrules word-break)
