(ns stylo.util
  (:refer-clojure :exclude [format])
  (:require
   [garden.color :refer [hex->rgb]]
   [garden.units :as units]
   #?(:cljs [goog.string :as gstring])
   #?(:cljs [goog.string.format])
   #?(:clj [garden.def]))
  #?(:cljs
     (:require-macros [garden.def])))


(defn format
  [fmt & args]
  #?(:clj (apply clojure.core/format fmt args)
     :cljs (apply gstring/format fmt args)))


(defn with-alpha
  [color variable]
  (if-let [{:keys [red green blue]} (hex->rgb color)]
    (format "rgba(%d,%d,%d,var(%s))"
            red green blue (name variable))
    color))


(defn str-ratio?
  [s]
  (and (string? s)
       (re-matches #"(-?\d+)/(\d+)" s)))


(defn parse-str-ratio
  [s]
  (let [[_ n d] (re-matches #"(-?\d+)/(\d+)" s)]
    #?(:clj (/ (Double/parseDouble n) (Double/parseDouble d))
       :cljs (/ (js/parseFloat n) (js/parseFloat d)))))


(defn as-unit
  ([v]
   (as-unit v :rem))
  ([v preferred-unit]
   (cond
     (units/unit? v) v
     (str-ratio? v) (units/percent (* 100 (parse-str-ratio v)))
     (string? v) v
     :else (case preferred-unit
             :ms (units/ms v)
             :px (units/px v)
             :deg (units/deg v)
             :rem (units/rem (* v 0.25))
             :percent (units/percent v)))))


(comment
  (garden.compiler/render-css (units/percent 42))
  (garden.compiler/render-css (as-unit "3/4")))
