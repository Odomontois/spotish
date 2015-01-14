(ns ^{:author Odomontois} hackerrank.play-with-words)
(def s (read-line))
(def n (count s))
(def sub (vec (repeatedly n #(transient (vec (repeat n 0))))))
(defn get' [i j] (get (get sub i) j))
(defn fill [i j] (assoc! (get sub i) j
                   (cond
                     (> i j)                 0
                     (= i j)                 1
                     (= (get s i) (get s j)) (+ 2 (get' (inc i) (dec j)))
                     :else                   (max (get' (inc i) j) (get' i (dec j))))))
(dotimes [k n] (dotimes [i (- n k)] (fill i (+ k i))))
(println (reduce max 0
           (for [i (range (dec n))
                 :let [left  (get-in sub [0 i])
                       right (get-in sub [(inc i) (dec n)])]]
             (* left right))))
