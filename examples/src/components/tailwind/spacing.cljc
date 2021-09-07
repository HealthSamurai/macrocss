(ns components.tailwind.spacing
  (:require [components.hiccup :as h]
            [stylo.tailwind.spacing :as s]))

(defn spacing []
  [:div (h/example-block "Padding"
                         "Utilities for setting padding of an element"
                         (merge {:pyx (h/units [:padding-top
                                                :padding-bottom
                                                :padding-left
                                                :padding-right])}
                                {:pb-auto {:padding-bottom "auto"},
                                 :pt {:padding-top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                                 :pt-px {:padding-top {:unit :px, :magnitude 1}},
                                 :px-px
                                 {:padding-left {:unit :px, :magnitude 1},
                                  :padding-right {:unit :px, :magnitude 1}},
                                 :pt-auto {:padding-top "auto"},
                                 :pb-px {:padding-bottom {:unit :px, :magnitude 1}},
                                 :px-auto {:padding-left "auto", :padding-right "auto"},
                                 :p-auto {:padding "auto"},
                                 :px
                                 {:padding-left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                                  :padding-right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                                 :pr-px {:padding-right {:unit :px, :magnitude 1}},
                                 :pl {:padding-left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                                 :pl-px {:padding-left {:unit :px, :magnitude 1}},
                                 :pr-auto {:padding-right "auto"},
                                 :py-px
                                 {:padding-top {:unit :px, :magnitude 1},
                                  :padding-bottom {:unit :px, :magnitude 1}},
                                 :p-px {:padding {:unit :px, :magnitude 1}},
                                 :pl-auto {:padding-left "auto"},
                                 :py-auto {:padding-top "auto", :padding-bottom "auto"},
                                 :pr {:padding-right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                                 :py
                                 {:padding-top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                                  :padding-bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                                 :p {:padding {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                                 :pb {:padding-bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}}}))
   (h/example-block "Margins"
                    "Utilities for setting margins of an element"
                    {:m-auto {:margin "auto"},
                     :mt-px {:margin-top {:unit :px, :magnitude 1}},
                     :mx-px
                     {:margin-left {:unit :px, :magnitude 1},
                      :margin-right {:unit :px, :magnitude 1}},
                     :m {:margin {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :mr {:margin-right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :mb-px {:margin-bottom {:unit :px, :magnitude 1}},
                     :my-px
                     {:margin-top {:unit :px, :magnitude 1},
                      :margin-bottom {:unit :px, :magnitude 1}},
                     :mb {:margin-bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :mr-px {:margin-right {:unit :px, :magnitude 1}},
                     :my-auto {:margin-top "auto", :margin-bottom "auto"},
                     :ml {:margin-left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :mt-auto {:margin-top "auto"},
                     :mx
                     {:margin-left {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                      :margin-right {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :m-px {:margin {:unit :px, :magnitude 1}},
                     :mx-auto {:margin-left "auto", :margin-right "auto"},
                     :ml-px {:margin-left {:unit :px, :magnitude 1}},
                     :mb-auto {:margin-bottom "auto"},
                     :ml-auto {:margin-left "auto"},
                     :my
                     {:margin-top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"},
                      :margin-bottom {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}},
                     :mr-auto {:margin-right "auto"},
                     :mt {:margin-top {:unit :rem, :magnitude "YOUR_INT * 0.25 REM"}}
                     :myx (h/units [:margin-top
                                    :margin-bottom
                                    :margin-right
                                    :margin-left])})
   (h/example-block "Space-X"
                    "Utilities for setting minimum width of an element"
                    {:space-x  {:used_combinator ">*+*"
                                :--space-x-reverse 0,
                                :margin-right "calc(YOUR_INT * 0.25 REM, DEFAULTS TO 1 * var(--space-x-reverse))",
                                :margin-left "calc(YOUR_INT * 0.25 REM, DEFAULTS TO 1 * calc(1 - var(--space-x-reverse)))"}
                     :space-x-reverse {::used_combinator "&>*+*"
                                       :--space-x-reverse 1}})
   (h/example-block "Space-Y"
                    "Utilities for setting height of an element"

                    {:space-y {:used_combinator "&>*+*"
                               :--space-y-reverse 0,
                               :margin-bottom "calc(YOUR_INT * 0.25 REM, DEFAULTS TO 1 * var(--space-y-reverse))",
                               :margin-top "calc(YOUR_INT * 0.25 REM, DEFAULTS TO 1 * calc(1 - var(--space-y-reverse)))"}
                     :space-y-reverse {:used_combinator "&>*+*"
                                       :--space-y-reverse 1}})
   (h/navigation-arrows [:sizing :svg])])
