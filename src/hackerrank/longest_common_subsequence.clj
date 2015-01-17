(ns ^{:author Odomontois} hackerrank.longest-common-subsequence (:require [clojure.string :refer [split]]))
(def lcs'
  (memoize
    (fn [xs ys]
      (cond
        (or (empty? xs) (empty? ys)) []
        (= (first xs) (first ys))    (conj (lcs' (rest xs) (rest ys)) (first xs))
        :else                        (max-key count (lcs' xs (rest ys)) (lcs' (rest xs) ys))))))
(def lcs (comp reverse lcs'))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn main [] (read-ints) (apply println (lcs (read-ints) (read-ints))))
(main)