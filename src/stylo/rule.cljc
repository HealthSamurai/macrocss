(ns stylo.rule)

(defmulti rule (fn [k & _] k) :default ::default)

(defmethod rule ::default
  [k & types]
  [[:& {k (pr-str types)}]])

(defn defrules
  ([rules]
  (doseq [[k v] rules]
    (if (associative? v)
     (defmethod rule k [_]
       [[:& v]])
     (defmethod rule k [_ x]
       [[:& (v x)]]))))
  ([rules pseudo-element-key]
   (doseq [[k v] rules]
    (if (associative? v)
     (defmethod rule k [_]
       [[(str ":&:" pseudo-element-key) v]])
     (defmethod rule k [_ x]
       [[(str ":&:" pseudo-element-key) (v x)]])))))

(defn merge-by-selector
  [exps]
  (->> exps
       (group-by first)
       (mapv
         (fn [[selector exps]]
           (let [style (reduce merge (map second exps))
                 children (reduce concat (map #(drop 2 %) exps))]
             (cond-> [selector]
               (seq style) (conj style)
               (seq children) (into children)))))))

(defn join-rules
  [rules]
  (->> rules
       (mapcat
         #(cond
            (and (sequential? %) (every? sequential? %)) %
            (sequential? %) (apply rule %)
            (map? %) [[:& %]]
            :else (rule %)))
       (merge-by-selector)))
