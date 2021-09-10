(ns components.linter
  (:require [clojure.string :as str]
            [clojure.set :as ss]
            [stylo.core :refer [c]]
            [components.hiccup :as h]))

(def key-re #"\:\w+")

(def str-re #"\"\w+\"")

(def comm-marker-re #"^\;+[\w\s]*")

(def special-forms #{"defn " "defmethod " "defmulti " "defrules " "let "})

(def clj-default (apply conj ["ns " "def "
                              "(" ")"
                              "{" "}"
                              "[" "]"]
                        key-re
                        str-re
                        comm-marker-re
                        special-forms))

(def html-syntax ["<" "/>" ">" "="])

(defn dispatch-syntax-el
  [linted-str syntax-el]
  (cond
    (string? syntax-el) syntax-el
    (or (keyword? syntax-el)
        (symbol? syntax-el)) (str syntax-el)
    :else (re-find syntax-el linted-str)))

(defn index-of
  [linted-str w]
  (when w
    (str/index-of (str/lower-case linted-str)
                  (str/lower-case w))))

(defn dispatch-syntax-el-type
  [word]
  (cond
    (= word "(") :op-round-bracket
    (= word ")") :round-bracket

    (= word "[") :op-square-bracket
    (= word "]") :square-bracket
    (= word "{") :op-curly-bracket
    (= word "}") :curly-bracket
    (#{"ns "} word) :ns
    (#{"def "} word) :def
    (special-forms word) :special-form
    (re-find str-re word) :string
    (re-find key-re word) :keyword
    (re-find comm-marker-re word) :comment))

(defn matched-syntax-position
  [linted-str]
  (->> clj-default
       (map (fn [syntax-el]
              (let [word (dispatch-syntax-el linted-str syntax-el)
                    start (index-of linted-str word)
                    end (+ (or start 0) (count word))]
                {:pos-start start
                 :pos-end end
                 :word (when start
                         (subs linted-str start end))
                 :cur (when start
                        (dispatch-syntax-el-type word))})))
       (remove (comp nil? :pos-start))
       (sort-by :pos-start)))

(def doom-emacs-styles
  {:special-form (c [:text "#4894c9"])
   :after-form (c [:text "#b770cd"])

   :ns (c [:text "#4894c9"])
   :after-ns (c [:text "#e8ba79"])

   :def (c [:text "#4894c9"])
   :after-def (c [:text "#c39bd0"])

   :string (c [:text "#8fb360"])
   :keyword (c [:text "#a39bd9"])
   :comment (c [:text "#575c63"])

   :op-round-bracket (c [:text "#4894c9"])
   :round-bracket (c [:text "#4894c9"])

   :op-square-bracket (c [:text "#c678dd"])
   :square-bracket (c [:text "#c678dd"])

   :op-curly-bracket (c [:text "#8fb360"])
   :curly-bracket (c [:text "#8fb360"])
   :default (c [:text "#bbc2cf"])

   :bg (c [:text "#21252b"])})

(defn dispatch-style
  ([el]
   (dispatch-style el doom-emacs-styles))
  ([{:keys [prev-el-type cur]} stylesheet]
   (cond
     (= :ns prev-el-type) (:after-ns stylesheet)
     (= :special-form prev-el-type) (:after-form stylesheet)
     (= :def prev-el-type) (:after-def stylesheet)
     :else (cur stylesheet))))

(defn wrap-by-component [el]
  [:span {:class (dispatch-style el)
          :key (h/gen-key)}
   (:val el)])

(defn declare-component
  [{:keys [cur word]}]
  {:val word
   :cur cur})

(defn conjoin-linted-el
  [linted-str {:keys [acc prev-end]} {:keys [pos-start] :as w}]
  (conj acc
        (subs linted-str prev-end pos-start)
        (declare-component w)))

(defn inject-component [linted-str syntax-pos]
  (reduce (fn [acc v]
            (-> acc
                (assoc :acc (conjoin-linted-el linted-str acc v))
                (assoc :prev-end (:pos-end v))))
          {:acc [] :prev-end 0}
          syntax-pos))

(defn insert-lost
  [linted-str {:keys [acc prev-end]}]
  (cond-> acc (not= (count linted-str) prev-end) (conj (subs linted-str prev-end))))

(defn inject-default
  [vec-of-lazy-seq]
  (mapv (partial reduce (fn [acc v] (if (string? v)
                                      (conj acc {:val v
                                                 :cur :default})
                                      (conj acc v)))
                 []) vec-of-lazy-seq))

(defn consider-prev-el
  [vec-of-els]
  (->> vec-of-els
       (reduce (fn [{:keys [prev acc] :as res} {:keys [cur] :as el}]
                 (-> res
                     (assoc :acc (conj acc (merge el {:prev prev})))
                     (assoc :prev cur)))
               {:prev nil :acc []})
       :acc))

(defn make-el-comment [acc el]
  (conj acc (-> el
                (assoc :cur :comment))))

(defn comment-logic [{:keys [acc comm] :as res} {:keys [val cur] :as el}]
  (if comm
    (if (= :comment cur)
      (assoc res :acc (conj acc el))
      (if (= "\n" val)
        (-> res
            (assoc :acc (make-el-comment acc el))
            (assoc :comm false))
        (assoc res :acc (make-el-comment acc el))))
    (if (= :comment cur)
      (-> res
          (assoc :acc (conj acc el))
          (assoc :comm true))
      (assoc res :acc (conj acc el)))))

(defn consider-comment
  [vec-of-els]
  (->> vec-of-els
       (reduce comment-logic {:acc []
                              :comm false})
       :acc))

(defn inject-spaces [vec-of-els]
  (reduce (fn [acc {:keys [val prev cur] :as el}]
            (if (or (= :def cur)
                    (= :special-form cur)
                    (= :ns cur)
                    (= :comment cur)
                    (= :keyword cur)
                    (= :string cur)
                    (= :default cur))
              (conj acc (update el :val str " "))
              (if (and (or (= :square-bracket cur)
                           (= :curly-bracket cur)
                           (= :round-bracket cur))
                       (or (= :default prev)
                           (= :keyword prev)))
                (let [new-last-item (-> acc
                                        last
                                        (update :val str/trimr))]
                  (-> acc
                      (subvec 0 (- (count acc) 1))
                      (conj new-last-item el)))
                (conj acc el))))
          [] vec-of-els))

(defn lint-string
  [linted-str]
  (->> linted-str
       matched-syntax-position
       (inject-component linted-str)
       (insert-lost linted-str)
       (remove empty?)))

(defn highlight [code]
  (->  code
     (str/split #" +")
  (->> (mapv lint-string)
     inject-default
     flatten
     consider-comment
     consider-prev-el
     inject-spaces
     (mapv wrap-by-component))))
