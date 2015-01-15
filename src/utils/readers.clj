(ns ^{:author Odomontois} utils.readers (:require [clojure.string :refer [split]]))
(defn read-ints [] (map #(Long/parseLong %) (split (read-line) #"\s+")))
