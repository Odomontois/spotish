(ns ^{:author Odomontois} hackerrank.play-with-words)

(defn get' [s i j] (get (get s i) j))
(defn produce-line
  ([s prev-line i] (produce-line 1 prev-line i (dec i) [1]))
  ([s prev-line prev i j acc]
    (if (empty? prev) [acc prev]
      (let [that (if (= (get s i) (get s j))
                  (if-some [x (second prev-line)] (+ 2 x) 2)
                  (max (prev) (first prev-line)))]
      (recur s (rest prev-line) that i (dec j) (conj acc that))))))
(defn produce-table
  ([s] (produce-table s [1] [1] 1))
  ([s prev-line i acc]
    (if (= i (count s)) [prev-line acc]
      (let [[that-line v] (produce-line s prev-line i)]
        (recur s that-line (inc i) (conj acc v))))))

(defn main []
  (let [s (read-line)
        n (count s)
        sub (vec (repeatedly n #(transient (vec (repeat n 0)))))
        fill (fn [i j] (assoc! (get sub i) j
                         (if (= (get s i) (get s j))
                           (+ 2 (get' sub (inc i) (dec j)))
                           (max (get' sub (inc i) j) (get' sub i (dec j))))))]
    (dotimes [i n] (assoc! (get sub i) i 1))
    (dotimes [k n] (dotimes [i (- n k 1)] (fill i (+ k i 1))))
    (println (reduce max 0
               (for [i (range (dec n))
                     :let [left (get' sub 0 i)
                           right (get' sub (inc i) (dec n))]]
                 (* left right))))))