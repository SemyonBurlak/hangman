package backend.academy.hangman.config;

import lombok.Getter;

@Getter
public enum DebugCommand {
    STOP_GAME_LOOP("test_stop");

    final String command;

    DebugCommand(String command) {
        this.command = command;
    }
}
