(ns ^{:author Odomontois} utils.iterations)
(defn count-compare
  ([op x y] (count-compare op x y 0))
  ([op x y cnt]
    (if (op (first y) (first x))
      (recur op x (rest y) (inc cnt))
      (lazy-seq (cons cnt (count-compare op (rest x) y cnt))))))
