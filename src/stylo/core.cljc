(ns stylo.core
  (:require
    [garden.core]
    [clojure.string :as str]
    [stylo.rule :refer [join-rules]]
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
    [stylo.tailwind.variant]
    [stylo.util :as u])
  #?(:cljs (:require-macros [stylo.core])))

(defonce styles (atom {}))

(defmacro c
  [& rules]
  (when rules
    (let [class (if-let [ns-name (get-in &env [:ns :name])]
                  (u/format "%s-%s-%s"
                            (str/replace ns-name #"\." "_")
                            (:line &env)
                            (:column &env))
                  (str "c" (hash rules)))]
      (swap! styles assoc
             (keyword (str "." class))
             (with-meta (join-rules rules)
               {:location [(:name (:ns &env))
                           (:line &env)
                           (:column &env)]}))
      (keyword class))))

(defmacro c? [& rules]
  (->> rules
       join-rules
       (into [(keyword (str ".c" (hash rules)))])
       garden.core/css
       boolean))

(defn get-styles []
  (garden.core/css
    (concat
      stylo.tailwind.preflight/preflight
      (->> @styles
           (sort-by (comp :location meta val))
           (map (fn [[k v]] (into [k] v)))))))

(defmacro mount-style
  []
  `(aset (or (.getElementById js/document "stylo")
             (let [style# (.createElement js/document "style")]
               (.setAttribute style# "id" "stylo")
               (.appendChild js/document.head style#)
               style#))
         "innerHTML" ~(get-styles)))


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
