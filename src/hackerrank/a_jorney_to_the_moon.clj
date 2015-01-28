(ns hackerrank.a-jorney-to-the-moon
  (:require [clojure.string :refer [split]]))
(defprotocol SetCell
  (get-set [c sets])
  (root? [c]))
(defrecord RootCell [^long idx ^long count]
  SetCell
  (get-set [this sets] [sets this])
  (root? [_this] true))
(defrecord RefCell [^long idx ^long parent]
  SetCell
  (get-set [_this sets]
    (let [p (sets parent)
          [sets' root] (get-set p sets)]
      (if (root? p)
        [sets' root]
        [(assoc sets' idx (->RefCell idx (:idx root))) root])))
  (root? [_this] false))
(defn get-set-idx [sets i] (get-set (sets i) sets))
(defn init-sets [n] (vec (for [i (range n)] (->RootCell i 1))))
(defn merge-sets
  ([sets [^long i ^long j]]
    (let [[sets'  si] (get-set-idx sets i)
          [sets'' sj] (get-set-idx sets' j)]
      (if (= si sj)
        sets
        (let [[root sec] (if (> (:count si) (:count sj)) [si sj] [sj si])
              rc         (->RootCell (:idx root) (+ (:count si) (:count sj)))]
          (assoc sets'' (:idx root) rc (:idx sec) (->RefCell (:idx sec) (:idx rc))))))))
(defn merge-all [n merges] (reduce merge-sets (init-sets n) merges))
(defn square [x] (* x x))
(defn variants [sets]
  (let [counts (->> sets (filter root?) (map :count))
        squares (map square counts)
        csum (reduce + counts)
        ssum (reduce + squares)]
    (quot (- (square csum) ssum) 2)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn solution [n merges] (variants (merge-all n merges)))
(defn main []
  (let [[n k] (read-ints)
        merges (repeatedly k read-ints)]
    (println (solution n merges))))