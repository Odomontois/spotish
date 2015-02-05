(ns daily)

(defn str->map [s] (apply sorted-map (mapcat vector (iterate inc 0) s)))