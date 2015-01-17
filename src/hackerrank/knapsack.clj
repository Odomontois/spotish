(ns hackerrank.knapsack
  (:require  [clojure.set    :refer [union]]
             [clojure.string :refer [split]])
  (:import java.util.PriorityQueue))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(dotimes [t (first (read-ints))]
  (let [[n k]   (read-ints)
        xs      (read-ints)
        q       (PriorityQueue. [0])]
    (println (loop [prev nil] (if (empty? q) prev
      (let [next (.poll q)]
        (when-not (= next prev) (some->> (filter #(<= % k) (map #(+ next %) xs)) (.addAll q)))
        (recur next)))))))

