(ns stylo.core
  (:require
   [garden.core]
   [garden.stylesheet]
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
(def media-styles (atom {}))

(def media {:screen {:screen true}
            :smartphone {:min-width "320px"}
            :ereader {:min-width "481px"}
            :p-tablets {:min-width "961px"}
            :l-tablets {:min-width "1025px"}
            :desktop {:min-width "1281px"}})

(defn media-query? [k]
  (-> media
      keys
      (->> (apply hash-set)
           k)))

(defn create-classname [rules]
  (->> rules
       hash
       (str ".c")))

(defn divide-rules [rules]
  (reduce (fn [acc r]
            (cond
              (keyword? r) (update acc :rules conj r)
              (-> r first media-query?) (update acc :media-rules conj r)
              :else (update acc :rules conj r)))
          {:rules []
           :media-rules []} rules))

(defn garden-readable [media-rules]
  (reduce (fn [acc [f s :as r]]
            (if (string? f)
              (conj acc [(keyword f) (second s)])
              (conj acc r))) [] media-rules))

(defn garden-media-query [class-name media-type rules]
  (garden.stylesheet/at-media
     media-type
     [[class-name (-> rules
                      rest
                      join-rules
                      garden-readable)]]))

(defn create-media-rules [class-name rules]
  (when-let [media-type (->> rules ffirst media)]
      (swap! media-styles assoc class-name
             (garden-media-query class-name media-type (first rules)))))

(defn create-rules [rules]
  (when rules
    (let [class-name (-> rules create-classname keyword)]
      (swap! styles assoc
             class-name
             (join-rules rules))
      class-name)))

(defmacro c
  [& rs]
  (let [{:keys [media-rules rules]} (divide-rules rs)
        class-name (create-rules rules)
        _ (create-media-rules class-name media-rules)]
    (->> class-name
         str
         (drop 2)
         str/join)))

(defmacro c? [& rules]
  (->> rules
       divide-rules
       :rules
       join-rules
       (into [(keyword (str ".c" (hash rules)))])
       garden.core/css
       boolean))

(defn prettify [s]
  (->> s
       (#(str/replace % #"\n" ""))
       (#(str/replace % #"\s{2,}" " "))
       (reduce (fn [acc v]
                 (cond (or (= \{ v)
                           (= \} v)) (conj acc v \newline)
                       (= \@ v) (conj acc \newline \newline v)
                       :else (conj acc v)))
               [])
       str/join))

(defn css-media-styles
  ([]
   (css-media-styles @media-styles))
  ([media-styles]
   (-> media-styles
        vals
        garden.core/css
        prettify)))

(defn css-rules
  ([] (css-rules @styles))
  ([styles]
   (garden.core/css
    (concat stylo.tailwind.preflight/preflight
            (map (fn [[k v]] (into [k] v)) styles)))))

(defn get-styles
  []
  (str (css-rules)
       (css-media-styles)))

(defmacro mount-style
  []
  `(aset (or (.getElementById js/document "stylo")
             (let [style# (.createElement js/document "style")]
               (.setAttribute style# "id" "stylo")
               (.appendChild js/document.head style#)
               style#))
         "innerHTML" ~(get-styles)))

(defn compile-styles
  [styles media-styles]
  (str (css-rules styles)
       (css-media-styles media-styles)))

(comment
  (reset! styles {})
  (c [:text :blue-300]
     [:smartphone [:text :blue-500]])
  @styles
  @media-styles
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
