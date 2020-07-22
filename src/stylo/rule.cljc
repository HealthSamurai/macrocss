(ns stylo.rule
  (:require
    #?(:clj [flatland.ordered.map :refer [ordered-map]])))


(defmulti rule (fn [k & _] k) :default ::default)


(defmethod rule
  ::default
  [k & types]
  [[:& {k (pr-str types)}]])


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



(comment
  (-> [[:& {:color "red"}]
       [:& {:background "blue"}]
       [:&:hover {:display "none"}]
       [:&:hover {} [:&:first {:display "grid"}]]
       [:&:hover {} [:&:last {:display "flex"}]]
       [:&:hover {:display "none"}]
       [:&:disabled {} [:&:hover {:display "block"}]]]
      (merge-by-selector)))


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
