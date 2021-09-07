(ns app.intro
  (:require [app.pages]
            [components.tailwind.accessibility :as a]
            [components.default :as d]
            [components.tailwind.typography :as t]
            [components.tailwind.background :as b]
            [components.tailwind.border :as bd]
            [components.tailwind.color :as c]
            [components.tailwind.effect :as ef]
            [components.tailwind.flex :as f]
            [components.tailwind.grid :as g]
            [components.tailwind.interactivity :as i]
            [components.tailwind.layout :as l]
            [components.tailwind.preflight :as p]
            [components.tailwind.sizing :as s]
            [components.tailwind.spacing :as sp]
            [components.tailwind.svg :as svg]
            [components.tailwind.table :as tb]
            [components.tailwind.transition :as tr]
            [components.tailwind.variant :as v]))

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

(app.pages/reg-page :grid g/grid 12)

(app.pages/reg-page :interactivity i/interactivity 13)

(app.pages/reg-page :layout l/layout 14)

(app.pages/reg-page :preflight p/preflight 15)

(app.pages/reg-page :sizing s/sizing 16)

(app.pages/reg-page :spacing sp/spacing 17)

(app.pages/reg-page :svg svg/svg 18)

(app.pages/reg-page :table tb/table 19)
;(app.pages/reg-page :transition tr/transition 20)
;(app.pages/reg-page :variant v/variant 21)
