(ns ^{:author Odomontois} hackerrank.ACM-ICPC-team
  (:require [clojure.string :refer [split]])
  (:import [java.math BigInteger]))
(defn combinations [s k]
  (case k
    0 []
    1 (map list s)
    (if (empty? s) s
      (lazy-cat
        (for [u (combinations (rest s) (dec k))] (cons (first s) u) )
        (combinations (rest s) k)))))

(defn read-bin [](BigInteger. (read-line) 2))
(defn and-len [^BigInteger x ^BigInteger y] (.bitCount (.or x y)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn topics [a] (for [[x y] (combinations a 2)] (and-len x y)))
(defn main []
  (let [[n m] (read-ints)
        a     (repeatedly n read-bin)
        top   (topics a)
        m     (reduce max top)
        c     (count (filter #(= m %) top))]
    (println m)
    (println c)))
(main)