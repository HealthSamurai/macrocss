(ns utils
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.java.shell :as shell]))

(defn version [] (edn/read-string (slurp "version.edn")))

(defn version->str
  [ver]
  (->> (vals ver)
       (str/join ".")))

(defn save-version
  [new-ver]
  (spit "version.edn" new-ver))

(defn update-pom-xml
  [new-ver]
  (let [new-version-xml (str "<version>" new-ver "</version>")
        new-pom-xml (str/replace-first
                     (slurp "pom.xml")
                     (re-pattern "<version>.+</version>")
                     new-version-xml)]
    (spit "pom.xml" new-pom-xml)))

(defn commit-push
  [new-version-str]
  (doseq [cmd ["git add version.edn pom.xml"
               (format "git commit -m 'Bump version to %s'" new-version-str)
               (str "git tag " new-version-str)
               "git pull -r"
               (format "git push --atomic origin master %s" new-version-str)]]
    (let [{out :out} (shell/sh "sh" "-c" cmd)]
      (println cmd)
      (println out))))

(defn propagate-updates
  [new-version]
  (let [new-version-str (version->str new-version)]
    (save-version new-version)
    (update-pom-xml new-version-str)
    (commit-push new-version-str)
    (version)))

(defn bump-major
  []
  (-> (version)
      (update :major inc)
      (merge {:minor 0 :fix 0})
      propagate-updates))

(defn bump-minor
  []
  (-> (version)
      (update :minor inc)
      (merge {:fix 0})
      propagate-updates))

(defn bump-fix
  []
  (-> (version)
      (update :fix inc)
      propagate-updates))
