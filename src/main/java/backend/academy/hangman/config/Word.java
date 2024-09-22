package backend.academy.hangman.config;

import java.util.regex.Pattern;

public record Word(String word, String hint) {
    public boolean checkIsWordCorrect() {
        Pattern wordPattern = Pattern.compile("[A-Z]{3,10}");
        Pattern hintPattern = Pattern.compile("[A-Za-z ]{3,}");
        return (wordPattern.matcher(word).matches() && hintPattern.matcher(hint).matches());
    }
}
