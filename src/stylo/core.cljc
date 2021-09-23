(ns stylo.core
  (:require
   [garden.core]
   [garden.stylesheet]
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
   [stylo.tailwind.variant]
   [stylo.util :as u])
  #?(:cljs (:require-macros [stylo.core])))

(defonce styles (atom {}))
(def media-styles (atom []))

(def media {:screen {:screen true}
            :smartphone {:min-width "320px"}
            :ereader {:min-width "481px"}
            :p-tablets {:min-width "961px"}
            :l-tablets {:min-width "1025px"}
            :desktop {:min-width "1281px"}})

(defn garden-readable [media-rules]
  (reduce (fn [acc [f s :as r]]
            (if (string? f)
              (conj acc [(keyword f) (second s)])
              (conj acc r))) [] media-rules))

(defn media-query [media-specs class-name rules]
  (garden.stylesheet/at-media
   media-specs
   [[class-name (-> rules
                    join-rules
                    garden-readable)]]))

(defn defmediarules
  [media]
  (doseq [[k v] media]
    (defmethod rule k [_ & rules]
      (fn [class-name]
        (media-query v class-name rules)))))

(defmediarules media)

(defn media-rule?
  ([k]
   (media-rule? k media))
  ([k m]
   (when (keyword? k)
     (-> m
         keys
         (->> (apply hash-set)
              k)))))

(defn create-classname [rules]
  (->> rules
       hash
       (str ".c")))

(defn divide-rules [rules]
  (reduce (fn [acc r]
            (cond
              (keyword? r) (update acc :rules conj r)
              (-> r first media-rule?) (update acc :media-rules conj r)
              :else (update acc :rules conj r)))
          {:rules []
           :media-rules []} rules))

(defn create-media-rules [class-name media-rules]
  (->> media-rules
       (mapv (partial apply rule))
       (mapv (fn [f] (f class-name)))
       (mapv (fn [x] (swap! media-styles conj x)))))

(defn create-rules [rules]
  (when-not (empty? rules)
    (let [class-name (-> rules create-classname keyword)]
      (swap! styles assoc
             class-name
             (join-rules rules))
      class-name)))

(defmacro c
  [& rs]
  (let [{:keys [media-rules rules]} (divide-rules rs)
        class-name (or (create-rules rules)
                       (create-classname media-rules))
        _ (create-media-rules class-name media-rules)]
    (->> class-name
         str
         (drop 2)
         str/join)))

(defmacro c? [& rs]
  (let [{:keys [rules media-rules]} (divide-rules rs)
        class-name (if-not (empty? rules)
                     (create-classname rules)
                     (create-classname media-rules))

        compute-rules (fn [r] (->> r
                                 join-rules
                                 (into [class-name])
                                 garden.core/css
                                 boolean))
        compute-media-rules (fn [m] (->> m
                                         (mapv (partial apply rule))
                                         (mapv (fn [f] (f class-name)))
                                         garden.core/css
                                         boolean))]
    (cond
      (and (empty? media-rules)
           (empty? rules)) true
      (empty? media-rules) (compute-rules rules)
      (empty? rules) (compute-media-rules media-rules)
      :else (and (compute-rules rules)
                 (compute-media-rules media-rules)))))

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
  (reset! media-styles [])
  @styles
  @media-styles
  (c? [:text :blue-300]
      [:smartphone [:text :blue-500]])
  (c? [:smartphone [:text :blue-500] {:font-weight "500"}]
      [:screen [:text :pink-200] {:font-weight "300"}])
  (c? [:smartphone [:bg :red-500] [[:.some-arbitrary-class {:bg :blue-400}]]])
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
