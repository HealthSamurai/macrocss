(ns components.default
  (:require [components.hiccup :as h]
            [stylo.core :refer [c]]
            [components.linter :as l]
            [goog.object :as g]))

(def img-link "https://downloader.disk.yandex.ru/preview/ca522feb15400eba63497d10e65d0405373c8096601bbda6cee6a48d0e19f323/613f67d4/Wl6CmFjIBj2w8wB2eabbLfsP5MTyOBZ61rOCtuuZNvM1aoLBKXGZxDldjtRlFBHJ9nCtiA5elDx96oZoJ1OvIQ%3D%3D?uid=0&filename=ryzikov.jpeg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=0&tknv=v2&size=2048x2048")
(def phrase "\"MacroCSS is is utility-first, atomic only CSS library that is flexible and solves serious code health and convenience problems\"")

(defn niquola-quote []
  [:figure {:class (c [:bg :gray-100]
                      [:rounded :xl]
                      [:mt 2]
                      [:p 8])
            :key (h/gen-key)}
   [:img {:class (c [:w 32] [:h 32] [:rounded :full]
                    :mx-auto)
          :src img-link
          :key (h/gen-key)}]
   [:div {:class (c [:pt 6] [:space-y 4])
          :key (h/gen-key)}
    [:p {:class (c :text-lg :font-semibold)
         :key (h/gen-key)}
     phrase]
    [:figcaption {:class (c :font-medium)
                  :key (h/gen-key)}
     [:div {:class (c [:text "#00bcd4"])
            :key (h/gen-key)} "Nikolay Ryghikov"]
     [:div {:class (c [:text :gray-500])
            :key (h/gen-key)} "Health Samurai, CTO"]]]])

(defn niquola-quote-gif []
  [:div {:class (c :flex-col :content-center)
         :key (h/gen-key)}
   [:div {:key (h/gen-key)}
     [:img {:src "https://media.giphy.com/media/8JjUds7oN2MwGUGnfU/giphy.gif?cid=790b761175a6c8190e266f9acbb9560a261ddb7242171c76&rid=giphy.gif&ct=g"
            :class (c [:w 140])
            :key (h/gen-key)}]]
   [:div {:key (h/gen-key)}
    [:img {:class (c [:ml 2])
           :src "https://media.giphy.com/media/KUW0CK8zQwfZJADhGu/giphy.gif?cid=790b761182f7f2f69c620e14b41aa4b540a47d649a53b85c&rid=giphy.gif&ct=g"
           :key (h/gen-key)}]]])

(defn about
  []

  (h/block
   [:h3 {:class (c
                :text-3xl
                [:mt 5]
                [:mb 5]
                :font-extrabold
                [:text :gray-900]
                :tracking-tight)
        :key (h/gen-key)}
    "Lightspeed styling without leaving your Clojure code."]
   [:p {:class (c [:m 1] :font-sans :text-xl :font-light [:text :gray-600])
        :key (h/gen-key)}
     "Utility first CSS library packed with familiar Tailwind classes."]
  (niquola-quote-gif)
  (h/block
   (h/h3 "Version and compatibility. ")
      (h/p3 (str "Latest version is " (g/get js/window "STYLO_VERSION")))
      (h/p3 "Tested with: ")
      (h/p3 "Clojure 1.10.0")
      (h/p3 "ClojureScript 10.10.866")
      (h/p3 "Status: usable alpha."))

  (h/block
    (h/h3 "Distribution and license: ")
    (h/p3 "Copyright © belongs to HealthSamurai and contributors.")
    (h/p3 "Distributed under the Eclipse Public License 2.0")
    (h/p3 "Logo is a property of HealthSamurai, but you can make a T-shirt with it free of charge."))))

