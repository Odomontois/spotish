(ns ^{:author "Odomontois"} hackerrank.algorithms.greedy.crush
  (:require [clojure.string :refer [split]])
  (:import (java.io Writer)))
(defprotocol Raisable (raise [tree a b k] "raise maximum from a to b"))
(defrecord node [mx add from to left right] Raisable
  (raise [this a b k]
    (cond (or (< b from) (> a to)) this
          (<= a from to b) (assoc this :mx (+ mx k) :add (+ add k))
          :otherwise
          (let [left' (raise left a b k)
                right' (raise right a b k)
                mx' (+ add (max (:mx left') (:mx right')))]
            (->node mx' add from to left' right')))))
(defrecord leaf [mx index] Raisable
  (raise [this a b k]
    (if (<= a index b) (->leaf (+ mx k) index) this)))

(defn build [a b]
  (if (= a b)
    (->leaf 0 a)
    (let [m (quot (+ a b) 2)] (->node 0 0 a b (build a m) (build (inc m) b)))))

#_( Main )
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn read-int [] (parse-int (read-line)))
(defn read-ints [] (map parse-int (split (read-line) #"\s+")))
(defn main []
  (let [[n m] (read-ints)]
    (loop [tree (build 1 n) remains m]
      (if (zero? remains)
        (println (:mx tree))
        (let [[a b k] (read-ints)] (recur (raise tree a b k) (dec remains)))))))

#_( Utilities )
(def ^:dynamic node-prefix nil)
(def ^:dynamic node-add 0)

(defmethod print-method node [{:keys [mx add from to left right]} ^Writer w]
  (.write w (str \newline node-prefix "╙(" (+ node-add mx) ")/" from \: to \/))
  (binding [node-prefix (str node-prefix "  ║") node-add (+ node-add add)] (print-method left w))
  (binding [node-prefix (str node-prefix "  ") node-add (+ node-add add)] (print-method right w)))

(defmethod print-method leaf [{:keys [mx index]} ^Writer w] (.write w (str \newline node-prefix "╙(" (+ node-add mx) ")/" index \/)))
