package backend.academy.hangman.io;

import backend.academy.hangman.config.DebugCommand;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Handles console-based input in the game.
 */
public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public String getMenuOption(int numberOfOptions) {
        try {
            String optionIndexString = scanner.nextLine();
            if (handleDebugCommand(Set.of(DebugCommand.STOP_GAME_LOOP), optionIndexString)) {
                return optionIndexString;
            }
            int optionIndex = Integer.parseInt(optionIndexString);
            if (numberOfOptions < optionIndex || optionIndex < 0) {
                throw new InputMismatchException();
            }
            return optionIndexString;

        } catch (InputMismatchException | NumberFormatException e) {
            throw new InputMismatchException("Invalid menu option");
        }
    }

    public String getNextLetter() throws InputMismatchException {
        String stringChar = scanner.nextLine();
        if (handleDebugCommand(Set.of(DebugCommand.STOP_GAME_LOOP), stringChar)) {
            return stringChar;
        }
        Pattern pattern = Pattern.compile("[A-Za-z]");
        if (pattern.matcher(stringChar).matches()) {
            return stringChar.toUpperCase();
        }
        throw new InputMismatchException("Letter expected");
    }

    private boolean handleDebugCommand(Set<DebugCommand> debugCommands, String string) {
        for (DebugCommand debugCommand : debugCommands) {
            if (debugCommand.command().equals(string)) {
                return true;
            }
        }
        return false;
    }
}


