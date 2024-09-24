package backend.academy.hangman;

import backend.academy.hangman.game.GameRulesChecker;
import backend.academy.hangman.config.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameRulesCheckerTest {
    Word testWord = new Word("TESTWORD", "HINT");

    @Test
    public void isLetterRight_CorrectLetter_ReturnsTrue() {
        Assertions.assertTrue(GameRulesChecker.isLetterRight(testWord, "T"));
    }
    @Test
    public void isLetterRight_IncorrectLetter_ReturnsFalse() {
        Assertions.assertFalse(GameRulesChecker.isLetterRight(testWord, "Z"));
    }
}
