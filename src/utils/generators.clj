(ns ^{:author Odomontois} utils.generators)
(defn rand-char []
  (let [low  (int \0)
        high (int \z)]
    (char (+ low (rand-int (- high low))))))

(defn print-rand-string [n] (println (apply str (repeatedly n rand-char))))
