(ns ^{:author Odomontois} hackerrank.favorite-sequence
  (:require [clojure.string :refer [split]]
            [clojure.set :refer [difference]]))
(defn read-int [] (Long/parseLong (read-line)))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
(defn read-seqs [] (repeatedly (read-int) #(do (read-line) (read-ints))))
(defn children [l] (map #(hash-map %1 [%2]) l (rest l)))
(defn children-map [seqs] (apply merge-with concat (mapcat children seqs)))
(defn prelim-map [seqs] (frequencies (apply concat (map rest seqs))))
(defn dec-prelim [[prelim order] x]
  (let [v (get prelim x)]
    (if (= 1 v)
      [(dissoc prelim x) (conj order x)]
      [(assoc prelim x (dec v)) order])))
(defn guess-step [children prelim order acc]
  (if (empty? order)
    acc
    (let [next (first order)
          free (get children next)
          order' (disj order next)
          [prelim' order''] (reduce dec-prelim [prelim order'] free)]
      (recur children prelim' order'' (conj acc next)))))
(defn guess-sequence [seqs]
  (let [children  (children-map seqs)
        prelim    (prelim-map seqs)
        order     (difference (apply sorted-set (map first seqs)) (apply hash-set (keys prelim)))]
  (guess-step children prelim order [])))
(defn main [] (apply println (guess-sequence (read-seqs))))
(main)

