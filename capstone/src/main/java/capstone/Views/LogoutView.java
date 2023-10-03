package capstone.Views;

import capstone.Enums.AccessLevel;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;

/**
 * The log out page before being redirected to the {@link LoginView}.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class LogoutView extends View {

  /**
   * Required method from View. Logs out the {@link Database}.CURRENT_USER and redirects user back
   * to the log in page.
   */
  public void print() {
    clearScreen("Logout");

    Database.CURRENT_USER = null;
    Database.CURRENT_ACCESS_LEVEL = AccessLevel.NONE;

    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "╔════════════════════════════════════════════════════════════════════════╗");
    System.out.println(
        "║                                                                        ║");
    System.out.println(
        "║           "
            + ConsoleColours.WHITE
            + "           You have been logged out of            "
            + ConsoleColours.GREEN_BRIGHT
            + "           ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║                 "
            + ConsoleColours.GREEN
            + "██"
            + ConsoleColours.RESET
            + "                                        "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║               "
            + ConsoleColours.GREEN
            + "██▒▒██"
            + ConsoleColours.RESET
            + "                                      "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "██▒▒▒▒██"
            + ConsoleColours.RESET
            + "                                      "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "████▒▒▒▒██  ██████"
            + ConsoleColours.RESET
            + "                            "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "  ██▒▒▒▒▒▒██▒▒▒▒▒▒"
            + ConsoleColours.PURPLE
            + "██"
            + ConsoleColours.RESET
            + "                          "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "    ██▒▒▒▒▒▒▒▒"
            + ConsoleColours.PURPLE
            + "██████"
            + ConsoleColours.RESET
            + "                          "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "  ██▒▒▒▒▒▒▒▒"
            + ConsoleColours.PURPLE
            + "██▓▓▓▓▓▓██"
            + ConsoleColours.RESET
            + "                        "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "  ██▒▒"
            + ConsoleColours.PURPLE
            + "████"
            + ConsoleColours.GREEN
            + "▒▒"
            + ConsoleColours.PURPLE
            + "██▓▓▓▓▓▓▓▓██                      "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "  ██▒▒"
            + ConsoleColours.PURPLE
            + "██████▓▓▓▓▓▓▓▓▓▓▓▓████                  "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.GREEN
            + "  ██"
            + ConsoleColours.PURPLE
            + "██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████              "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "  ████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓██████        "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "  ████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▓▓▓▓██      "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "    ████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓▓▓██    "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "    ████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓▓▓██  "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "      ████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓██  "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "      ██████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓██"
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "        ██████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓██"
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "          ██████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓██"
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "            ██████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▓▓████"
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "              ██████████▓▓▓▓▓▓▓▓▓▓▒▒▒▒▓▓████  "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "                ██████████▓▓▓▓▓▓▓▓▓▓▓▓██████  "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "                  ████████████████████████    "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "                    ████████████████████      "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");
    System.out.println(
        ConsoleColours.GREEN_BRIGHT
            + "║             "
            + ConsoleColours.PURPLE
            + "                        ████████████          "
            + ConsoleColours.GREEN_BRIGHT
            + "             ║");

    System.out.println(
        "║                                                                        ║");
    System.out.println(
        "╚════════════════════════════════════════════════════════════════════════╝");

    System.out.println(ConsoleColours.WHITE + "\n\nTaking you back to the log in screen");
    for (int i = 0; i < 6; i++) {
      Helper.load();
    }

    System.out.println("\n");

    clearScreen("Login" + "\uD83D\uDC4B");
  }
}
