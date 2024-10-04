package backend.academy.hangman.game;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

/**
 * Represents the state of the game session, tracking the player's progress.
 */
@Getter
public class SessionState {
    private final Set<String> guessedLetters;
    private int attemptsLeft;
    private boolean winCondition;
    private boolean loseCondition;

    public SessionState(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
        guessedLetters = new HashSet<>();

        this.winCondition = false;
        this.loseCondition = false;
    }

    public void addGuessedCharacter(String character) {
        guessedLetters.add(character);
    }

    public void decrementAttemptsLeft() {
        attemptsLeft--;
    }

    public void win() {
        winCondition = true;
    }

    public void lose() {
        loseCondition = true;
    }
}
