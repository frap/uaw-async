(set-env!
 :source-paths #{"src"}

 :dependencies '[[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.494"]
                 [adzerk/boot-cljs "1.7.228-2"]
                 [crisptrutski/boot-cljs-test "0.3.0"]
                 [doo "0.1.7"] ; used by boot-cljs-test
                 [adzerk/boot-test "1.2.0"]
                 [cljs-ajax "0.5.9"]])

(require '[adzerk.boot-test :refer [test]]
         '[adzerk.boot-cljs :refer [cljs]]
         '[crisptrutski.boot-cljs-test :refer [test-cljs]]
         )

(deftask testing
   []
   (merge-env! :source-paths #{"test"})
   identity)

(deftask clj-tdd
  "Launch a CLJ TDD Environment"
  []
  (comp
   (testing)
   (watch)
   (test :namespaces #{'uaw.async}))
  )

(deftask cljs-tdd
  "Launch a CLJ TDD Environment"
  []
  (comp
   (testing)
 ;;  (watch)
   (test-cljs :namespaces #{'uaw.async})))
