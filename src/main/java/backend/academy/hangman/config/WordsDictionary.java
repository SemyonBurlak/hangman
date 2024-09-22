package backend.academy.hangman.config;

import java.util.Map;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MemberName")
public class WordsDictionary {
    public final Map<Category, Set<Word>> WORDS = Map.of(
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
}
