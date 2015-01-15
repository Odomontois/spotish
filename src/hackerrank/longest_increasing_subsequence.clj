(ns ^{:author Odomontois} hackerrank.longest-increasing-subsequence)
(defn- check-insert [v i e](if (>= i (count v)) (conj v e) (assoc v i e)))
(defn- search-and-insert
  ([v e] (search-and-insert v e 0 (count v)))
  ([v e l h]
    (let [m (quot (+ l h) 2)]
      (cond
        (>= l (dec h))   (check-insert v (inc l) e)
        (<= e (get v m)) (recur v e l m)
        :else            (recur v e m h)))))
(defn lis
  ([s] (lis s [[0 nil]]))
  ([s v]
    (if (empty? s)
      (dec (count v))
      (recur (rest s) (search-and-insert v (first s))))))
(defn- read-int [] (Integer/parseInt (read-line)))
(defn main [] (-> (read-int) (repeatedly read-int) lis println))

(main)