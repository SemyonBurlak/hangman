package backend.academy.hangman.game;

import backend.academy.hangman.config.Category;
import backend.academy.hangman.config.DebugCommand;
import backend.academy.hangman.config.Difficulty;
import backend.academy.hangman.config.Settings;
import backend.academy.hangman.config.Submenu;
import backend.academy.hangman.config.Word;
import backend.academy.hangman.config.WordsDictionary;
import backend.academy.hangman.io.ConsoleInput;
import backend.academy.hangman.io.ConsoleOutput;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lombok.Getter;

/**
 * Responsible for initializing the game, managing settings,
 * showing the main menu, and starting the game session.
 */
public class GameInitializer {
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    @Getter
    private final Settings settings;
    private final Random random;

    public GameInitializer(InputStream inputStream, PrintStream printStream) {
        this.consoleInput = new ConsoleInput(inputStream);
        this.consoleOutput = new ConsoleOutput(printStream);
        this.settings = new Settings();
        this.random = new Random();
    }

    public GameInitializer(ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
        this.settings = new Settings();
        this.random = new Random();
    }

    /**
     * Displays the main menu options and processes user input to navigate between starting the game,
     * selecting difficulty, or selecting a category.
     */
    @SuppressWarnings("MagicNumber")
    public void showMainMenu() {
        List<String> mainMenuOptions = Arrays.asList("START GAME", "SELECT DIFFICULTY", "SELECT CATEGORY");
        // Main menu loop
        loop:
        while (true) {
            consoleOutput.showMainMenu(settings.difficulty(), settings.category(), mainMenuOptions);
            try {
                String selectedOptionString = consoleInput.getMenuOption(mainMenuOptions.size());
                if (selectedOptionString.equals(DebugCommand.STOP_GAME_LOOP.command())) {
                    break;
                }
                int selectedOption = Integer.parseInt(selectedOptionString);
                switch (selectedOption) {
                    case 1 -> {
                        initializeGameSession();
                        break loop;
                    }
                    case 2 -> showDifficultySelection();
                    case 3 -> showCategorySelection();
                    case 0 -> {
                        break loop;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                consoleOutput.showInputMismatchMenuOptionMessage();
            }
        }
    }

    public Word getRandomWordFromCategory(Category category, Map<Category, Set<Word>> wordDictionary)
        throws NullPointerException {
        List<Word> wordsWithCategory = new ArrayList<>(wordDictionary.get(category));
        return wordsWithCategory.get(random.nextInt(wordsWithCategory.size()));
    }

    private void showDifficultySelection() {
        List<Difficulty> difficulties = List.of(Difficulty.values());
        settings.setDifficulty(selectOptionFromSubmenu(difficulties, Submenu.DIFFICULTIES));
    }

    private void showCategorySelection() {
        List<Category> categories = List.of(Category.values());
        settings.setCategory(selectOptionFromSubmenu(categories, Submenu.CATEGORIES));
    }

    private <T> T selectOptionFromSubmenu(List<T> options, Submenu submenu) {
        while (true) {
            consoleOutput.showSubmenu(submenu, options.stream().map(T::toString).toList());
            try {
                String selectedOptionString = consoleInput.getMenuOption(options.size());
                if (selectedOptionString.equals(DebugCommand.STOP_GAME_LOOP.command())) {
                    break;
                }
                int selectedOption = Integer.parseInt(selectedOptionString);
                return selectedOption > 0 ? options.get(selectedOption - 1) : null;
            } catch (InputMismatchException e) {
                consoleOutput.showInputMismatchMenuOptionMessage();
            }
        }
        return null;
    }

    private void initializeGameSession() {
        try {
            // Set random difficulty if difficulty not selected
            settings.setDifficulty(settings.getDifficultyFallbackToRandom());
            // Get random word from category.
            // If category not selected set random category and get word from this category
            Word word = getRandomWordFromCategory(settings.getCategoryFallbackToRandom(), WordsDictionary.WORDS);
            // Check that word and hint are longer than 2 symbols and consists of Latin letters
            if (!word.checkIsWordCorrect()) {
                throw new InputMismatchException(
                    "Word and hint should be longer than 2 symbols and consists of Latin letters"
                );
            }

            GameSession gameSession = new GameSession(word, settings, consoleInput, consoleOutput);
            gameSession.startGameSession();
        } catch (NullPointerException e) {
            consoleOutput.showWordNotFoundMessage();
            showCategorySelection();
        }
    }

}
