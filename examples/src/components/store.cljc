(ns components.store
  (:require [components.default :as d]
            [components.tailwind.accessibility :refer [accessibility]]))

(def components {:about (d/about)
                 :installation (d/installation)
                 :documentation (d/documentation)
                 :accessibility (accessibility)})
