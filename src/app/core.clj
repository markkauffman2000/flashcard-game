(ns app.core)
(require '[clojure.edn :as edn])

(def sample-deck
  [{:id 1 :question "Clojure is a Lisp. true/false" :answer true}
   {:id 2 :question "Vectors are mutable. true/false" :answer false}
   {:id 3 :question "(+ 1 2) equals 3. true/false" :answer true}
   {:id 4 :question "you have no quest. true/false" :answer true}]
  )

;; load the deck and shuffle it.
(defn initialize-deck [] ;; this is so we can read a file here later on..
  (let [shuffled-deck (shuffle sample-deck)]
   shuffled-deck)
  )

(defn query-user [num-correct card]
  (println card)
  (+ 1 num-correct)
  )

(defn game-loop [deck]
  (flush)
;;  (let num-correct 0
;;    ( doseq [card deck]
;;      (query-user card num-correct)
;;     )
;;    )
;;    replacing the above with the following reduce. num-correct 0 is now just 0.

;;  (reduce (fn [num-correct card]
;;            (query-user num-correct card))
;;          0
;;          deck)
;;   We can get even simpler and get rid of the anonymous function.
    (reduce query-user 0 deck)

) ;; defn game-loop


(defn play-game []
  (println "Initializing deck...!")
  (let [shuffled-deck  (initialize-deck)
        final-score (game-loop shuffled-deck)]
    (println "Your final score is: " final-score)
    ) ;; end let

    "That's all folks!"
  )

(defn -main [& args]
  (case (count args)
    0 ( println (play-game))
    (println "wrong number of args")
   )
 )

(comment
  (app.core/-main "live_signal")  ;; should give wrong # of args...

  (app.core/-main)

  )
