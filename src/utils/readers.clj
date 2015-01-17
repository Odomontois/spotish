(ns ^{:author Odomontois} utils.readers
  (:require [clojure.string :refer [split]])
  (:import  [java.lang.BigInteger]))
(defn read-int [] (Long/parseLong (read-line)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn read-bin [](BigInteger. (read-line) 2))
