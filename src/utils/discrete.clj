(ns ^{:author Odomontois} utils.discrete)
(defn prime? [n] (every? #(not= 0 (mod n %)) (take-while (fn [x] (<= (* x x) n)) primes)))
(def primes (cons 2 (filter prime? (iterate #(+ 2 %) 3))))
