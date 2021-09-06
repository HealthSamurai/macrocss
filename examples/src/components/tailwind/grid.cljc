(ns components.tailwind.grid
  (:require [components.hiccup :as h]
            [stylo.tailwind.grid :as g]))

(defn grid []
  [:div
   (h/example-block "Grid"
                    "Utilities for creating grid"
                    g/grid)
   (h/example-block "Gap"
                    "Utilities for controlling gutters between grid and flexbox items."

                    {:gap {:gap {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :gap-px {:gap {:unit :px, :magnitude 1}},
                     :row-gap {:row-gap {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :row-gap-px {:row-gap {:unit :px, :magnitude 1}},
                     :col-gap {:column-gap {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :col-gap-px {:column-gap {:unit :px, :magnitude 1}}})

   (h/example-block "Grid columns"
                    "Utilities for controlling grid columns"

                    {:grid-cols-none {:grid-template-columns "none"},
                     :grid-cols {:grid-template-columns "repeat(YOUR_INT, minmax(0, 1fr))"},
                     :col-auto {:grid-column "auto"},
                     :col-span {:grid-column "span YOUR_INT / span YOUR_INT"},
                     :col-start {:grid-column-start "YOUR_INT"},
                     :col-start-auto {:grid-column-start "auto"},
                     :col-end {:grid-column-end "YOUR_INT"},
                     :col-end-auto {:grid-column-end "auto"}})
   (h/example-block "Grid rows"
                    "Utilities for controlling grid rows"
                    {:grid-rows-none {:grid-template-rows "none"},
                     :grid-rows {:grid-template-rows "repeat(YOUR_INT, minmax(0, 1fr))"},
                     :row-auto {:grid-row "auto"},
                     :row-span {:grid-row "span YOUR_INT / span YOUR_INT"},
                     :row-start {:grid-row-start "YOUR_INT"},
                     :row-start-auto {:grid-row-start "auto"},
                     :row-end {:grid-row-end "YOUR_INT"},
                     :row-end-auto {:grid-row-end "auto"}})
   (h/example-block "Grid flow"
                    "Utilities for controlling the order items in flexbox appear"
                    g/grid-flow)
   (h/navigation-arrows [:flex :interactivity])])
