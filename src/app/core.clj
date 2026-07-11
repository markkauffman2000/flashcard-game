(ns app.core
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

;; Notice how we've added a :type key, and separated the question text
;; from how it's presented to the user.
(def sample-deck
  [{:id 1 :type :tf :question "Clojure is a Lisp." :answer true}
   {:id 2 :type :tf :question "Vectors are mutable." :answer false}
   {:id 3 :type :tf :question "(+ 1 2) equals 3." :answer true}
   ;; This multiple-choice card slots in perfectly now!
   {:id 4 :type :mc :question "What is my favorite color?" :options "a: red / b: blue / c: yellow" :answer "a"}])

;; load the deck and shuffle it.
(defn initialize-deck [] ;; this is so we can read a file here later on..
  (let [shuffled-deck (shuffle sample-deck)]
   shuffled-deck)
  )

;; CONSIDERATION 1: Dedicated function to just show the card based on its type
(defn display-card [card]
  (println "\n========================================")
  (println "Question:" (:question card))
  ;; Use 'case' to change the presentation depending on the question type
  (case (:type card)
    :tf (println "[ True or False? ]")
    :mc (println (:options card))))

;; CONSIDERATION 2: Input helper checks the card type to know what to ask for
(defn get-user-answer [card-type]
  (loop []
    (case card-type
      :tf (print "Your answer (t/f): ")
      :mc (print "Your answer (a/b/c): "))
    (flush)
    (let [input (str/lower-case (str/trim (read-line)))]
      (case card-type
        ;; Validation loop for True/False
        :tf (cond
              (or (= input "t") (= input "true")) true
              (or (= input "f") (= input "false")) false
              :else (do (println "Invalid input. Please enter 't' or 'f'.") (recur)))

        ;; Validation loop for Multiple Choice
        :mc (if (contains? #{"a" "b" "c"} input)
              input
              (do (println "Invalid input. Please enter 'a', 'b', or 'c'.") (recur)))))))

(defn query-user [num-correct card]
  ;; First, display the card text
  (display-card card)

  ;; Next, grab the type-specific validated answer
  (let [user-ans (get-user-answer (:type card))
        correct-ans (:answer card)]

    ;; Finally, score it
    (if (= user-ans correct-ans)
      (do
        (println "Correct! 🎉")
        (inc num-correct))
      (do
        (println "Incorrect. ❌ The correct answer was:" correct-ans)
        num-correct))))

(defn game-loop [deck]
  (flush)
    (reduce query-user 0 deck)

) ;; defn game-loop


(defn play-game []
  (println "Initializing deck...!")
  (let [shuffled-deck  (initialize-deck)
        final-score (game-loop shuffled-deck)]
    (println "Your final score is: " final-score "out of" (count shuffled-deck))
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
