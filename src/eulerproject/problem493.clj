(ns ^{:author "Odomontois"} eulerproject.problem493)
(def ! (vec (reductions * 1N (range 1 80))))

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

(def u (map frequencies (shares 10 7 20)))

