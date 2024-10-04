package backend.academy.hangman.config;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class TextRenderer {
    public final static String[] STAGES  = {

            """








            """,

            """
            ------
            |    |
            |
            |
            |
            |
            |
            ------
            """,

            """
            ------
            |    |
            |    0
            |
            |
            |
            |
            ------
            """,

            """
            ------
            |    |
            |    0
            |    |
            |
            |
            |
            ------
            """,

            """
            ------
            |    |
            |    0
            |   /|
            |
            |
            |
            ------
            """,

            """
            ------
            |    |
            |    0
            |   /|\\
            |
            |
            |
            ------
            """,

            """
            ------
            |    |
            |    0
            |   /|\\
            |   /
            |
            |
            ------
            """,

            """
            ------
            |    |
            |    0
            |   /|\\
            |   / \\
            |
            |
            ------
            """,
    };

    public final static String MAIN_MENU_LOGO =
        """
        +=======================+
        |                       |
        |    HANGMAN THE GAME   |
        |                       |
        +=======================+
        """;

    public final static String CATEGORY_SUBMENU_HEADER =
        """
        +==============+
        |              |
        |  CATEGORIES  |
        |              |
        +==============+
        """;


    public final static String DIFFICULTY_SUBMENU_HEADER =
        """
        +=====================+
        |                     |
        |  DIFFICULTY LEVELS  |
        |                     |
        +=====================+
        """;
}

