package backend.academy.hangman;

import backend.academy.hangman.game.GameRulesChecker;
import backend.academy.hangman.config.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameRulesCheckerTest {
    Word testWord = new Word("TESTWORD", "HINT");

    @Test
    public void isStepRight_CorrectLetter_ReturnsTrue() {
        Assertions.assertTrue(GameRulesChecker.isStepRight(testWord, "T"));
    }
    @Test
    public void isStepRight_IncorrectLetter_ReturnsFalse() {
        Assertions.assertFalse(GameRulesChecker.isStepRight(testWord, "Z"));
    }
}
