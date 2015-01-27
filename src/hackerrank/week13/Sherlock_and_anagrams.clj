(ns ^{:author Odomontois} hackerrank.week13.Sherlock-and-anagrams)
(defn anagramic [s]
  (reduce + 0
    (map (comp (fn [x] (quot (* x (dec x)) 2)) second)
      (frequencies
        (for [i (range 1 (inc (count s))) j (range i)]
          (frequencies (subs s j i)))))))
(defn main []
  (dotimes [_ (Long/parseLong (read-line))]
    (println (anagramic (read-line)))))
