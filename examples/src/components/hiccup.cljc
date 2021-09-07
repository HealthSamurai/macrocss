(ns components.hiccup
  (:require [stylo.core :refer [c]]
            [clojure.string :as str]))

(defn gen-key [] (gensym "key-"))

(defn k [] {:key (gen-key)})

(defn with-key [m] (merge (k) m))

(defn pre-bash
  [& content]
  [:div
   {:class (c [:mt 1]
              [:bg :black]
              [:rounded 12]
              [:px 4]
              [:py 3]
              :w-auto)
    :key (gen-key)}
   [:pre {:class (c [:text :white]
                    :font-mono
                    :text-sm)
          :key (gen-key)} content]])

(defn lint [code-string]
  (reduce (fn [acc smbl]
              (conj acc (cond
                          (or (= \] smbl)
                              (= \[ smbl)) [:span {:class (c [:text :purple-500])
                                                   :key (gen-key)} smbl]

                          (or (= \} smbl)
                              (= \{ smbl)) [:span {:class (c [:text :blue-500])
                                                   :key (gen-key)} smbl]

                          (or (= \( smbl)
                              (= \) smbl)) [:span {:class (c [:text :indigo-500])
                                                   :key (gen-key)} smbl]

                          (or (= \< smbl)
                              (= \> smbl)) [:span {:class (c [:text :blue-900])
                                                   :key (gen-key)} smbl]

                          (= \= smbl)  [:span {:class (c [:text :orange-900])
                                               :key (gen-key)} smbl]

                          :else [:span {:class (c [:text :yellow-500])
                                        :key (gen-key)} smbl])))
            [:span] code-string))

(defn code [code-string]
  [:div
   {:class (c [:mt 2]
              [:mb 2]
              [:bg :black]
              [:rounded 12]
              [:px 4]
              [:py 3]
              :w-auto)
    :key (gen-key)}
   [:pre {:class (c :font-mono
                    :font-thin)
          :key (gen-key)} (lint code-string)]])

(defn block [& content]
  [:div {:class (c [:w 180] :content-center [:mt 8])
         :key (gen-key)}
   [:div
    {:class (c :box-border [:pb 10] [:mb 10] [:border-b :gray-200])
     :key (gen-key)}
    content]])

(defn h1 [& content]
  [:h1 {:class (c
                :text-3xl
                :inline-block
                :font-extrabold
                [:text :gray-900]
                :tracking-tight)
        :key (gen-key)} content])

(defn p1 [& content]
  [:p {:class (c [:mt 1] :font-sans :text-lg :font-light [:text :gray-600])
       :key (gen-key)} content])

(defn h3 [& content]
  [:h3 {:class (c [:m 1]
                  :text-xl
                  :inline-block
                  :font-bold
                  [:text :gray-900]
                  :tracking-tight)
        :key (gen-key)} content])

(defn p3 [& content]
  [:p {:class (c [:mt 1] :font-sans :font-light :text-base [:text :gray-800])
       :key (gen-key)} content])

(defn hash-link? [link]
  (= "/" (-> link
             first
             str)))

(defn href [path]
  (str "#" path))

(defn create-link [link]
  (if (hash-link? link)
    (href link)
    link))

(defn a
  ([link] (a link link))
  ([link description]
   [:a {:href (create-link link)
        :class (c [:text :blue-300] :underline)
        :key (gen-key)}
    description]))

(defn create-table-heading [keyseq]
  [:thead {:class (c :border-b)
           :key (gen-key)}
   (conj
    (reduce (fn [acc v] (conj acc [:th {:class (c :font-semibold
                                                  :font-sans
                                                  [:text :gray-600]
                                                  :text-sm
                                                  [:pb 2])
                                        :key (gen-key)}
                                   [:div {:class (c :flex
                                                    :content-start)
                                          :key (gen-key)} v]]))

            [:tr] keyseq))])

(defn create-left-cell [k]
  [:td {:class (c :font-mono :text-xs
                  [:text :purple-600]
                  :whitespace-no-wrap)
        :key (gen-key)} k])

(defn cell-logic [cell]
  [:p (cond
        (string? cell) cell
        (keyword? cell) (str cell)
        :else
        (let [[k v] cell] (-> k
                           name
                           (str ":")
                           (str "  '" v "'; "))))])

(defn create-right-cell [cell]
  [:td {:class (c :font-mono :text-xs
                  [:text :blue-300])}
   (reduce (fn [acc c] (conj acc (cell-logic c)))
           [:div]
           cell)])

(defn create-table-cells [rule-data]
  (reduce (fn [acc [k v]]
            (conj acc [:tr (create-left-cell k)
                       (create-right-cell v)]
                  [:tr {:class (c :border-b)
                        :key (gen-key)}]))
          [:tbody] rule-data))

(defn table [rule-data]
  [:table {:class (c :w-full :text-left :border-collapse [:mt 5])
           :key (gen-key)}
   (create-table-heading ["Class" "Properties"])
   (create-table-cells rule-data)])

(defn code-span [code]
  [:span {:class (c :font-mono
                    :text-sm
                    :font-semibold
                    [:text "7c3aed"])
          :key (gen-key)}
   code])

(defn title-from-key [k]
  (-> k
      name
      (str/split #"-")
      (->>
       (mapv str/capitalize)
       (str/join " "))))

(defn navigation-arrows [[prev-page next-page]]
  (let [prev-title (->> prev-page
                        title-from-key
                        (str "← "))
        prev-link (-> prev-page
                      name
                      href)
        next-title (-> next-page
                       title-from-key
                       (str " →"))
        next-link (-> next-page
                      name
                      href)]
    [:div {:class (c :flex [:mt 16] :font-medium [:leading 6])
           :key (gen-key)}
     [:a {:class (c :flex [:mr 8] [:text :gray-500]
                    [:hover [:text :gray-900]]
                    [:duration 200])
          :href prev-link
          :key (gen-key)} prev-title]
     [:a {:class (c :flex :text-right :ml-auto
                    [:text :gray-500]
                    [:duration 200]
                    [:hover [:text :gray-900]])
          :href next-link
          :key (gen-key)} next-title]]))

(defn saturate-with-default
  ([table-data]
   (saturate-with-default table-data 5))
  ([table-data default]
   (reduce (fn [acc [k v]]
             (assoc acc k (if (associative? v)
                            v
                            (v default)))) {} table-data)))

(defn unit [x] {x {:unit :rem :magnitude "YOUR_INT * 0.25 REM"}})

(defn units [xs]
  (reduce (fn [acc k] (merge acc (unit k))) {} xs))

(defn example-block
  ([heading description table-data]
   (block (h1 heading)
          (p1 description)
          (table table-data)))
  ([heading description table-data usage]
   (block (h1 heading)
          (p1 description)
          (table table-data)
          (usage))))
