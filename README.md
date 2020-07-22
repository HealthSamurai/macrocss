# macrocss

Styles in css for clojure(script) using macros

hibryd of tailwind and https://github.com/clj-commons/cljss

```clj
[:div {:class (c [:pt 8] [:px 6] :h-screen :flex :flex-col [:space-y 4])}
   [search/search]
   [:div {:class (c :flex [:space-x 4] :flex-1 :overflow-hidden)}
    [:div {:class (c [:w 80] [:px 4] [:py 3] [:bg :gray-100] :border [:border-b 0] :rounded-tl :rounded-tr :overflow-auto)}
     [search/filter-list]]
    [:div {:class (c :flex :flex-col :flex-1 [:space-y 4])}
     [:div {:class (c [:px 4] [:py 3] [:bg :gray-100] :border :rounded :flex :items-center)}
      [navigation/button]
      [:div {:class (c :ml-auto)}
       [search/filter-form]]]
```

## Dev

See samurai seminar in Russian - https://storage.googleapis.com/samurai-files/samurai-seminar-css.mp4
