mkdir flashcard-game
cd flashcard-game
echo "{}" > deps.edn

mkdir -p src/app

[mbk@fedora-s-1vcpu-1gb-sfo2 flashcard-game]$ cat src/app/core.clj 
                                                                    
(ns app.core)
                                                                      
(str "processing node with input: " input))

(println (process-node (first args))))

(app.core/-main "live_signal")

[mbk@fedora-s-1vcpu-1gb-sfo2 flashcard-game]$

cd /path/to/your/clojure-project 
emacs -nw src/app/core.clj

Jack in
SPC m '

SPC m e b  <— load the file to get the namespace set up.

Go to end of a form. Evaluate inline.
SPC m e e

Save everything, close CIDER and emacs. Write Quit All :wqa
:wqa


1. Don't forget to run 'doom sync' and restart Emacs after modifying init.el or
   packages.el in ~/.config/doom. This is never necessary for config.el.

2. If something goes wrong, run `doom doctor` to diagnose common issues with
   your environment, setup, and config.

3. Use 'doom upgrade' to update Doom. Doing it any other way will require
   additional steps (see 'doom help upgrade').

4. Access Doom's documentation from within Emacs via 'SPC h d h' or 'C-h d h'
   (or 'M-x doom/help').

Setting up that side-by-side view in Doom Emacs is entirely managed through the window (w) leader key menu.
Assuming you currently have your app.core file open and taking up the whole screen, here is how to arrange it:
1. Create the Split
Ensure you are in Normal mode (press Esc), then press:
SPC w v
	•	SPC (Space) opens the Doom leader menu.
	•	w selects the Window management menu.
	•	v creates a vertical split, dividing your screen into a left and right pane.
Doom will automatically move your cursor focus into the new right-hand pane.
2. Open the REPL in the New Pane
While your cursor is in the new right-hand pane, you want to swap the buffer from your source code to the CIDER REPL:
SPC b b
	•	This opens the buffer search at the bottom of the screen.
	•	Start typing cider-repl until the REPL buffer is highlighted, then press Enter.
You now have your code on the left and your live output on the right!
3. Moving Between the Panes
Since Doom Emacs uses Vim navigation keys, you can jump between your left and right windows just like you move your cursor:
	•	SPC w h moves focus to the left pane.
	•	SPC w l moves focus to the right pane. (You can also use SPC w w to just quickly bounce back and forth between them).
Closing a Pane
If you ever want to collapse the split and go back to a single window, make sure your cursor is in the pane you want to get rid of, and press:
SPC w c (Window close).

1. Quit the CIDER REPL

Ensure your cursor is in your Clojure source code buffer or the REPL buffer, and press:
SPC m r q(Localleader -> repl -> quit)
Fallback method: If a keybinding ever fails or a module update changes it, you can always manually call the function by pressing Alt+x (or Option+x on Mac/iPad), typing cider-quit, and pressing Enter.

2. Save Your Files

- Save the current file: Press SPC f s (File -> Save).
- Save all open modified files: Press SPC b S (Buffer -> Save all).

3. Exit Doom Emacs

To completely close the editor, press:
SPC q q (Quit -> Quit).
If you skip Step 2 and jump straight to SPC q q, Doom still has your back. It will halt the exit process, list any unsaved files at the bottom of the screen, and ask if you want to save them. Pressing y will save the file and complete the shutdown.
