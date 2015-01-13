(ns hackerrank.alternating-characters)
(defn count-repeat [s] (count (filter identity (map = s (rest s)))))
(dotimes [t (Long/parseLong (read-line))] (println (count-repeat (read-line))))