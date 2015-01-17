(ns ^{:author Odomontois} utils.combinatorics)
(defn combinations [s k]
  (case k
    0 []
    1 (map list s)
    (if (empty? s) s
      (lazy-cat
        (for [u (combinations (rest s) (dec k))] (cons (first s) u) )
        (combinations (rest s) k)))))