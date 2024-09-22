package backend.academy.hangman.game;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the state of the game session, tracking the player's progress.
 */
@Getter
public class SessionState {
    private final Set<String> guessedCharacters;
    private int attemptsLeft;
    @Setter
    private boolean isWinCondition;
    @Setter
    private boolean isLoseCondition;

    public SessionState(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
        guessedCharacters = new HashSet<>();

        this.isWinCondition = false;
        this.isLoseCondition = false;

    }

    public void addGuessedCharacter(String character) {
        guessedCharacters.add(character);
    }

    public void decrementAttemptsLeft() {
        attemptsLeft--;
    }
}
