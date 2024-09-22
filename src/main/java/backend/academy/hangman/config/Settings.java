package backend.academy.hangman.config;

import java.util.Random;
import lombok.Getter;

public class Settings {
    @Getter
    private Difficulty difficulty;
    @Getter
    private Category category;
    private final Random random = new Random();

    public Settings() {}

    public Settings(Difficulty difficulty, Category category) {
        this.difficulty = difficulty;
        this.category = category;
    }

    public Difficulty getDifficultyFallbackToRandom() {
        if (difficulty == null) {
            difficulty = getRandomDifficulty();
        }
        return difficulty;
    }

    public Category getCategoryFallbackToRandom() {
        if (category == null) {
            category = getRandomCategory();
        }
        return category;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == null) {
            return;
        }
        this.difficulty = difficulty;
    }

    public void setCategory(Category category) {
        if (category == null) {
            return;
        }
        this.category = category;
    }

    private Difficulty getRandomDifficulty() {
        Difficulty[] difficulties = Difficulty.values();
        return difficulties[random.nextInt(difficulties.length)];
    }

    private Category getRandomCategory() {
        Category[] categories = Category.values();
        return categories[random.nextInt(categories.length)];
    }
}
