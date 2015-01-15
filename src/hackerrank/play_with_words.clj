(ns ^{:author Odomontois} hackerrank.play-with-words)

(defn produce-line
  ([s prev-line i] (produce-line s prev-line 1 i (dec i) [1]))
  ([s prev-line prev i j acc]
    (if (empty? (rest prev-line)) [acc prev]
      (let [that (if (= (get s i) (get s j))
                  (+ 2 (first prev-line))
                  (max prev (second prev-line)))]
        (recur s (rest prev-line) that i (dec j) (conj acc that))))))

(defn produce-table
  ([s] (produce-table s [0 1] [1] 1))
  ([s prev-line acc i]
    (if (= i (count s)) [(vec (rest prev-line)) acc]
      (let [[that-line v] (produce-line s prev-line i)]
        (recur s (cons 0 that-line) (conj acc v) (inc i))))))

(defn main []
  (let [s (read-line)
        n (count s)
        [end start] (produce-table s)]
    (println (reduce max 0
               (for [i (range (dec n))
                     :let [left  (get start i)
                           right (get end (- n i 2))]]
                 (* left right))))))
(main)