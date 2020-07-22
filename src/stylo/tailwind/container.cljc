(ns stylo.tailwind.container
  (:require
    [stylo.rule :refer [rule]]
    [stylo.tailwind.color :refer [colors]]
    [stylo.util :refer [with-alpha]]))


(defmethod rule :container
  ([_] [[:& {}]])
  ([_ x] [[:& {}]]))

