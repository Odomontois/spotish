(ns ^{:author "Odomontois"} eulerproject.problem491
  (:require [utils.iterations :refer [sum prod]]
            [utils.combinatorics :refer [combinations !]]))
(def n 10)
(defn sumHalf [xs] (-> xs sum (- 45) (mod 11) zero?))
(def digs (range n))
(def vars (->> (map sort (combinations (concat digs digs) n)) set (filter sumHalf)))
(defn perms [variant]
  (let [counts (map second (frequencies variant))]
    (/ (! (sum counts)) (prod counts))))
(defn red-perms [variant]
  (let [f (frequencies variant)
        counts (map second f)
        sumc (sum counts)
        first (sum (map second (assoc f 0 0)))
        perm (* (! (dec sumc)) first)]
    (/ perm (prod counts))))
(defn nums [variant]  (* (red-perms variant) (perms variant)))
(def result (sum (map nums vars)))

