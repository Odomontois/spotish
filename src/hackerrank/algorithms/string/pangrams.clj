(ns ^{:author "Odomontois"} hackerrank.algorithms.string.pangrams)
(defn main [] (println (if (-> (read-line) .toUpperCase set (disj \space) count (= 26)) "pangram" "not pangram")))
