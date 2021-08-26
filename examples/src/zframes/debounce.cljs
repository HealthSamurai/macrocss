(ns zframes.debounce
  (:require [re-frame.core  :refer [reg-fx dispatch console] :as rf]))

(defn now [] (.getTime (js/Date.)))

(def registered-keys (atom nil))

(defn dispatch-if-not-superceded [{:keys [key delay event time-received]}]
  (when (= time-received (get @registered-keys key))
    ;; no new events on this key!
    (dispatch event)))

(defn dispatch-later [{:keys [delay] :as debounce}]
  (js/setTimeout
   (fn [] (dispatch-if-not-superceded debounce))
   (or delay 300)))

(defn dispatch-debounce [debounce]
  (let [ts (now)]
    (swap! registered-keys assoc (:key debounce) ts)
    (dispatch-later (assoc debounce :time-received ts))))

(reg-fx :dispatch-debounce dispatch-debounce)

(rf/reg-event-fx
 :dispatch-debounce
 (fn [fx [_ deb]]
   {:dispatch-debounce deb}))
