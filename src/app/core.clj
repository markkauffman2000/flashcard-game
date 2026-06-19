(ns app.core)
(require '[clojure.edn :as edn])

(def sample-deck
  [{:id 1 :question "Clojure is a Lisp. true/false" :answer true}
   {:id 2 :question "Vectors are mutable. true/false" :answer false}
   {:id 3 :question "(+ 1 2) equals 3. true/fasle" :answer true}]
  )

;; load the deck and shuffle it.
(defn initialize-deck [] ;; this is so we can read a file here later on..
  (let [shuffled-deck (shuffle sample-deck)]
   shuffled-deck)
  )


(defn process-node [input]
  (str "processing node with input: " input))

(defn get-user-number []
  (println "Enter your integer guess between 1 and 100")
  (let [input (read-line)
    number (edn/read-string input)]
  (if (integer? number)
    number
    (println "That wasn't a valid integer"))
  ))



;; Generate a number between 1 and 100.
(defn generate-num []
  (inc (rand-int 99)))

(defn game-loop [target]
  (flush)
  (let [guess (get-user-number)]
    (cond
      (not (integer? guess ))
      (do
        (println "Invalid input. Enter a whole number.")
        (recur target))

      (== guess target)
      (println "Correct! You win!!``")

      :else
     (do
      (println "Guess" guess (if (< guess target) "To low!" "Too high!!"))
      (recur target))
    ) ;; cond

   ) ;; let

  ) ;; defn generate-num


(defn play-game []
  (println "Initializing deck...!")
  (println (initialize-deck))
"""  (let [the-num (generate-num)]
    (game-loop the-num)
    ) """
    "That's all folks!"
  )

(defn -main [& args]
  (case (count args)
    0 ( println (play-game))
    1 ( println (process-node (first args)) )
    (println "wrong number of args")
   )
 )

(comment
  (app.core/-main "live_signal")

  (app.core/-main)

  (app.core/get-user-number)
  )
