(ns app.intro
  (:require [app.pages]
            [components.tailwind.accessibility :as a]
            [components.default :as d]))

(app.pages/reg-page :about d/about 0)

(app.pages/reg-page :installation  d/installation 1)

(app.pages/reg-page :accessibility a/accessibility 2)
