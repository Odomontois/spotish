(ns ^{:author "Odomontois"} hackerrank.algorithms.greedy.crush
  (:require [clojure.string :refer [split]])
  (:import (java.io Writer)))
(defprotocol Raisable
  (raise [tree a b from to k] "raise maximum from a to b")
  (get-max [this] "get current maximum"))
(defrecord node [mx add left right] Raisable
  (raise [this a b from to k]
    (cond (or (< b from) (> a to)) this
          (<= a from to b) (assoc this :mx (+ mx k) :add (+ add k))
          :otherwise
          (let [m (quot (+ a b) 2)
                left' (raise left a b from m k)
                right' (raise right a b (inc m) to k)
                mx' (+ add (max (get-max left') (get-max right')))]
            (->node mx' add left' right'))))
  (get-max [_] mx))
(extend-type Long Raisable
  (raise [this a b from to k]
    (if (<= a from to b) (+ this k) this))
  (get-max [this] this))

(defn build [a b]
  (if (= a b)
    0
    (let [m (quot (+ a b) 2)] (->node 0 0 (build a m) (build (inc m) b)))))

#_( Main )
(defn ^Long parse-int [^String s] (Long/parseLong s))
(defn read-int [] (parse-int (read-line)))
(defn read-ints [] (map parse-int (split (read-line) #"\s+")))
(defn main []
  (let [[n m] (read-ints)]
    (loop [tree (build 1 n) remains m]
      (if (zero? remains)
        (println (:mx tree))
        (let [[a b k] (read-ints)] (recur (raise tree a b 1 n k) (dec remains)))))))

#_( Utilities )
(def ^:dynamic node-prefix nil)
(def ^:dynamic node-add 0)

(defmethod print-method node [{:keys [mx add left right]} ^Writer w]
  (.write w (str (+ node-add mx)))
  (binding [node-prefix (str node-prefix "  ║") node-add (+ node-add add)]
    (.write w (str \newline node-prefix "╙"))
    (print-method left w))
  (binding [node-prefix (str node-prefix "  ") node-add (+ node-add add)]
    (.write w (str \newline node-prefix "╙"))
    (print-method right w)))