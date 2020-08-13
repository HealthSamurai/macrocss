(ns stylo.core
  (:require
    [garden.core]
    [clojure.string :as str]
    [stylo.rule :refer [rule join-rules]]
    [stylo.tailwind.preflight]
    [stylo.tailwind.accessibility]
    [stylo.tailwind.background]
    [stylo.tailwind.border]
    [stylo.tailwind.effect]
    [stylo.tailwind.flex]
    [stylo.tailwind.grid]
    [stylo.tailwind.interactivity]
    [stylo.tailwind.layout]
    [stylo.tailwind.sizing]
    [stylo.tailwind.spacing]
    [stylo.tailwind.svg]
    [stylo.tailwind.table]
    [stylo.tailwind.transform]
    [stylo.tailwind.transition]
    [stylo.tailwind.typography]
    [stylo.tailwind.variant])
  #?(:cljs
     (:require-macros [stylo.core])))


(defonce styles (atom {}))


#?(:clj
   (defmacro c [& rules]
     (when rules
       (let [class (if (:name (:ns &env)) (str (str/replace (:name (:ns &env)) #"\." "_") "-" (:line &env) "-" (:column &env))
                                          (str "c" (hash rules)))]
         (swap! styles assoc
                (keyword (str "." class))
                (with-meta (join-rules rules) {:location [(:name (:ns &env)) (:line &env) (:column &env)]}))
         (keyword class)))))


#?(:clj
   (defmacro c? [& rules]
     (garden.core/css
       (into [(keyword (str ".c" (hash rules)))] (join-rules rules)))))


(defn get-styles
  ([]
   (get-styles {}))
  ([flags]
   (garden.core/css
    flags
    (concat
     stylo.tailwind.preflight/preflight
     (->> @styles
          (sort-by (comp :location meta val))
          (map (fn [[k v]] (into [k] v))))))))
           (map (fn [[k v]] (into [k] v)))))))


#?(:clj
   (defmacro mount-style
     []
     `(aset (or (.getElementById js/document "stylo")
                (let [style# (.createElement js/document "style")]
                  (.setAttribute style# "id" "stylo")
                  (.appendChild js/document.head style#)
                  style#))
            "innerHTML" ~(get-styles))))


(comment
  (reset! styles {})

  @styles

  (c? [:progress-bar [:bg :red-500]] {:font-weight "500"})

  (c? [:progress-bar [:bg :red-500]])
  (c? [:disabled [:hover [:bg :red-500]]])
  (c? [:bg :red-500] [[:.some-arbitrary-class {:bg :blue-400}]])
  (c? [:bg :red-500]
      [:hover [[:.some-arbitrary-class {:bg :blue-400}]]])
  (c? [:pseudo ":nth-child(2)" [:hover [:bg :red-500]]])

  (c? [[:& {:color "red"}]
       [:&:target {:color "green"}]])
  (c? {:color "red"})
  (c? [:hover [:placeholder :white] [:mb 1]])
  (c? [:p 1])
  (c? [:placeholder :white])
  (c? [:divide-x 2])
  (c? :sr-only))
