(ns components.tailwind.pseudo
  (:require [components.hiccup :as h]
            [stylo.tailwind.variant :as p]
            [components.linter :as l]))

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
    (h/code :clojure
            (h/comment-code ";; These parameterized pseudo-classes and pseudo-elements are not \n;; supported. Use :pseudo rule to use them:\n \n")
    (l/highlight "(c [:pseudo \":is(header, main, footer)\" [:text :red-500]]) \n\n")
    (h/comment-code ";; :dir()
;; :has()
;; :host()
;; :host-context()
;; :is() (:matches(), :any())
;; :lang()
;; :not()
;; :nth-child()
;; :nth-last-child()
;; :nth-last-of-type()
;; :nth-of-type()
;; :where()
;; ::after (:after)
;; ::before (:before)
;; ::first-letter (:first-letter)
;; ::first-line (:first-line)
;; ::part()
;; ::slotted() \n ")))

   (h/navigation-arrows [:transition
                         :accessibility])])
