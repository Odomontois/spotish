(ns ^{:author "Odomontois"} hackerrank.algorithms.greedy.board-cutting
  (:require [clojure.string :refer [split]]))
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn read-int [] (parse-int (read-line)))
(defn read-ints [] (map parse-int (split (read-line) #"\s+")))
(defn direct [dir zs] (map vector zs (repeat dir)))
(def base (->> 10 (repeat 9) (reduce *) (+ 7)))
(defn solution [xs ys]
  (loop [vs (->> (direct 0 xs) (concat (direct 1 ys)) sort vec rseq) dirq [1 1] acc 0]
    (if (empty? vs)
      acc
      (let [[[v d] & vs'] vs
            add (* v (dirq (bit-xor 1 d)))
            acc' (mod (+ acc add) base)
            dirq' (update-in dirq [d] inc)]
        (recur vs' dirq' acc')))))
(defn main [] (doall (repeatedly (read-int) #(do (read-line) (println (solution (read-ints) (read-ints)))))))
