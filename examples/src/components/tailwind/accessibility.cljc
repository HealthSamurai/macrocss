(ns components.tailwind.accessibility
  (:require [components.hiccup :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.accessibility :as a]))

(defn accessibility []
  [:div
   (h/block
     (h/h1 "Screen Readers")
     (h/p1 "Utilities for improving accessibility with screen readers.")
     (h/table a/accessibility-data)
     (h/h3 "Usage")
          (h/p3 "Use " (h/code-span "'sr-only'") " to hide an element visually without hiding it from screen readers:")
          (h/code :clojure  (h/lint " [:a {:href \"#\"} \n   [:svg \"some.svg\"] \n   [:span {:class (c :sr-only)} \"Settings\"]]"))
          (h/p3 "Use " (h/code-span "'not-sr-only'") " to undo sr-only, making an element visible to sighted users as well as screen readers.\n
 This can be useful when you want to visually hide something on small screens but show it on larger screens for example:")
          (h/code :clojure (h/lint "[:a {:href \"#\"} \n  [:svg \"some.svg\"] \n  [:span {:class (c \"not-sr-only\")}]]"))
          (h/p3 "By default, " (h/code-span "'responsive'") " and " (h/code-span "'focus'") " variants are generated for these utilities. You can use pseudo classes, such as focus
          to make an element visually hidden by default but visible when the user tabs to it — useful for “skip to content” links:")
          (h/navigation-arrows [:basic-syntax :typography]))])
