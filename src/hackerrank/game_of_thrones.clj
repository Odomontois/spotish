(ns ^{:author Odomontois} hackerrank.game-of-thrones)
(defn counter
  ([s] (counter s {}))
  ([s cnt]
    (if (empty? s) cnt
      (let [c (first s)]
        (recur (rest s)(assoc cnt c (inc (get cnt c 0))))))))
(defn palindromal? [s] (->> s counter (filter (comp odd? second)) count (>= 1)))
(println (if (palindromal? (read-line)) "YES" "NO"))