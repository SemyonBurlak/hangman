package backend.academy.hangman.config;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings({"MultipleStringLiterals", "MemberName"})
public class TextRenderer {
    public final String[] STAGES  = {

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

    public final String MAIN_MENU_LOGO =
        """
        +=======================+
        |                       |
        |    HANGMAN THE GAME   |
        |                       |
        +=======================+
        """;

    public final String CATEGORY_SUBMENU_HEADER =
        """
        +==============+
        |              |
        |  CATEGORIES  |
        |              |
        +==============+
        """;


    public final String DIFFICULTY_SUBMENU_HEADER =
        """
        +=====================+
        |                     |
        |  DIFFICULTY LEVELS  |
        |                     |
        +=====================+
        """;
}

