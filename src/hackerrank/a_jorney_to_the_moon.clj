(ns hackerrank.a-jorney-to-the-moon (:require [clojure.string :refer [split]]))
(defn combinations [s k]
  (case k
    0 []
    1 (map list s)
    (if (empty? s) s
                   (lazy-cat
                     (for [u (combinations (rest s) (dec k))] (cons (first s) u) )
                     (combinations (rest s) k)))))
(defn init-sets [n] (vec (repeat n 1)))
(defrecord Set [root count path])
(defn get-set [sets i]
  (let [v (sets i)]
    (if (pos? v) (->Set i v [])
      (let [s (get-set sets (- v))]
        (update-in s [:path] conj i)))))
(defn merge-sets
  ([sets i j]
  (let [si         (get-set sets i)
        sj         (get-set sets j)
        [prim sec] (if (> (:count si) (:count sj))
                     [si sj] [sj si])
        upd-idx    (conj (concat (:path si) (:path sj)) (:root sec))
        root       (:root prim)
        count      (+ (:count si) (:count sj))]
    (apply assoc sets root count (interleave upd-idx (repeat (- root))))))
  ([sets [i j]] (merge-sets sets i j)))
(defn variants [sets] (reduce + (for [[i j] (combinations (filter pos? sets) 2)] (* i j))))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn solution [n merges] (variants (reduce merge-sets (init-sets n) merges)))
(defn main []
  (let [[n k]  (read-ints)
        merges (repeatedly k read-ints)]
    (println (solution n merges))))