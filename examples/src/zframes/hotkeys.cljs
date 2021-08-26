(ns zframes.hotkeys
  (:require [re-frame.core :as rf]

            [re-frame.db :as db]))

(defonce handlers (atom {"Slash" {:event ::help}}))

(rf/reg-event-fx
 ::help
 (fn [& _]
   (println @handlers)))

;; @handlers

(rf/reg-fx
 :hotkey/init
 (fn [opts]
   (swap! handlers merge opts)
   (.addEventListener
    js/document
    "keyup"
    (fn [ev]
      (when-let [h (and (.-altKey ev) (get @handlers (.-code ev)))]
        (println "Hotkey " (.-code ev) @handlers)
        (rf/dispatch [(:event h) h])) 
      ) false)))

(rf/reg-fx
 :hotkey/add
 (fn [opts]
   ;; (println "Add hotkey " opts)
   (swap! handlers
          (fn [old]
            (reduce (fn [ks [id ev]]
                      (assoc ks (:key ev) (assoc ev :id id)))
                    old opts)))))

(rf/reg-fx
 :hotkey/clear
 (fn [id]
   ;; (println "Clear hotkey " id)
   (let [ids (into #{} (if (sequential? id) id #{id}))]
     (swap! handlers (fn [old]
                       (reduce (fn [ks [k ev]]
                                 (if (contains? ids (:id ev))
                                   (dissoc ks k)
                                   ks))
                               old old))))))
