(ns ^{:author Odomontois} hackerrank.longest-increasing-subsequence)
(defn search-and-insert
  ([v e] (search v e 0 (count v)))
  ([v e l h]
    (let [m (quot (+ l h ) 2)]
      (cond
        (= l h)               (if (= l (dec (count v)))
                                (conj v e)
                                (assoc v (inc l) e))
        (< e (get v m))       (recur v e l m)
        (< l (dec (count v))) (recur v e m h)
        :else                 (conj v e)))))
(defn lis
  ([s] (lis s (sorted-map 0 0)))
  ([s m]
    (println s m)
    (if (empty? s) (->> m vals (reduce max))
      (let [e (first s)
            v (-> m (rsubseq < e) first second inc)]
        (println (rsubseq m < e))
        (recur (rest s) (assoc m e v))))))
(defn read-int [] (Integer/parseInt (read-line)))
(defn main [] (-> (read-int) (repeatedly read-int) lis println))
(main)