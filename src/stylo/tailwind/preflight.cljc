(ns stylo.tailwind.preflight
  (:require [stylo.tailwind.color :refer [colors]]))


;; Copied from https://github.com/tailwindcss/tailwindcss/blob/master/src/plugins/css/preflight.css
(def preflight
  [[:blockquote
    :dl
    :dd
    :h1
    :h2
    :h3
    :h4
    :h5
    :h6
    :hr
    :figure
    :p
    :pre {:margin 0}]

   [:button {:background-color "transparent"
             :background-image "none"
             :padding          0}]

   [:button:focus {:outline "1px dotted"} {:outline "5px auto -webkit-focus-ring-color"}]

   [:fieldset {:margin  0
               :padding 0}]

   [:ol :ul {:list-style "none"
             :margin     0
             :padding    0}]

   [:html {:font-family "system-ui,-apple-system,BlinkMacSystemFont,\"Segoe UI\",Roboto,\"Helvetica Neue\",Arial,\"Noto Sans\",sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\",\"Segoe UI Symbol\",\"Noto Color Emoji\"" ; 1
           :line-height 1.5}]

   [:*
    "::before"
    "::after" {:box-sizing   "border-box"
               :border-width 0
               :border-style "solid"
               :border-color (colors :gray-300)}]

   [:hr {:border-top-width "1px"}]


   [:img {:border-style "solid"}]
   [:textarea {:resize "vertical"}]

   ["input::placeholder"
    "textarea::placeholder" {:color "#a0aec0"}]

   [:button
    "[role=\"button\"]" {:cursor "pointer"}]

   [:table {:border-collapse "collapse"}]

   [:h1
    :h2
    :h3
    :h4
    :h5
    :h6 {:font-size   "inherit"
         :font-weight "inherit"}]

   [:a {:color "inherit"
        :text-decoration "inherit"}]

   [:button
    :input
    :optgroup
    :select
    :textarea {:padding     0
               :line-height "inherit"
               :color       "inherit"}]

   [:pre
    :code
    :kbd
    :samp {:font-family "Menlo,Monaco,Consolas,\"Liberation Mono\",\"Courier New\",monospace"}]

   [:img
    :svg
    :video
    :canvas
    :audio
    :iframe
    :embed
    :object {:display "block"
             :vertical-align "middle"}]

   [:img
    :video {:max-width "100%"
            :height "auto"}]])


(comment
  (garden.core/css
    [[:div {:color "red"}]
     [:a {:color "blue"}]])

  (print
    (garden.core/css
      preflight)))

