(ns ^{:author "Odomontois"} hackerrank.algorithms.string.palindrome-index)
(defn palindrome? [s] (= (seq s) (reverse s)))
(defn removes [s i] (concat (take i s) (drop (inc i) s)))
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn read-int [] (parse-int (read-line)))
(defn solution [str]
  (loop [begin 0 end (dec (count str))]
    (cond (>= begin end) -1
          (= (get str begin) (get str end)) (recur (inc begin) (dec end))
          (palindrome? (removes str begin)) begin
          :otherwise end)))
(defn single-test [] (println (solution (read-line))))
(defn main [] (doall (repeatedly (read-int) single-test)))

