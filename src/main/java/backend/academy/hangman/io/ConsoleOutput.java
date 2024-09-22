package backend.academy.hangman.io;

import backend.academy.hangman.config.Category;
import backend.academy.hangman.config.Difficulty;
import backend.academy.hangman.config.Settings;
import backend.academy.hangman.config.Submenu;
import backend.academy.hangman.config.TextRenderer;
import backend.academy.hangman.config.Word;
import backend.academy.hangman.game.SessionState;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;

/**
 * Handles console-based output in the game.
 */
@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleOutput {
    private final PrintStream printStream;

    public ConsoleOutput(PrintStream printStream) {
        this.printStream = new PrintStream(printStream);
    }

    public void showGameField(SessionState sessionState, Settings settings, Word word, boolean isHintVisible) {
        renderStage(TextRenderer.STAGES.length - 1 - sessionState.attemptsLeft());
        printStream.println("DIFFICULTY: " + settings.difficulty());
        printStream.println("CATEGORY: " + settings.category());
        printStream.println("HINT: " + (isHintVisible ? word.hint() : "NOT AVAILABLE"));
        printStream.println();
        showGuessingWord(word, sessionState.guessedCharacters());
    }

    public void showMainMenu(Difficulty difficulty, Category category, List<String> mainMenuOptions) {
        renderMainMenuLogo();
        printStream.println();
        renderOptionsSelection(mainMenuOptions);
        printStream.println();
        printStream.println("SELECTED DIFFICULTY: " + (difficulty == null ? "RANDOM DIFFICULTY" : difficulty));
        printStream.println("SELECTED CATEGORY: " + (category == null ? "RANDOM CATEGORY" : category));
        printStream.println();
        printStream.println("[0] EXIT");
    }

    public void showInputMismatchMenuOptionMessage() {
        printStream.println("Option index expected");
    }

    public void showInputMismatchLetterMessage() {
        printStream.println("Letter expected");
    }

    public void showWordNotFoundMessage() {
        printStream.println("Word not found. Select another category.");
    }

    public void showSubmenu(Submenu submenu, List<String> options) {
        switch (submenu) {
            case CATEGORIES -> renderCategorySubmenuHeader();
            case DIFFICULTIES -> renderDifficultySubmenuHeader();
            default -> throw new IllegalArgumentException();
        }
        printStream.println();
        renderOptionsSelection(options);
        printStream.println("[0] EXIT SUBMENU");
    }

    public void renderStage(int stage) {
        printStream.println(TextRenderer.STAGES[stage]);
    }

    public void renderMainMenuLogo() {
        printStream.println(TextRenderer.MAIN_MENU_LOGO);
    }

    public void renderCategorySubmenuHeader() {
        printStream.println(TextRenderer.CATEGORY_SUBMENU_HEADER);
    }

    public void renderDifficultySubmenuHeader() {
        printStream.println(TextRenderer.DIFFICULTY_SUBMENU_HEADER);
    }

    public void showWinMessage() {
        printStream.println("YOU WON");
    }

    public void showLoseMessage(String word) {
        printStream.println("YOU LOST");
        printStream.println("GUESSED WORD: " + word);
    }

    private void showGuessingWord(Word word, Set<String> guessedCharacters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String letter : word.word().split("")) {
            if (guessedCharacters.contains(letter)) {
                stringBuilder.append(letter);
            } else {
                stringBuilder.append("_");
            }
        }
        printStream.println(stringBuilder);
    }

    private void renderOptionsSelection(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            printStream.println("[" + (i + 1) + "] " + (list.get(i)));
        }
    }
}

