(ns rule-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [stylo.rule :refer [rule defrules]]
            [stylo.core :refer [c c? styles media-styles]]
            [garden.core]))

(def rule-atom (atom {:old nil
                      :new nil}))

(defn clear [t]
  (reset! rule-atom {:old nil
                     :new nil})
  (t))

(use-fixtures :each clear)

(defn clear-styles [t]
  (reset! styles {})
  (reset! media-styles {})
  (t))

(use-fixtures :each clear-styles)

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

  (testing "media query may include pseudo classes
and it also works properly"
    (let [classname (c [:smartphone [:hover [:text :green-300]]])]
      (is (-> (stylo.core/get-styles)
              empty?
              not))))

  (testing "we can take different forms as arguments: "
    (testing "pseudoclasses combined with media queries"
      (c? [:text :blue-300] [:smartphone [:text :blue-500]]))
    (testing "pseudoclasses combined with media queries"
      (c? [:smartphone [:text :blue-500] {:font-weight "500"}]
          [:screen [:text :pink-200] {:font-weight "300"}]))
    (testing "pseudoclasses combined with classes"
      (c? [:smartphone [:bg :red-500] [[:.some-arbitrary-class {:bg :blue-400}]]]))
    (testing "pseudoclasses combined with hiccup styles"
      (c? [:progress-bar [:bg :red-500]] {:font-weight "500"}))
    (testing "pseudoclasses with macroCSS structures"
      (c? [:progress-bar [:bg :red-500]]))
    (testing "psedoclasses combined with pseudoclasses"
      (c? [:disabled [:hover [:bg :red-500]]]))
    (testing "macroCSS with random classes"
      (c? [:bg :red-500] [[:.some-arbitrary-class {:bg :blue-400}]]))
    (testing "macroCSS with random classes"
      (c? [:bg :red-500]
          [:hover [[:.some-arbitrary-class {:bg :blue-400}]]]))
    (testing "another way of using pseudoclasses"
      (c? [:pseudo ":nth-child(2)" [:hover [:bg :red-500]]]))
    (testing "gardenCSS structures"
      (c? [[:& {:color "red"}]
           [:&:target {:color "green"}]]))
    (testing "plain hiccup"
      (c? {:color "red"}))
    (testing "some more pseudoclasses"
      (c? [:hover [:placeholder :white] [:mb 1]]))
    (testing "custom values in macroCSS"
      (c? [:p 1]))
    (testing "just macroCSS"
      (c? [:placeholder :white]))
    (testing "more custom values"
      (c? [:divide-x 2]))))
