(ns stylo.tailwind.interactivity
  (:require
    [stylo.rule :refer [rule]]))


;; https://tailwindcss.com/docs/appearance/#app
(defmethod rule :appearance-none [_] [[:& {:appearance "none" :-webkit-appearance "none"}]])


;; https://tailwindcss.com/docs/cursor/#app
(defmethod rule :cursor-auto [_] [[:& {:cursor "auto"}]])
(defmethod rule :cursor-default [_] [[:& {:cursor "default"}]])
(defmethod rule :cursor-pointer [_] [[:& {:cursor "pointer"}]])
(defmethod rule :cursor-wait [_] [[:& {:cursor "wait"}]])
(defmethod rule :cursor-text [_] [[:& {:cursor "text"}]])
(defmethod rule :cursor-move [_] [[:& {:cursor "move"}]])
(defmethod rule :cursor-not-allowed [_] [[:& {:cursor "not-allowed"}]])


;; https://tailwindcss.com/docs/outline/#app
(defmethod rule :outline-none [_] [[:& {:outline 0}]])


;; https://tailwindcss.com/docs/pointer-events/#app
(defmethod rule :pointer-events-none [_] [[:& {:pointer-events "none"}]])
(defmethod rule :pointer-events-auto [_] [[:& {:pointer-events "auto"}]])


;; https://tailwindcss.com/docs/resize/#app
(defmethod rule :resize [_] [[:& {:resize "both"}]])
(defmethod rule :resize-none [_] [[:& {:resize "none"}]])
(defmethod rule :resize-x [_] [[:& {:resize "horizontal"}]])
(defmethod rule :resize-y [_] [[:& {:resize "vertical"}]])



;; https://tailwindcss.com/docs/user-select/#app
(defmethod rule :select-none [_] [[:& {:user-select "none"}]])
(defmethod rule :select-text [_] [[:& {:user-select "text"}]])
(defmethod rule :select-all [_] [[:& {:user-select "all"}]])
(defmethod rule :select-auto [_] [[:& {:user-select "auto"}]])
