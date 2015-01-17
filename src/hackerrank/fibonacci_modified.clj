(ns ^{:author Odomontois} hackerrank.fibonacci-modified (:require [clojure.string :refer [split]]))
(defn fib [a b n] (cond (= n 1) a (= n 2) b :else (recur b (+ a (* b b)) (dec n))))
(println (str (apply fib (map bigint (split (read-line) #"\s+")))))
