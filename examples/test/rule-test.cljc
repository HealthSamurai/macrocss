(ns rule-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [stylo.rule :refer [rule defrules]]
            [stylo.core :refer [c]]))

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
            :not-sr-only (fn [x] {:position x
                                  :width "auto"
                                  :height "auto"
                                  :padding "0"
                                  :margin "0"
                                  :overflow "visible"
                                  :clip "auto"
                                  :white-space "normal"})})

(deftest rule-tests
  (testing "we declare rules old-fashioned way via defmethod"
    (let [sr :sr-only
          _ (defmethod rule sr [_] [[:& (sr rules)]])
          nsr :not-sr-only
          _ (defmethod rule nsr [_ x] [[:& {:position x
                                            :width "auto"
                                            :height "auto"
                                            :padding "0"
                                            :margin "0"
                                            :overflow "visible"
                                            :clip "auto"
                                            :white-space "normal"}]])
          old-a1 (rule :sr-only)
          old-a2 (rule :not-sr-only "static")
          _ (swap! rule-atom assoc-in [:old :a1] old-a1)
          _ (swap! rule-atom assoc-in [:old :a2] old-a2)]
      (is (= old-a1  (->> rule-atom
                          deref
                                  :old
                                  :a1)))
      (is (= old-a2 (->> rule-atom
                         deref
                         :old
                         :a2)))))
  (testing "we declare rule using defrules and store it
            under :new key in rule atom, before we remove all methods to ensure it works.
            thus we can check equality of old and new"
    (let [_ (remove-all-methods rule)
          _ (defrules rules)
          new-a1 (rule :sr-only)
          new-a2 (rule :not-sr-only "static")
          _ (swap! rule-atom assoc-in [:new :a1] new-a1)
          _ (swap! rule-atom assoc-in [:new :a2] new-a2)]
      (is (= new-a1 (-> rule-atom
                        deref
                        :new
                        :a1)))
      (is (= new-a2 (-> rule-atom
                        deref
                        :new
                        :a2)))
      (is (= (:new @rule-atom) (:old @rule-atom))))))


(deftest media-query-test
  (testing "we do not have different hash with media query,
            because media query contains styles for the same class "
    (is (= (c [:text :green-300]
              [:smartphone
               [:text :green-300]])
           (c [:text :green-300]))))
  (testing "we affect CSS file by adding media queries for concrete class")
  (testing "media query may include pseudo classes
and it also works properly"))
