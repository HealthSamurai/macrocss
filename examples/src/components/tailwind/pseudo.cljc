(ns components.tailwind.pseudo
  (:require [components.hiccup :as h]
            [stylo.tailwind.variant :as p]))

(defn pseudo []
  [:div
   (h/example-block "Pseudo classes"
                    "List of supported pseudo classes"
                    (assoc {} :pseudo p/pseudo-classes))
   (h/example-block "Pseudo elements"
                    "List of supported pseudo elements"
                    (assoc {} :pseudo (update p/pseudo-elements :progress-bar str)))
   (h/block
    (h/h1 "Some important notes")
    (h/p1 "Instructions for proper usage of some pseudo-clsses or pseudo-elements")
    (h/code ";; These parameterized pseudo-classes and pseudo-elements are not \n
;; supported. Use :pseudo rule to use them:\n
 [:pseudo \":is(header, main, footer)\" [:text :red-500]]\n
;; :dir() \n
;; :has() \n
;; :host() \n
;; :host-context() \n
;; :is() (:matches(), :any()) \n
;; :lang() \n
;; :not() \n
;; :nth-child() \n
;; :nth-last-child() \n
;; :nth-last-of-type() \n
;; :nth-of-type() \n
;; :where() \n
;; ::after (:after) \n
;; ::before (:before) \n
;; ::first-letter (:first-letter) \n
;; ::first-line (:first-line) \n
;; ::part() \n
;; ::slotted() \n "))

   (h/navigation-arrows [:transition
                         :accessibility])])
