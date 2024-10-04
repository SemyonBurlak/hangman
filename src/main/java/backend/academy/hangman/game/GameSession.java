package backend.academy.hangman.game;

import backend.academy.hangman.config.DebugCommand;
import backend.academy.hangman.config.Difficulty;
import backend.academy.hangman.config.Settings;
import backend.academy.hangman.config.Word;
import backend.academy.hangman.io.ConsoleInput;
import backend.academy.hangman.io.ConsoleOutput;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import lombok.Getter;

/**
 * A session of the game. Controls the game logic.
 */
@Getter
public class GameSession {
    private final Settings settings;
    private final Word word;
    private final Set<String> wordLetters;
    private final boolean isHintVisible;
    private final SessionState sessionState;
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;

    public GameSession(Word word, Settings settings, ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.settings = settings;
        this.word = word;
        this.isHintVisible = settings.difficulty() == Difficulty.EASY;
        this.sessionState = new SessionState(settings.difficulty().attempts());
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;

        this.wordLetters = new HashSet<>();
        Collections.addAll(wordLetters, word.content().split(""));
    }

    /**
     * Starts the game session and manages the gameplay loop.
     */
    public void startGameSession() {
        // Session loop
        while (true) {
            String letter = askPlayerForLetter();
            // Check stop command
            if (letter.equals(DebugCommand.STOP_GAME_LOOP.command())) {
                break;
            }
            processGuessStep(letter);

            // Check if the player has guessed all the letters
            if (wordLetters.size() == sessionState.guessedLetters().size()
                && wordLetters.equals(sessionState.guessedLetters())) {
                consoleOutput.showGameField(sessionState, settings, word, isHintVisible);
                sessionState.win();
                break;
            }
            // Check if the player has run out of attempts
            if (sessionState.attemptsLeft() == 0) {
                consoleOutput.showGameField(sessionState, settings, word, isHintVisible);
                sessionState.lose();
                break;
            }
        }
        if (sessionState.winCondition()) {
            consoleOutput.showWinMessage();
        }
        if (sessionState.loseCondition()) {
            consoleOutput.showLoseMessage(word.content());
        }
    }

    public void processGuessStep(String letter) {
        // Check right step and that the letter haven't been guessed already
        boolean stepResult =
            word.containsLetter(letter) && !sessionState.guessedLetters().contains(letter);
        if (stepResult) {
            sessionState.addGuessedCharacter(letter);
        } else {
            sessionState.decrementAttemptsLeft();
        }
    }

    private String askPlayerForLetter() {
        String letter;
        while (true) {
            consoleOutput.showGameField(sessionState, settings, word, isHintVisible);
            try {
                letter = consoleInput.getNextLetter();
                // Return the command if it has been entered
                if (letter.equals(DebugCommand.STOP_GAME_LOOP.command())) {
                    return letter;
                }
                break;
            } catch (InputMismatchException e) {
                consoleOutput.showInputMismatchLetterMessage();
            }
        }
        return letter;
    }
}
