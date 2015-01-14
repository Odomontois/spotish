(ns ^{:author Odomontois} hackerrank.play-with-words)
(def s (read-line))
(def n (count s))
(def sub (into-array (repeatedly n #(int-array n))))
(defn fill [i j] (aset (aget sub i) j
 (cond
  (> i j)                 0
  (= i j)                 1
  (= (get s i) (get s j)) (+ 2 (aget (aget sub (inc i)) (dec j)))
  :else                   (max (aget (aget sub (inc i)) j      )
                               (aget (aget sub i     )  (dec j))))))
(dotimes [k n] (dotimes [i (- n k)] (fill i (+ k i))))
(println (reduce max 0
  (for [i (range (dec n))
        :let [left  (aget (aget sub  0)      i)
              right (aget (aget sub (inc i)) (dec n))]]
    (* left right))))
