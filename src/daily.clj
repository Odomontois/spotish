(ns daily
  (:require [clojure.java.io :as jio]))

(def refresh-ns (atom nil))
(def refresh-resource (atom nil))

(defn refresh []
  (when-let [ns @refresh-ns] (require [(symbol (name ns)) :reload true :refer :all]))
  (when-let [res @refresh-resource] (set! *in* (jio/reader (jio/resource res)))))

(defn str->map [s] (apply sorted-map (mapcat vector (iterate inc 0) s)))
