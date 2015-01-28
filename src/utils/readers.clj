(ns ^{:author Odomontois} utils.readers
  (:require [clojure.string :refer [split]]
            [clojure.java.io :as jio]
            [clojure-csv.core :refer [parse-csv]])
  (:import [java.lang.BigInteger]))
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn read-int [] (parse-int (read-line)))
(defn read-ints [] (map parse-int (split (read-line) #"\s+")))
(defn read-bin [] (BigInteger. (read-line) 2))
(defn slurp-resource [name] (slurp (-> name jio/resource jio/file)))
(defn read-csv [name f & options] (map f (apply parse-csv (slurp-resource name) options)))
