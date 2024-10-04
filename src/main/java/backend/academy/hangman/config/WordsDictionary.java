package backend.academy.hangman.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WordsDictionary {

    public static final Map<Category, Set<Word>> WORDS = Map.of(
        Category.ANIMALS, Set.of(
            new Word("ELEPHANT", "Big and gray"),
            new Word("FROG", "Small and green"),
            new Word("BEE", "Very small and striped")
        ),
        Category.FRUITS, Set.of(
            new Word("APPLE", "Fell down on Newton's head"),
            new Word("MANGO", "Instantly restores 100 mana"),
            new Word("PINEAPPLE", "SpongeBob's house")
        ),
        Category.BODY_PARTS, Set.of(
            new Word("LEG", "To walk"),
            new Word("EYE", "To see"),
            new Word("PALM", "To grab")
        )

    );

    public static Word getRandomWordFromCategory(Category category)
        throws NullPointerException {
        Random random = new Random();
        List<Word> wordsWithCategory = new ArrayList<>(WORDS.get(category));
        Word word = wordsWithCategory.get(random.nextInt(wordsWithCategory.size()));
        // Check that word and hint are longer than 2 symbols and consists of Latin letters
        if (!word.checkIsWordCorrect()) {
            throw new IllegalArgumentException(
                "Word should be longer than 2 symbols and consists of Latin letters and hint should be not empty."
            );
        }
        return word;
    }
}
