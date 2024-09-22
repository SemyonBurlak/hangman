package backend.academy.hangman.game;

import backend.academy.hangman.config.Word;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameRulesChecker {
    public static boolean isStepRight(Word word, String letter) {
        return word.word().contains(letter);
    }
}
