(ns app.intro
  (:require [app.pages]
            [components.tailwind.accessibility :as a]
            [components.default :as d]
            [components.tailwind.typography :as t]))

(app.pages/reg-page :introduction nil 0)

(app.pages/reg-page :about d/about 1)

(app.pages/reg-page :installation  d/installation 2)

(app.pages/reg-page :basic-syntax d/basic-syntax 3)

(app.pages/reg-page :documentation nil 4)

(app.pages/reg-page :accessibility a/accessibility 5)

(app.pages/reg-page :typography t/typography  6)
