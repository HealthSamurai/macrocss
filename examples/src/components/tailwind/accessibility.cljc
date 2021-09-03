(ns components.tailwind.accessibility
  (:require [components.hiccup :refer [h1 h3
                                       p1 p3 a
                                       pre-bash code
                                       code-span
                                       block table
                                       navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.accessibility :as a]))

(defn accessibility []
  [:div
   (block
     (h1 "Screen Readers")
     (p1 "Utilities for improving accessibility with screen readers."))
    (h/table a/accessibility-data)
   (block (h3 "Usage")
          (p3 "Use " (code-span "'sr-only'") " to hide an element visually without hiding it from screen readers:")
          (code " [:a {:href \"#\"} \n   [:svg \"some.svg\"] \n   [:span {:class (c :sr-only)} \"Settings\"]]")
          (p3 "Use " (code-span "'not-sr-only'") " to undo sr-only, making an element visible to sighted users as well as screen readers.\n
 This can be useful when you want to visually hide something on small screens but show it on larger screens for example:")
          (code "[:a {:href \"#\"} \n  [:svg \"some.svg\"] \n  [:span {:class (c \"not-sr-only\")}]]")
          (p3 "By default, " (code-span "'responsive'") " and " (code-span "'focus'") " variants are generated for these utilities. You can use pseudo classes, such as focus
          to make an element visually hidden by default but visible when the user tabs to it — useful for “skip to content” links:")
          (code "[:a {:href \"#\" \n     :class (c :sr-only [:focus :not-sr-only])}\n \"Some content\"]")
          (navigation-arrows [:basic-syntax :typography]))])
