(ns app.intro
  (:require [app.pages]
            [components.tailwind.accessibility :as a]
            [components.default :as d]
            [components.tailwind.typography :as t]
            [components.tailwind.background :as b]
            [components.tailwind.border :as bd]
            [components.tailwind.color :as c]
            [components.tailwind.effect :as ef]
            [components.tailwind.flex :as f]))

(app.pages/reg-page :introduction 0)

(app.pages/reg-page :about d/about 1)

(app.pages/reg-page :installation d/installation 2)

(app.pages/reg-page :basic-syntax d/basic-syntax 3)

(app.pages/reg-page :documentation 4)

(app.pages/reg-page :accessibility a/accessibility 5)

(app.pages/reg-page :typography t/typography 6)

(app.pages/reg-page :background b/background 7)

(app.pages/reg-page :borders bd/borders 8)

(app.pages/reg-page :colours c/color 9)

(app.pages/reg-page :effect ef/effect 10)

(app.pages/reg-page :flex f/flex 11)

;(app.pages/reg-page :grid g/grid)
;(app.pages/reg-page :interactivity i/interactivity)
;(app.paegs/reg-page :layout l/layout)
;(app.pages/reg-page :preflight p/preflight)
;(app.pages/reg-page :sizing s/sizing)
;(app.pages/reg-page :spacing sp/spacing)
;(app.pages/reg-page :svg svg/svg)
;(app.pages/reg-page :table tb/table)
;(app.pages/reg-page :transition tr/transition)
;(app.pages/reg-page :variant v/variant)
