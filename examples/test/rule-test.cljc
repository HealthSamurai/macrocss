(ns rule-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [stylo.rule :refer [rule defrule]]))

(def rule-atom (atom {:old nil
                      :new nil}))

(defn clear [t]
  (reset! rule-atom {:old nil
                           :new nil})
  (t))

(use-fixtures :each clear)

(def rules {:sr-only  {:position "absolute"
                       :width "1px"
                       :height "1px"
                       :padding "0"
                       :margin "-1px"
                       :overflow "hidden"
                       :clip "rect(0 0 0 0)"
                       :white-space "nowrap"
                       :border-width "0"}
            :not-sr-only {:position "static"
                          :width "auto"
                          :height "auto"
                          :padding "0"
                          :margin "0"
                          :overflow "visible"
                          :clip "auto"
                          :white-space "normal"}})

(deftest rule-test
  (testing "we declare rule old-fashioned way via doseq and without macro,
            then add result of checking rule application into atom,
            it is stored under :old key in rule-atom"
    (let [_  (doseq [[k v] rules]
                      (defmethod rule k [_] [[:& v]]))
          old-rule-applied (rule :sr-only)
          _ (swap! rule-atom assoc :old old-rule-applied)]
      (is (= old-rule-applied (:old @rule-atom)))))
  (testing "we declare rule using add-rules macro and store it
            under :new key in rule atom,
            before we remove all methods to ensure it works.
            thus we can check equality of old and new"
    (let [_ (remove-all-methods rule)
          _ (doseq [[k v] rules]
                       (defrule k v))
            new-rule-applied (rule :sr-only)
            _ (swap! rule-atom assoc :new new-rule-applied)]
        (is (= new-rule-applied (:new @rule-atom)))
        (is (= (:new @rule-atom) (:old @rule-atom))))))
