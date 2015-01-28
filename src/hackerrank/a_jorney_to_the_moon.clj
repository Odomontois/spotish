(ns hackerrank.a-jorney-to-the-moon
  (:require [clojure.string :refer [split]]))
(defn init-sets [n] (vec (repeat n 1)))
(defn get-set [sets i]
  (let [v (sets i)]
    (if (pos? v)
      [sets i]
      (let [[sets' s] (get-set sets (- v))]
        [(assoc sets' i (- s)) s]))))
(defn merge-sets
  ([sets [^long i ^long j]]
    (let [[sets' si] (get-set sets i)
          [sets'' sj] (get-set sets' j)
          sa (+ (sets si) (sets sj))]
      (cond
        (= si sj) sets
        (> (sets si) (sets sj)) (assoc sets'' sj (- si) si sa)
        :else (assoc sets'' si (- sj) sj sa)))))
(defn merge-all [n merges] (reduce merge-sets (init-sets n) merges))
(defn square [x] (* x x))
(defn variants [sets]
  (let [counts (filter pos? sets)
        squares (map square counts)
        csum (reduce + counts)
        ssum (reduce + squares)]
    (quot (- (square csum) ssum) 2)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn solution [n merges] (variants (merge-all n merges)))
(defn main []
  (let [[n k] (read-ints)
        merges (repeatedly k read-ints)]
    (println (solution n merges))))