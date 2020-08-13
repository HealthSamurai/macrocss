(ns stylo.shadow-cljs
  (:require [clojure.java.io :as io]
            [shadow.jvm-log :as log]
            [stylo.core]))

(def css-path-key ::css-path)


(defn get-path [build-state]
  (get build-state css-path-key))


(defn assoc-path [build-state path]
  (assoc build-state css-path-key path))


(defn write!
  ([css output-dir file-name]
   (write! css output-dir file-name false))
  ([css output-dir file-name append]
   (try
     (when-not (.exists output-dir)
       (.mkdirs output-dir))
     (spit (str output-dir "/" file-name) css :append append)
     (log/info ::write! (str "Stylo css saved to "  output-dir "/" file-name))
     (catch Exception e
       (log/warn ::write! {:message (str "Failed to save Stylo css in "  output-dir "/" file-name)
                           :exception e})))))


(defn hook
  {:shadow.build/stage :flush}
  ([build-state]
   (hook build-state {:output-dir (str "target/" (name (:shadow.build/mode build-state)) "/public/css")
                      :file-name "stylo.min.css"}))
  ([build-state options]
   (let [{:keys [output-dir file-name]} options
         output-dir (io/file output-dir)]
     (when (seq @stylo.core/styles)
       (log/info ::hook (str "Stylo styles: " (keys @stylo.core/styles)))
       (write! (stylo.core/get-styles {:pretty-print? false})
               output-dir
               file-name))
     (assoc-path build-state file-name))))


(defn reload-hook
  {:shadow.build/stage :flush}
  ([build-state]
   (reload-hook build-state {:output-dir (str "target/" (name (:shadow.build/mode build-state)) "/public/css")
                             :file-name "stylo.min.css"}))
  ([build-state options]
   (log/debug ::hook (str "Stylo styles: " (keys @stylo.core/styles)))
   (let [{:keys [output-dir file-name]} options
         output-dir (io/file output-dir)]
     (when (seq @stylo.core/styles)
       (if (.exists (io/file output-dir file-name))
         (write! (stylo.core/get-stylo-styles)
                 output-dir
                 file-name
                 true)
         (write! (stylo.core/get-styles)
                 output-dir
                 file-name)))
     (assoc-path build-state file-name))))
