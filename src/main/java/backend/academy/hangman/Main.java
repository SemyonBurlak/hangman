package backend.academy.hangman;

import backend.academy.hangman.game.GameInitializer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        GameInitializer gameInitializer = new GameInitializer(System.in, System.out);
        gameInitializer.showMainMenu();

    }
}