(defn installation
  []
  (h/block
   (h/block
    (h/h1 "Installation")
    (h/p1 "Learn to set up shadow-cljs from the scratch in your project. "))
   (h/block
    (h/h3 "Installing using shadow-cljs and Leiningen")
    (h/p3 "At first we need to create a new shadow-cljs template.")
    (h/code :bash "lein new shadow-cljs shadow-example +reagent")
    (h/p3 "Don't forget to install all necessary js packages: ")
    (h/code :bash "npm install")
    (h/p3 "Other available options of shadow-cljs are given here: "
       (h/a "https://github.com/shadow-cljs/lein-template"))
    (h/p3 "Add  into " [:span {:class (c :font-bold)
                             :key (h/gen-key)} " project.clj "] " dependencies: ")
    (h/code :clojure
            (l/highlight (str "[com.health-samurai/macrocss \""  (g/get js/window "STYLO_VERSION") "\"]")))
    (h/p3 "Open up your" [:span {:class (c :font-bold)
                               :key (h/gen-key)} " shadow-cljs.edn "] "configuration file and add")
    (h/code :clojure
            (l/highlight "{ :build-hooks [(stylo.shadow/reload \"public/out/stylo/css/stylo.css\")]}"))
    (h/p3 "  into the :app configuration."
        [:span {:class (c :font-bold)
                :key (h/gen-key)} " NB! "]
        "Path written below is the path where the css file will be generated. Do not just copy and paste, find your own location.")
    (h/p3 " Our configuration should look like this: ")
    (h/code :clojure (l/highlight "{... \n :builds \n {:app \n \n {... \n \n :build-hooks [(stylo.shadow/reload \"public/out/stylo/css/stylo.css\")]}}}"))
    (h/p3
     "Open public/index.html file, it is generated by shadow-cljs by default. We should add the new source of css into it. ")
    (h/p3 "Add the following into the <head> </head> section: ")
    (h/code :clojure (h/lint " <link href= \"out/stylo/css/stylo.css\" rel= \"stylesheet\">"))
    (h/block
     (h/h3 "Lets go!")
     (h/p3
     "So, it is time to use the library!
        Comprehensive documetation may be read "  (h/a "/documentation" "here") ".")
     (h/p3 "This landing is done using macroCSS library, so it may explain some conceptions via code: "
         (h/a "https://github.com/HealthSamurai/macrocss/tree/master/examples" "github/examples."))))
  (h/block
    (h/h3 "figwheel")
    (h/p3 "Documentation for figwheel is in progress."))))

(defn basic-syntax
  []
  (h/block
   (h/block
    (h/h1 "Basic Syntax.")
    (h/p1 " Essential library usage scenarios."))
  (h/block
   (h/h3 "Actually, it is not a rocket science.")
   (h/p3 "You just require library and do some little magic to make macros work in ClojureScript: ")
   (h/code :clojure (l/highlight "(ns wonderful-ns.core \n  (:require-macros [stylo.core :refer [c]])) \n\n")
                     (l/highlight ";; c - hey, it's out macro \n ;; it waits for class alias as arguments \n\n")
                     (l/highlight "[:div {:class (c [:pt 8] :h-screen)}] \n \n")
                     (l/highlight ";; :h-screen and [:pt 8] \n ;;- those keywords are just our version \n;; of Tailwind classnames"))

    (h/p3 "Maybe you have already started to suspect, but when class needs some extra input (e.g. size, color, weight etc.) \n - it is passed as a vector.
    Let us try to explain what happens here: ")
    (h/code :clojure (l/highlight "(c [:pt 8])  \n\n")
                     (l/highlight ";; once used inside a component \n")
                     (l/highlight ";; it generates css with unique classname \n")
                     (l/highlight ";; (c [:pt 8]) will always return either\n ;; class :c-1581282564\n ;; or location in code \n")
                     (l/highlight ";; and properties {'padding-top:' 8rem}  \n")
                     (l/highlight ";; classname is not random, it's a hash (sic!) \n")
                     (l/highlight ";; every component with same set of properties \n")
                     (l/highlight ";; will have the same class name \n")
                     (l/highlight ";; isn't it more convenient than\n ;; 'pt-8' in Tailwind? \n")
                     (l/highlight ";; when you do not use class inside a component \n")
                     (l/highlight ";; it is swiped out from css file \n ;; - no useless or dead CSS.")))
  (h/block
   (h/h3 "Use it as constructor!")
   (h/p3 "Whether you face situations when you have not found needed css rules \n
        or you have found some inconvenience using pre-defined css - \n
        adding your own is not a big deal.")
   (h/p3 "Adding a single style may be done by registering new method, we call it " (h/code-span "rule") ".")
   (h/p3 "For example - you want to make special style for Apple Iphone XR (c) users: ")
   (h/code :clojure (l/highlight "(ns your-wonderful-code.core \n  (:require [stylo.rule :refer [rule]])) \n\n")
                    (l/highlight ";; Add your custom rule (if styles do not appear after hot-reload \n")
                    (l/highlight ";; - we recommend to recompile project) \n")
                    (l/highlight ";; If styles are not added even after - clean \n")
                    (l/highlight ";; .cpcache .shadow-cljs and public/out folders \n\n")
                    (l/highlight "(defmethod rule :w-max-iphone-xr [_] \n")
                    (l/highlight "  [[:& {:max-width \"414px\"}]]) \n \n")
                    (l/highlight "(defn apple-geek-div [] [:div {:class (c :w-max-iphone-xr)}])\n\n")
                    (l/highlight ";; And it just works. Seemlessly."))
   (h/p3 "But what if we want to add rules for a bunch of different phones. Not just a single model?")
   (h/p3 "We have a solution for this case. Luke, use "  (h/code-span "defrules") "!")
   (h/code :clojure (l/highlight "(ns your-wonderful-code.core \n")
                    (l/highlight "  (:require [stylo.rule :refer [defrules]])) \n\n")
                    (l/highlight ";; Defrules receives [k v] structure as an argument. \n")
                    (l/highlight ";; Hash-map is a perfect solution for it, isn't it? \n\n")
                    (l/highlight "(def iphones-wmax {:w-max-iphone-12 {:max-width \"390px\"} \n")
                    (l/highlight " :w-max-iphone-11 {:max-width \"414px\"} \n")
                    (l/highlight " :w-max-iphone-x {:max-wdith \"375px\"}}) \n\n")
                    (l/highlight ";; So let's use defrules \n\n")
                    (l/highlight "(defrules iphone-wmax) \n\n")
                    (l/highlight "(defn one-more-apple-geek-div [] \n")
                    (l/highlight "  [:div {:class (c :w-max-iphone-12)}])\n\n")
                    (l/highlight ";; And yes. It just works. Seemlessly. \n\n")))))
