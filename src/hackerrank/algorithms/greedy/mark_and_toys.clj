(ns ^{:author "Odomontois"} hackerrank.algorithms.greedy.mark-and-toys
  (:require [clojure.string :refer [split]]))
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn read-int [] (parse-int (read-line)))
(defn read-ints [] (map parse-int (split (read-line) #"\s+")))

(defn solution [k toys]
  (loop [k k toys (sort toys) count 0]
    (if (or (empty? toys) (> (first toys) k))
      count
      (recur (- k (first toys)) (rest toys) (inc count)))))

(defn main [] (println (solution (second (read-ints)) (read-ints))))
