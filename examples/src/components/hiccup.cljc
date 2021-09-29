(ns components.hiccup
  (:require [stylo.core :refer [c]]
            [clojure.string :as str]))

(defn gen-key [] (gensym "key-"))

(defn k [] {:key (gen-key)})

(defn with-key [m] (merge (k) m))

(def doom
  {:special-form (c [:text "#4894c9"])
   :after-form (c [:text "#b770cd"])

   :ns (c [:text "#4894c9"])
   :after-ns (c [:text "#e8ba79"])

   :def (c [:text "#4894c9"])
   :after-def (c [:text "#c39bd0"])

   :opening-bracket (c [:text "#4894c9"])
   :function (c [:text "#bbc2cf"])

   :string (c [:text "#8fb360"])
   :keyword (c [:text "#a39bd9"])
   :comment (c [:text "#575c63"])

   :round-bracket (c [:text "#4894c9"])
   :square-bracket (c [:text "#c678dd"])
   :curly-bracket (c [:text "#8fb360"])
   :default (c [:text "#b2b9c6"])

   :bg (c [:text "#21252b"])})

(defn span-style [smbl]
  (cond

    (or (= \] smbl)
        (= \[ smbl)) (:square-bracket doom)

    (or (= \} smbl)
        (= \{ smbl))  (:curly-bracket doom)

    (or (= \( smbl)
        (= \) smbl)) (:round-bracket doom)

    (or (= \< smbl)
        (= \> smbl)) (c [:text :blue-900])

    (= \= smbl)  (c [:text :orange-900])
    :else (:default doom)))

(defn lint [code-string]
  (reduce (fn [acc smbl]
            (conj acc [:span {:key (gen-key)
                              :class (span-style smbl)} smbl]))
          [:span {:key (gen-key)}] code-string))

(defn comment-code [code-string]
  [:span {:class (:comment doom)
          :key (gen-key)}
   code-string])

(defn code-style [style]
  (condp = style
    :bash (c [:text :white]
             :font-mono
             :text-sm)
    :clojure (c :font-mono
                :font-hairline
                :text-sm
                :tracking-tighter
                :whitespace-pre-line)))

(defn code [k & code-string]
  [:div
   {:class (c [:mt 2]
              [:mb 2]
              [:bg "#21252b"]
              [:rounded 12]
              [:px 4]
              [:py 3]
              :w-auto)
    :key (gen-key)}
   [:pre {:class (code-style k)
          :key (gen-key)} code-string]])

(defn block [& content]
  [:div {:class (c :content-center [:mt 5]
                   [:smartphone [:w 88]]
                   [:w 180])
         :key (gen-key)}
   [:div
    {:class (c :box-border [:pb 10] [:mb 4] [:border-b :gray-200])
     :key (gen-key)}
    content]])

(defn example-div [& content]
  [:div {:class (c [:w 180] :content-center [:mt 1])
         :key (gen-key)}
   [:div
    {:class (c :box-border [:pb 5] [:mb 1])
     :key (gen-key)}
    content]])

(defn h1 [& content]
  [:h1 {:class (c
                :text-3xl
                [:m 1]
                :font-extrabold
                [:text :gray-900]
                :tracking-tight)
        :key (gen-key)} content])

(defn p1 [& content]
  [:p {:class (c [:m 1] :font-sans :text-lg :font-light [:text :gray-600])
       :key (gen-key)} content])

(defn h3 [& content]
  [:h3 {:class (c [:m 1]
                  :text-xl
                  :font-bold
                  [:text :gray-900]
                  :tracking-tight
                  [:smartphone :break-all])
        :key (gen-key)} content])

(defn p3 [& content]
  [:div {:key (gen-key)}
   [:p {:class (c [:ml 1] [:mb 3] :font-sans :font-light :text-base [:text :gray-700]
                  [:smartphone :break-all])
       :key (gen-key)} content]])

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
                  :whitespace-no-wrap
                  [:p 1])
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
                  [:text :blue-500]
                  [:p 2])
        :key (gen-key)}
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
  [:table {:class (c :text-left :border-collapse [:mt 1] [:mb 1]
                     :w-full [:smartphone [:w 88]
                              :overflow-scroll])
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
    [:div {:class (c :flex [:mt 16] [:mb 7] :text-lg :font-medium [:leading 6]
                     [:smartphone :content-center])
           :key (gen-key)}
     [:a {:class (c [:text :gray-600]
                    [:hover [:text :gray-900] :border-b]
                    [:duration 200]
                    [:smartphone [:ml 5]])
          :href prev-link
          :key (gen-key)} prev-title]
     [:a {:class (c  :ml-auto
                    [:text :gray-600]
                    [:duration 200]
                    [:hover [:text :gray-900] :border-b]
                    [:smartphone [:ml 16]])
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
   (example-div (h3 heading)
                (p3 description)
                (table table-data)))
  ([heading description table-data usage]
   (example-div (h3 heading)
                (p3 description)
                (table table-data)
                (usage))))
