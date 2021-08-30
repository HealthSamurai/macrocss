(ns app.intro
  (:require [app.pages]))


(defn about []
  [:div "About "])

(app.pages/reg-page :about "About" about 0)


(defn inst []
  [:div "Inst "])

(app.pages/reg-page :inst "Installation" inst 1)
