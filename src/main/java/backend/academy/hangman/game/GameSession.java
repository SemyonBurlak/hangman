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
    }

    /**
     * Starts the game session and manages the gameplay loop.
     */
    public void startGameSession() {
        while (true) {
            String letter = getLetter();
            if (letter.equals(DebugCommand.STOP_GAME_LOOP.command())) {
                break;
            }
            makeNextStep(letter);

            // Check if the player has guessed all the letters
            if (getSetOfWordLetters().equals(sessionState.guessedCharacters())) {
                consoleOutput.showGameField(sessionState, settings, word, isHintVisible);
                sessionState.isWinCondition(true);
                break;
            }

            // Check if the player has run out of attempts
            if (sessionState.attemptsLeft() == 0) {
                consoleOutput.showGameField(sessionState, settings, word, isHintVisible);
                sessionState.isLoseCondition(true);
                break;
            }
        }
        if (sessionState.isWinCondition()) {
            consoleOutput.showWinMessage();
        }
        if (sessionState.isLoseCondition()) {
            consoleOutput.showLoseMessage(word.word());
        }
    }

    public void makeNextStep(String letter) {
        boolean stepResult =
            GameRulesChecker.isStepRight(word, letter) && !sessionState.guessedCharacters().contains(letter);
        if (stepResult) {
            sessionState.addGuessedCharacter(letter);
        } else {
            sessionState.decrementAttemptsLeft();
        }
    }

    private String getLetter() {
        String letter;
        while (true) {
            consoleOutput.showGameField(sessionState, settings, word, isHintVisible);
            try {
                letter = consoleInput.getNextLetter();
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

    private Set<String> getSetOfWordLetters() {
        Set<String> setOfWordLetters = new HashSet<>();
        Collections.addAll(setOfWordLetters, word.word().split(""));
        return setOfWordLetters;
    }

}
