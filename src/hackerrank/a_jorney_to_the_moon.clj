(ns hackerrank.a-jorney-to-the-moon
  (:require [clojure.string :refer [split]]))
(defn init-sets [n] (vec (repeat n 1)))
(defn get-set [sets i]
  (let [v (sets i)]
    (if (pos? v)
      i
      (let [s (get-set sets (- v))]
        (assoc! sets i (- s))
        s))))
(defn merge-sets
  ([sets ^long i ^long j]
    (let [si (get-set sets i)
          sj (get-set sets j)
          sa (+ (sets si) (sets sj))]
      (if (> (sets si) (sets sj))
        (assoc! sets sj (- si) si sa)
        (assoc! sets si (- sj) sj sa)))))
(defn square [x] (* x x))
(defn variants [sets]
  (let [counts (filter pos? sets)
        squares (map square counts)
        csum (reduce + counts)
        ssum (reduce + squares)]
    (quot (- (square csum) ssum) 2)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn solution [n merges]
  (let [sets (transient (init-sets n))]
    (doseq [[i j] merges] (merge-sets sets i j))
    (variants (persistent! sets))))
(defn main []
  (let [[n k] (read-ints)
        merges (repeatedly k read-ints)]
    (println (solution n merges))))