package backend.academy.hangman;

import backend.academy.hangman.config.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordTest {
    @Test
    void testCorrectWord() {
        Word word = new Word("ELEPHANT", "Big and gray");
        Assertions.assertTrue(word.checkIsWordCorrect());
    }

    @Test
    void testEmptyWord() {
        Word word = new Word("", "Big and gray");
        Assertions.assertFalse(word.checkIsWordCorrect());
    }

    @Test
    void testShortWord() {
        Word word = new Word("EL", "Big and gray");
        Assertions.assertFalse(word.checkIsWordCorrect());
    }

    @Test
    void testLongWord() {
        Word word = new Word("ELLLLLLEPHANT", "Big and gray");
        Assertions.assertFalse(word.checkIsWordCorrect());
    }

    @Test
    void testNonUppercaseWord() {
        Word word = new Word("elephant", "Big and gray");
        Assertions.assertFalse(word.checkIsWordCorrect());
    }

    @Test
    void testEmptyHint() {
        Word word = new Word("ELEPHANT", "");
        Assertions.assertFalse(word.checkIsWordCorrect());
    }
}
