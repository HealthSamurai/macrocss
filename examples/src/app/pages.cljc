(ns app.pages)

(def pages {:default [:about :installation :documentation]
            :doc [:accessibility :background :border :color :container :effect :flex :grid
                  :interactivity :layout :preflight :sizing :spacing :svg :table :transform
                  :transition :typography :variant]})

(def all-pages (->> pages
                    vals
                    (apply concat)))
