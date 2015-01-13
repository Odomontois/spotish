(ns hackerrank.knapsack  (:require
  [clojure.set    :refer [union]]
  [clojure.string :refer [split]]))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(dotimes [t (first (read-ints))]
  (let [[n k]   (read-ints)
        produce (fn [s x] (do (println s) (union s (filter #(<= % k) (map #(+ x %) s)))))
        iter    (fn [s x] (let [s' (produce s x)] (if (= s s') s (recur s' x))))]
    (println  (reduce iter #{0} (read-ints)))))
