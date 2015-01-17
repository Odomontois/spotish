(ns ^{:author Odomontois} hackerrank.stock-maximize (:require [clojure.string :refer [split]]))
(defn step [[sell sum] value] (if (> value sell) [value sum] [sell (+ sum (- sell value))]))
(defn stock [xs] (second (reduce step [0 0] (reverse xs))))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn main []
  (dotimes [_ (first (read-ints))]
    (read-line)
    (println (stock (read-ints)))))
(main)