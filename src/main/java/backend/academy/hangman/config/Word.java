package backend.academy.hangman.config;

import java.util.regex.Pattern;

public record Word(String word, String hint) {
    public boolean checkIsWordCorrect() {
        Pattern wordPattern = Pattern.compile("[A-Z]{3,10}");
        return wordPattern.matcher(word).matches() && !hint.isEmpty();
    }
}
