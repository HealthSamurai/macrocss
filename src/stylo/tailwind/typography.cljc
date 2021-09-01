(ns stylo.tailwind.typography
  (:require
    [stylo.rule :refer [rule]]
    [stylo.tailwind.color :refer [colors]]
    [stylo.util :refer [with-alpha as-unit]]))


;; https://tailwindcss.com/docs/font-family/#app
(defmethod rule :font-sans [_] [[:& {:font-family "system-ui, -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, \"Noto Sans\", sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Noto Color Emoji\""}]])
(defmethod rule :font-serif [_] [[:& {:font-family "Georgia, Cambria, \"Times New Roman\", Times, serif"}]])
(defmethod rule :font-mono [_] [[:& {:font-family "Menlo, Monaco, Consolas, \"Liberation Mono\", \"Courier New\", monospace"}]])


;; https://tailwindcss.com/docs/font-weight/#app
(defmethod rule :font-hairline [_] [[:& {:font-weight 100}]])
(defmethod rule :font-thin [_] [[:& {:font-weight 200}]])
(defmethod rule :font-light [_] [[:& {:font-weight 300}]])
(defmethod rule :font-normal [_] [[:& {:font-weight 400}]])
(defmethod rule :font-medium [_] [[:& {:font-weight 500}]])
(defmethod rule :font-semibold [_] [[:& {:font-weight 600}]])
(defmethod rule :font-bold [_] [[:& {:font-weight 700}]])
(defmethod rule :font-extrabold [_] [[:& {:font-weight 800}]])
(defmethod rule :font-black [_] [[:& {:font-weight 900}]])


;; https://tailwindcss.com/docs/font-size/#app
(defmethod rule :text-xs [_] [[:& {:font-size ".75rem"}]])
(defmethod rule :text-sm [_] [[:& {:font-size ".875rem"}]])
(defmethod rule :text-base [_] [[:& {:font-size "1rem"}]])
(defmethod rule :text-lg [_] [[:& {:font-size "1.125rem"}]])
(defmethod rule :text-xl [_] [[:& {:font-size "1.25rem"}]])
(defmethod rule :text-2xl [_] [[:& {:font-size "1.5rem"}]])
(defmethod rule :text-3xl [_] [[:& {:font-size "1.875rem"}]])
(defmethod rule :text-4xl [_] [[:& {:font-size "2.25rem"}]])
(defmethod rule :text-5xl [_] [[:& {:font-size "3rem"}]])
(defmethod rule :text-6xl [_] [[:& {:font-size "4rem"}]])


;; https://tailwindcss.com/docs/font-smoothing/#app
(defmethod rule :antialiased [_] [[:& {:-webkit-font-smoothing "antialiased" :-moz-osx-font-smoothing "grayscale"}]])
(defmethod rule :subpixel-antialiased [_] [[:& {:-webkit-font-smoothing "auto" :-moz-osx-font-smoothing "auto"}]])


;; https://tailwindcss.com/docs/font-style/#app
(defmethod rule :italic [_] [[:& {:font-style "italic"}]])
(defmethod rule :not-italic [_] [[:& {:font-style "normal"}]])


;; https://tailwindcss.com/docs/letter-spacing/#app
(defmethod rule :tracking-tighter [_] [[:& {:letter-spacing "-0.05em"}]])
(defmethod rule :tracking-tight [_] [[:& {:letter-spacing "-0.025em"}]])
(defmethod rule :tracking-normal [_] [[:& {:letter-spacing "0"}]])
(defmethod rule :tracking-wide [_] [[:& {:letter-spacing "0.025em"}]])
(defmethod rule :tracking-wider [_] [[:& {:letter-spacing "0.05em"}]])
(defmethod rule :tracking-widest [_] [[:& {:letter-spacing "0.1em"}]])


;; https://tailwindcss.com/docs/line-height/#app
(defmethod rule :leading-none [_] [[:& {:line-height 1}]])
(defmethod rule :leading-tight [_] [[:& {:line-height 1.25}]])
(defmethod rule :leading-snug [_] [[:& {:line-height 1.375}]])
(defmethod rule :leading-normal [_] [[:& {:line-height 1.5}]])
(defmethod rule :leading-relaxed [_] [[:& {:line-height 1.625}]])
(defmethod rule :leading-loose [_] [[:& {:line-height 2}]])
(defmethod rule :leading [_ x] [[:& {:line-height (as-unit x)}]])


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
             
