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
(defn sum [c] (reduce + 0 c))
(defn prod [c] (reduce * 1 c))
(defn groups [s k n] (map frequencies (shares s k n)))
(defn pow [x p] (prod (repeat p x)))
(defn grc [m s r] (pow (C s m) r))

(defn vars [s k n]
  (for [g (groups s k n)
        :let [grcs (for [[m r] g] (grc m s r))
              col (map second g)
              colcnt (sum col)
              cc (/ (! k) (! (- k colcnt)) (prod (map ! col)))
              ]]
    [colcnt (* (prod grcs) cc)]))

(defn bycnt [s k n] (for [[c cnts] (group-by first (vars s k n))] [c (sum (map second cnts))]))
(defn result [s k n] ( / (sum (for [[k c] (bycnt s k n)] (* k c))) (C (* s k) n)))

;(def result (sum (vars 10 7 20)))



