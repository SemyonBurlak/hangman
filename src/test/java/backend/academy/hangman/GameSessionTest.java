package backend.academy.hangman;

import backend.academy.hangman.config.Category;
import backend.academy.hangman.config.Difficulty;
import backend.academy.hangman.config.Settings;
import backend.academy.hangman.config.Word;
import backend.academy.hangman.game.GameSession;
import backend.academy.hangman.io.ConsoleInput;
import backend.academy.hangman.io.ConsoleOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSessionTest {
    Word word = new Word("APPLE", "A fruit");
    Settings settings = new Settings(Difficulty.EASY, Category.FRUITS);
    ConsoleOutput consoleOutput = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream()));

    @Test
    public void testMakeNextStepCorrectUppercaseLetter() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("A\ntest_stop".getBytes()));
        GameSession session = new GameSession(word, settings, consoleInput, consoleOutput);
        session.startGameSession();
        assertTrue(session.sessionState().guessedCharacters().contains("A"));
    }

    @Test
    public void testMakeNextStepCorrectLowercaseLetter() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("a\ntest_stop".getBytes()));
        GameSession session = new GameSession(word, settings, consoleInput, consoleOutput);
        session.startGameSession();
        assertTrue(session.sessionState().guessedCharacters().contains("A"));
    }

    @Test
    public void testMakeNextStepIncorrectInput() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("AP\ntest_stop".getBytes()));
        GameSession session = new GameSession(word, settings, consoleInput, consoleOutput);
        session.startGameSession();
        assertTrue(session.sessionState().guessedCharacters().isEmpty());
        assertEquals(session.sessionState().attemptsLeft(), Difficulty.EASY.attempts());
    }

    @Test
    public void testMakeNextStepRepeatedLetter() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("A\nA\ntest_stop".getBytes()));
        GameSession session = new GameSession(word, settings, consoleInput, consoleOutput);
        session.startGameSession();
        assertTrue(session.sessionState().guessedCharacters().contains("A"));
        assertEquals(session.sessionState().attemptsLeft(), settings.difficulty().attempts() - 1);
    }

    @Test
    public void testWinCondition() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("A\nP\nL\nE".getBytes()));
        GameSession session = new GameSession(word, settings, consoleInput, consoleOutput);
        session.startGameSession();
        assertTrue(session.sessionState().isWinCondition());
    }

    @Test
    public void testLoseCondition() {
        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream("Z\nZ\nZ\nZ\nZ\nZ\nZ\n".getBytes()));
        GameSession session = new GameSession(word, settings, consoleInput, consoleOutput);
        session.startGameSession();
        assertTrue(session.sessionState().isLoseCondition());
    }
}
