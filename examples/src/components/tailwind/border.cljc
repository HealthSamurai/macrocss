(ns components.tailwind.border
  (:require [components.hiccup :refer [h1 p1 example-block
                                       block navigation-arrows] :as h]
            [stylo.core :refer [c]]
            [stylo.tailwind.border :as b]))


(def borders-default {:rounded (b/rounded nil :border-radius)
                      :rounded-t (b/rounded nil :border-top-left-radius :border-top-right-radius)
                      :rounded-r (b/rounded nil :border-top-right-radius :border-bottom-right-radius)
                      :rounded-b (b/rounded nil :border-bottom-right-radius :border-bottom-left-radius)
                      :rounded-l (b/rounded nil :border-top-left-radius :border-bottom-left-radius)
                      :rounded-tl (b/rounded nil :border-top-left-radius)
                      :rounded-tr (b/rounded nil :border-top-left-radius)
                      :rounded-br (b/rounded nil :border-bottom-right-radius)
                      :border {:border-width {:unit :px, :magnitude 1}}
                      :border-opacity  {:--border-opacity {:unit :%
                                                           :magnitude "ENTER INTEGER, MEANS PERCENT"}}
                      :divide {:border-color "HEX CODE OR PREDEFINED COLOR KEY",
                               :--divide-opacity 1}
                      :divide-x
                      {:--divide-x-reverse 0,
                       :border-right-width "calc(1px * var(--divide-x-reverse))",
                       :border-left-width "calc(1px * calc(1 - var(--divide-x-reverse)))"}

                      :divide-y  {:--divide-y-reverse 0,
                                  :border-top-width "calc(1px * calc(1 - var(--divide-y-reverse)))",
                                  :border-bottom-width "calc(1px * var(--divide-y-reverse))"}})

(defn borders []
  [:div
   (block (h1 "Borders")
               (p1 "Styling borders. Adjusting size, radius, color and other border properties."))
   (example-block "Rounded. Border. Divide."
                  "Default values for border styling. All of them can accept custom value as arguments."
                  borders-default)
   (navigation-arrows [:background :color])])
