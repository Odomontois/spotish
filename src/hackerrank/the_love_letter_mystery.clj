(ns hackerrank.the-love-letter-mystery)
(defn pal-val [s] (let [s' (map int s)] (/ (reduce + (map #(Math/abs %) (map - s' (reverse s')))) 2)))
(dotimes [t (Long/parseLong (read-line))] (println (pal-val (read-line))))