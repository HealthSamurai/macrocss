(ns stylo.tailwind.variant
  (:require
    [stylo.rule :refer [rule join-rules]]))


(defmethod rule :pseudo
  [_ k & rules]
  [(into [(str "&" k) {}] (join-rules rules))])


(defmethod rule :odd
  [_ & rules]
  (apply rule :pseudo ":nth-child(odd)" rules))


(defmethod rule :even
  [_ & rules]
  (apply rule :pseudo ":nth-child(even)" rules))


(def pseudo-classes
  [:active
   :any-link
   :blank
   :checked
   :default
   :defined
   :disabled
   :empty
   :enabled
   :first
   :first-child
   :first-of-type
   :focus
   :focus-visible
   :focus-within
   :fullscreen
   :hover
   :in-range
   :indeterminate
   :invalid
   :last-child
   :last-of-type
   ;;:left
   :link
   :only-child
   :only-of-type
   :optional
   :out-of-range
   :placeholder-shown
   :read-only
   :read-write
   :required
   ;;:right
   :root
   :scope
   :target
   :valid
   :visited])

(doseq [c pseudo-classes]
  (defmethod rule c
    [_ & rules]
    (apply rule :pseudo (str c) rules)))

(def pseudo-elements
  {:browse                "::-ms-browse"
   :check                 "::-ms-check"
   :clear                 "::-ms-clear"
   :expand                "::-ms-expand"
   :fill                  "::-ms-fill"
   :fill-lower            "::-ms-fill-lower"
   :fill-upper            "::-ms-fill-upper"
   :progress-bar          #{"::-moz-progress-bar" "::-webkit-progress-bar"}
   :progress-value        "::-webkit-progress-value"
   :range-progress        "::-moz-range-progress"
   :range-thumb           "::-moz-range-thumb"
   :range-track           "::-moz-range-track"
   :reveal                "::-ms-reveal"
   :slider-runnable-track "::-webkit-slider-runnable-track"
   :slider-thumb          "::-webkit-slider-thumb"
   :thumb                 "::-ms-thumb"
   :ticks-after           "::-ms-ticks-after"
   :ticks-before          "::-ms-ticks-before"
   :tooltip               "::-ms-tooltip"
   :track                 "::-ms-track"
   :value                 "::-ms-value"
   :after                 "::after"
   :backdrop              "::backdrop"
   :before                "::before"
   :cue                   "::cue"
   :cue-region            "::cue-region"
   :first-letter          "::first-letter"
   :first-line            "::first-line"
   :grammar-error         "::grammar-error"
   :marker                "::marker"
   :placeholder           "::placeholder"
   :selection             "::selection"
   :spelling-error        "::spelling-error"})


(doseq [[k v] pseudo-elements]
  (defmethod rule k
    [_ & rules]
    (->> (if (set? v) v #{v})
         (mapcat #(apply rule :pseudo % rules)))))


;; These parameterized pseudo-classes and pseudo-elements are not
;; supported. Use :pseudo rule to use them:
;;
;;   [:pseudo ":is(header, main, footer)" [:text :red-500]]
;;
;; :dir()
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
;; ::slotted()
