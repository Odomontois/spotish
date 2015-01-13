(ns hackerrank.maximizing-xor)
(defn read-int [] (Long/parseLong (read-line)))
(def rng (range (read-int) (inc (read-int))))
(println (reduce max (for [i rng j rng] (bit-xor i j))))
