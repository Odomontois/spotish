(ns ^{:author Odomontois} utils.iterations)
(defn sum [c] (reduce + 0 c))
(defn prod [c] (reduce * 1 c))
(defn pow [x p] (prod (repeat p x)))
(defn fpow
  ([x p] (fpow x p 1))
  ([x p acc] (if (zero? p) acc (recur (* x x) (quot p 2) (if (even? p) acc (* acc x))))))

(defn count-compare
  ([op x y] (count-compare op x y 0))
  ([op x y cnt]
   (if (op (first y) (first x))
     (recur op x (rest y) (inc cnt))
     (lazy-seq (cons cnt (count-compare op (rest x) y cnt))))))
