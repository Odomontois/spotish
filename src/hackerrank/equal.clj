(ns ^{:author Odomontois} hackerrank.equal (:require [clojure.string :refer [split]]))
(defn chocknum [n] (+ (quot n 5) (quot (inc (mod n 5))2 )))
(defn equate [x]
  (let [m   (reduce min x)
        res (for [low (range 5)
                  :let [res (map (comp chocknum #(+ (- % m) low)) x)]]
              (reduce + res))]
    (reduce min res)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn read-int [] (Long/parseLong (read-line)))
(defn main []
  (dotimes [_ (read-int)]
    (read-line)
    (println (equate (read-ints)))))
(main)