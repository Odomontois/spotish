(ns ^{:author Odomontois} utils.combinatorics)
(defn combinations [s k]
  (case k
    0 []
    1 (map list s)
    (if (empty? s) s
                   (lazy-cat
                     (for [u (combinations (rest s) (dec k))] (cons (first s) u))
                     (combinations (rest s) k)))))

(def ! (vec (reductions * 1N (range 1 100))))

(defn C [n k] (/ (! n) (! k) (! (- n k))))

(defn shares
  ([h k n]
   (if (zero? n)
     '(nil)
     (when (and (pos? n) (pos? k))
       (let [low (quot (+ n (dec k)) k)
             high (inc (min h n))]
         (for [f (range low high)
               r (shares f (dec k) (- n f))]
           (cons f r))))))
  ([k n] (shares n k n)))