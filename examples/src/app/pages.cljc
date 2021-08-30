(ns app.pages)

(defonce pages (atom {}))

(defn reg-page [key title comp w]
  (swap! pages assoc key {:cmp comp :title title :w w}))
