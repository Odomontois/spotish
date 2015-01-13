(ns hackerrank.angry-chidren)
(defn read-ints [n] (repeatedly n #(Long/parseLong (read-line))))
(let [ [n k] (read-ints 2)
       cs   (sort (read-ints n))
       unf  (map - (drop (dec k) cs) cs)]
  (println (apply min unf)))

