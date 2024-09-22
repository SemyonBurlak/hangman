package backend.academy.hangman.config;

import lombok.Getter;



@Getter
public enum Difficulty {
    EASY(7),
    MEDIUM(5),
    HARD(3);

    private final int attempts;

    Difficulty(int attempts) {
        this.attempts = attempts;
    }
}
