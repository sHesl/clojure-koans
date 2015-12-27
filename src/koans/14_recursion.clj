(ns koans.14-recursion
  (:require [koan-engine.core :refer :all]))
 
(defn is-even? [n]
  (if (or (= (mod n 2) 0) (= n 0))
    true
    false))

(defn recursive-reverse [coll]
  (loop [coll coll
          acc ()]
    (if (seq coll)
      (recur (rest coll) (conj acc (first coll)))
      acc)))

(defn factorial [n]
  (let [target (range 1 (inc n))]
    (reduce *' target)))

(defn factorial-x 
  ([n]
    (let [n-dec (dec n)]
      (factorial-x (*' n n-dec) n-dec)))
  ([accum n]
    (let [n-dec (dec n)]
      (factorial-x (* accum n) n-dec))))
 
(meditations
  "Recursion ends with a base case"
  (= true (is-even? 0))

  "And starts by moving toward that base case"
  (= false (is-even? 1))

  "Having too many stack frames requires explicit tail calls with recur"
  (= false (is-even? 100003N))

  "Reversing directions is easy when you have not gone far"
  (= '(1) (recursive-reverse [1]))

  "Yet it becomes more difficult the more steps you take"
  (= '(5 4 3 2 1) (recursive-reverse [1 2 3 4 5]))

  "Simple things may appear simple."
  (= 1 (factorial 1))

  "They may require other simple steps."
  (= 2 (factorial 2))

  "Sometimes a slightly bigger step is necessary"
  (= 6 (factorial 3))

  "And eventually you must think harder"
  (= 24 (factorial 4))

  "You can even deal with very large numbers"
  (< 1000000000000000000000000N (factorial 1000N))

  "But what happens when the machine limits you?"
  (< 1000000000000000000000000N (factorial 100003N)))
