(ns components.tailwind.layout
  (:require [components.hiccup :as h]
            [stylo.tailwind.layout :as l]))

(defn layout []
  [:div
   (h/example-block "Box sizing"
                    "Utilities for controlling how the browser should calculate an element's total size"
                    l/box-sizing)

   (h/example-block "Display"
                    "Utilities for controlling the display box type of an element"
                    l/display)
   (h/example-block "Floats"
                    "Utilities for controlling the wrapping of content around an element"
                    (merge l/css-floats l/floats-clearfix))
   (h/example-block "Clear"
                    "Utilities for controlling the wrapping of content around an element"
                    l/clear)
   (h/example-block "Object fit"
                    "Utilities for controlling how a replaced element's content should be resized"
                    l/object-fit)
   (h/example-block "Object position"
                    "Utilities for controlling how a replaced element's content should be positioned within its container"
                    l/object-position)
   (h/example-block "Overflow"
                    "Utilities for controlling how an element handles content that is too large for the container"
                    l/overflow)
   (h/example-block "Position"
                    "Utilities for controlling how an element is positioned in the DOM"
                    l/position)
   (h/example-block "Top/ Right / Bottom / Left"
                    "Utilities for controlling the placement of positioned elements."
                    {:right-auto {:right "auto"},
                     :inset
                     {:top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                      :right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                      :bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                      :left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :top-auto {:top "auto"},
                     :bottom {:bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :left-auto {:left "auto"},
                     :top {:top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :inset-x-auto {:left "auto", :right "auto"},
                     :inset-y-auto {:top "auto", :bottom "auto"},
                     :bottom-auto {:bottom "auto"},
                     :inset-y
                     {:top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}, :bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :right {:right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :inset-auto {:top "auto", :right "auto", :bottom "auto", :left "auto"},
                     :inset-x
                     {:left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}, :right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :left {:left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}}})
   (h/example-block "Visibility"
                    "Utilities for controlling the visibility of an element"
                    l/visibility)
   (h/example-block "Z-Index"
                    "Utilities for controlling the stack order of an element"
                    (assoc l/z-index :z {:z-index "YOUR_INT"}))
   (h/navigation-arrows [:interactivity :preflight])])
