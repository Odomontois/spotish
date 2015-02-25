(ns ^{:author Odomontois} utils.discrete
  (:import (java.io Writer)))
(declare primes)
(defn prime? [n] (every? #(not= 0 (mod n %)) (take-while (fn [x] (<= (* x x) n)) primes)))
(def primes (cons 2 (filter prime? (iterate #(+ 2 %) 3))))
(defn- join-nums [is] (apply str is))

(defrecord Periodic [whole start period]
  Object
  (toString [_] (str whole "," (join-nums start) "(" (join-nums period) ")")))
(defmethod print-method Periodic [x ^Writer writer] (.write writer (.toString x)))

(defn fraction-period [num den base]
  (loop [num num prev {num 0} step 1 acc []]
    (let [num' (* num base)
          d (quot num' den)
          r (rem num' den)
          acc' (conj acc d)]
      (if (contains? prev r)
        (split-at (prev r) acc')
        (recur r (assoc prev r step) (inc step) acc')))))

(defn periodic [f & {:keys [base] :or {base 10}}]
  (let [num (numerator f)
        den (denominator f)
        whole (quot num den)
        num' (- num (* den whole))
        frac-per (fraction-period num' den base)
        [start period] (map vec frac-per)]
    (->Periodic whole start period)))
