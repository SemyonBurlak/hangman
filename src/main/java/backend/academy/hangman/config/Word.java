package backend.academy.hangman.config;

import java.util.regex.Pattern;

public record Word(String content, String hint) {
    public boolean checkIsWordCorrect() {
        Pattern wordPattern = Pattern.compile("[A-Z]{3,10}");
        return wordPattern.matcher(content).matches() && !hint.isEmpty();
    }

    public boolean containsLetter(String letter) {
        return content.contains(letter);
    }
}
