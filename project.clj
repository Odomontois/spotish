(defproject
  spotish "INITIAL"
  :dependencies
  [[org.clojure/clojure "1.6.0"]
   [clojure-csv/clojure-csv "2.0.1"]
   [environ "1.0.0"]
   [org.clojure/data.json "0.2.5"]]
  :repl-options
  {:init (do
           (use 'daily))})
