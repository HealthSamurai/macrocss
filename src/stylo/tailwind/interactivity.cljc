(ns stylo.tailwind.interactivity
  (:require
   [stylo.rule :refer [defrules]]))


;; https://tailwindcss.com/docs/appearance/#app


(def appearance {:appearance-none   {:appearance "none" :-webkit-appearance "none"}})

(defrules appearance)

;; https://tailwindcss.com/docs/cursor/#app

(def cursor  {:cursor-auto   {:cursor "auto"}
              :cursor-default   {:cursor "default"}
              :cursor-pointer   {:cursor "pointer"}
              :cursor-wait   {:cursor "wait"}
              :cursor-text   {:cursor "text"}
              :cursor-move   {:cursor "move"}
              :cursor-not-allowed   {:cursor "not-allowed"}})

(defrules cursor)

;; https://tailwindcss.com/docs/outline/#app

(def outline {:outline-none   {:outline 0}})

(defrules outline)

;; https://tailwindcss.com/docs/pointer-events/#app
(def pointer-events {:pointer-events-none   {:pointer-events "none"}
                     :pointer-events-auto   {:pointer-events "auto"}})

(defrules pointer-events)

;; https://tailwindcss.com/docs/resize/#app
(def resize {:resize   {:resize "both"}
             :resize-none   {:resize "none"}
             :resize-x   {:resize "horizontal"}
             :resize-y   {:resize "vertical"}})

(defrules resize)

;; https://tailwindcss.com/docs/user-select/#app
(def user-select {:select-none   {:user-select "none"}
                  :select-text   {:user-select "text"}
                  :select-all   {:user-select "all"}
                  :select-auto   {:user-select "auto"}})

(defrules user-select)
