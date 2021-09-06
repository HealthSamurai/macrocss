(ns stylo.tailwind.background
  (:require
   [stylo.rule :refer [defrules]]
   [stylo.tailwind.color :refer [colors]]
   [stylo.util :refer [with-alpha as-unit]]))


;; https://tailwindcss.com/docs/background-attachment/#app


(def background-attachment {:bg-fixed  {:background-attachment "fixed"}
                            :bg-local    {:background-attachment "local"}
                            :bg-scroll    {:background-attachment "scroll"}})

(defrules background-attachment)

;; https://tailwindcss.com/docs/background-color/#app
(def background-color-opacity
  {:bg (fn [x] {:background-color (with-alpha (colors x)
                                    :--bg-opacity) :--bg-opacity 1})

   :bg-opacity   (fn [x] {:--bg-opacity (as-unit x :percent)})})

(defrules background-color-opacity)
;; https://tailwindcss.com/docs/background-position/#app


(def background-position {:bg-center    {:background-position "50%"}
                          :bg-bottom    {:background-position "50% 100%"}
                          :bg-left    {:background-position "0"}
                          :bg-left-bottom    {:background-position "0 100%"}
                          :bg-left-top    {:background-position "0 0"}
                          :bg-right    {:background-position "100%"}
                          :bg-right-bottom    {:background-position "100% 100%"}
                          :bg-right-top    {:background-position "100% 0"}
                          :bg-top    {:background-position "top"}

                          :bg-repeat    {:background-repeat "repeat"}
                          :bg-no-repeat    {:background-repeat "no-repeat"}
                          :bg-repeat-x    {:background-repeat "repeat-x"}
                          :bg-repeat-y    {:background-repeat "repeat-y"}
                          :bg-repeat-round    {:background-repeat "round"}
                          :bg-repeat-space    {:background-repeat "space"}})

(defrules background-position)

;; https://tailwindcss.com/docs/background-size/#app
(def background-size {:bg-auto    {:background-size "auto"}
                      :bg-cover    {:background-size "cover"}
                      :bg-contain    {:background-size "contain"}})

(defrules background-size)
