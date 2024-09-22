package backend.academy.hangman;

import backend.academy.hangman.config.Category;
import backend.academy.hangman.config.Difficulty;
import backend.academy.hangman.game.GameInitializer;
import backend.academy.hangman.io.ConsoleInput;
import backend.academy.hangman.io.ConsoleOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameInitializerTest {
    ConsoleOutput consoleOutput = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream()));

    @Test
    void testDifficultySelection() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("2\n1\ntest_stop".getBytes()));
        GameInitializer gameInitializer = new GameInitializer(consoleInput, consoleOutput);
        gameInitializer.showMainMenu();
        Assertions.assertEquals(gameInitializer.settings().difficulty(), Difficulty.EASY);
    }

    @Test
    void testCategorySelection() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("3\n1\ntest_stop".getBytes()));
        GameInitializer gameInitializer = new GameInitializer(consoleInput, consoleOutput);
        gameInitializer.showMainMenu();
        Assertions.assertEquals(gameInitializer.settings().category(), Category.ANIMALS);
    }

    @Test
    void testInitializeGameWithoutDifficultyAndCategorySelection() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("1\ntest_stop\ntest_stop".getBytes()));
        GameInitializer gameInitializer = new GameInitializer(consoleInput, consoleOutput);
        gameInitializer.showMainMenu();
        Assertions.assertNotNull(gameInitializer.settings().category());
        Assertions.assertNotNull(gameInitializer.settings().difficulty());
    }

    @Test
    void testGetRandomWordWithCategory() {
        ConsoleInput consoleInput =
            new ConsoleInput(new ByteArrayInputStream("3\n1\n1\ntest_stop\ntest_stop".getBytes()));
        GameInitializer gameInitializer = new GameInitializer(consoleInput, consoleOutput);
        gameInitializer.showMainMenu();
        Assertions.assertTrue(
            gameInitializer.getRandomWordWithCategory(gameInitializer.settings().category()).word()
                .equals("FROG") ||
                gameInitializer.getRandomWordWithCategory(gameInitializer.settings().category()).word()
                    .equals("ELEPHANT")
        );

    }
}
