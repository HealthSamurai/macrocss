(ns app.intro
  (:require [app.pages]))


(defn about []
  [:div "About "])

(app.pages/reg-page :about about 0)


(defn inst []
  [:div "Installation "])

(app.pages/reg-page :installation  inst 1)
