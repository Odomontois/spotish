(ns hackerrank.utopian-tree)
(defn read-int [] (Long/parseLong (read-line)))
(defn tree
  ([i]         (tree i true 1))
  ([i odd acc] (if (== i 0) acc (recur (dec i) (not odd) (if odd (* 2 acc) (inc acc))))))
(dotimes [_ (read-int)] (println (tree (read-int))))
