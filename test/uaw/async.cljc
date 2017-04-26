(ns uaw.async
  (:require
   #?(:clj   [clojure.test :refer :all]
      :cljs  [cljs.test :refer-macros [deftest is async use-fixtures]])
   #?(:cljs  [ajax.core :refer [GET]])
             ))
;; Run these fixtures for each test.

;; We could also use :once instead of :each in order to run
;; fixtures once for the entire namespace instead of once for
;; each individual test.
(use-fixtures
  :each
  {:before (fn [] (println "Setting up tests..."))
   :after (fn [] (println "Tearing down tests..."))})

(deftest another-successful-test
  ;; Give us an idea of when this test actually executes.
  (println "Running a test...")
  (is (= 4 (count "test"))))



;;#?(:cljs
(deftest test-async
  (async done
  (GET "http://localhost:8081/"
       ;; will always fail from PhantomJS because
       ;; `Access-Control-Allow-Origin` won't allow
       ;; our headless browser to make requests to Google.
       {:error-handler
        (fn [res]
          (is (= (:status-text res) "Request failed."))
          (println "Async Test finished!"))})))
;;)

(deftest ajax-get-test
  (async done
    (GET "http://www.lispcast.com/"
      {:handler (fn [response]
           (is (= 200 (:status response)))
           (println "Get lispcast passed"))} )))
