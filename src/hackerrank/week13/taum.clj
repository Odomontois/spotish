(ns ^{:author Odomontois} hackerrank.week13.taum
  (:require [clojure.string :refer [split]]))

(defn read-int [] (Long/parseLong (read-line)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn main []
  (dotimes [_ (read-int)]
    (let [[b w] (read-ints)
          [x y z] (read-ints)
          x' (min x (+ y z))
          y' (min y (+ x z))]
      (println (+ (* x' b) (* y' w))))))
(main)
