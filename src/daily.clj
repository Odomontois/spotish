(ns daily)

(def refresh-ns (atom "hackerrank.algorithms.string.pangrams"))

(defn refresh [] (require [(symbol @refresh-ns) :reload true :refer :all]))

(defn str->map [s] (apply sorted-map (mapcat vector (iterate inc 0) s)))
