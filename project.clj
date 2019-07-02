(defproject share-demo "0.1.0-SNAPSHOT"
  :description "Demo project to showcase some Clojure features by working out value of a portfolio"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cheshire "5.8.1"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.csv "0.1.4"]
                 ]
  :plugins [[lein-auto "0.1.3"]]
  :repl-options {:init-ns clj-demo.core})
