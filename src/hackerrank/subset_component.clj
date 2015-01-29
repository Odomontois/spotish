(ns ^{:author Odomontois} hackerrank.subset-component
  (:require [clojure.string :refer [split]]))
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn produce [sets x]
  (conj (filter #(zero? (bit-and x %)) sets)
        (reduce bit-or x
                (filter #(not (zero? (bit-and x %))) sets))))
(defn solution [nums]
  (loop [xs nums n 1 acc [nil]]
    (if (empty? xs)
      acc
      (recur (rest xs) (* 2 n)
        (loop [i 0 ac acc]
          (if (= i n)
            ac
            (recur (inc i) (conj ac (produce (ac i) (first xs))))))))))

(def big-limit (reduce * (repeat 63 2N)))
(defn parse-int [s]
  (let [b (bigint s)]
    (if (< b big-limit) (long b) (- b big-limit big-limit))))

(defn read-ints [] (map parse-int (split (read-line) #"\s+")))
(defn size [v] (- (* 64 (count v)) (reduce + (map #(dec (Long/bitCount %)) (apply concat v)))))
(defn main []
  (read-line)
  (-> (read-ints) solution size println))
