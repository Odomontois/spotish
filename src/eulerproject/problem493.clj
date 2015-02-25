(ns ^{:author "Odomontois"} eulerproject.problem493
  (:require [utils.combinatorics :refer [shares C !]]
            [utils.iterations :refer [sum prod fpow] :rename {fpow pow}]))


(defn groups [s k n] (map frequencies (shares s k n)))

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
(defn result [s k n] (/ (sum (for [[k c] (bycnt s k n)] (* k c))) (C (* s k) n)))

(when (System/getenv "CLOJURE_EXECUTE") (println (double (result 10 7 20))))


