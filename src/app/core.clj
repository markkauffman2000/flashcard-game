(ns app.core
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

;; Notice how we've added a :type key, and separated the question text
;; from how it's presented to the user.
;; load the deck and shuffle it.
;;
(def sample-deck
  [{:id 1 :type :tf :question "Clojure is a Lisp." :answer true}
   {:id 2 :type :tf :question "Vectors are mutable." :answer false}
   {:id 3 :type :tf :question "(+ 1 2) equals 3." :answer true}

   {:id 4
    :type :mc
    :question "What is my favorite color?"
    :options [["1" "red"] ["2" "blue"] ["3" "yellow"]]
    :answer "1"}

   {:id 5
    :type :mc
    :question "Which data structure is immutable in Clojure?"
    :options [["A." "Vectors"] ["B." "Lists"] ["C." "Maps"] ["D." "All of them"]]
    :answer "D."}
])


(defn initialize-deck [] ;; this is so we can read a file here later on..
  (let [shuffled-deck (shuffle sample-deck)]
   shuffled-deck)
  )

;; CONSIDERATION 1: Dedicated function to just show the card based on its type
(defn display-card [card]
  (println "\n========================================")
  (println "Question:" (:question card))
  (case (:type card)
    :tf (println "[ True or False? ]")
    ;; We pull out 'label' and 'text' directly from each pair
    :mc (doseq [[label text] (:options card)]
          (println (str "  " label " " text))
          )
    )
  )

(defn clean-label [label]
  ;; Strips trailing periods/spaces and lowercases for safe comparison
  (str/lower-case (str/replace label #"[.\s]" "")))

;; CONSIDERATION 2: Input helper checks the card type to know what to ask for
(defn get-user-answer [card]
  (let [card-type (:type card)]
    (loop []
      (case card-type
        :tf (print "Your answer (t/f): ")
        :mc (let [raw-labels (map first (:options card))
                  prompt-str (str/join "/" raw-labels)]
              (print (str "Your answer (" prompt-str "): "))))
      (flush)

      (let [input (str/lower-case (str/trim (read-line)))]
        (case card-type
          :tf (cond
                (or (= input "t") (= input "true")) true
                (or (= input "f") (= input "false")) false
                :else (do (println "Invalid input. Please enter 't' or 'f'.") (recur)))

          :mc (let [valid-choices (set (map clean-label (map first (:options card))))]
                (if (contains? valid-choices (clean-label input))
                  input
                  (do
                    (println "Invalid input. Please choose a valid option.")
                    (recur)))))
        )
      )
    )
  )


(defn query-user [num-correct card]
  (display-card card)
  (let [user-ans (get-user-answer card)
        correct-ans (:answer card)]

    ;; Compare cleaned versions so casing or punctuation variations don't break matches
    (if (= (clean-label user-ans) (clean-label correct-ans))
      (do
        (println "Correct! 🎉")
        (inc num-correct))
      (do
        (println "Incorrect. ❌ The correct answer was:" correct-ans)
        num-correct))))

(defn game-loop [deck]
  (flush)
  (reduce query-user 0 deck))

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
