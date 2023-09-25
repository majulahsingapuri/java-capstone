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
        ConsoleColours.GREEN_BRIGHT + "╔══════════════════════════════════════════════════╗");
    System.out.println("║                                                  ║");
    System.out.println(
        "║"
            + ConsoleColours.WHITE
            + "           You have been logged out of            "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "            ____     ____     ____                "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "           /\\  _`\\  /\\  _`\\  /\\  _`\\              "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "           \\ \\ \\L\\ \\\\ \\ \\L\\ \\\\ \\ \\/\\_\\            "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "            \\ \\  _ <'\\ \\  _ <'\\ \\ \\/_/_           "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "             \\ \\ \\L\\ \\\\ \\ \\L\\ \\\\ \\ \\L\\ \\          "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "              \\ \\____/ \\ \\____/ \\ \\____/          "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println(
        "║"
            + ConsoleColours.YELLOW_BOLD
            + "               \\/___/   \\/___/   \\/___/           "
            + ConsoleColours.GREEN_BRIGHT
            + "║");
    System.out.println("║                                                  ║");
    System.out.println("╚══════════════════════════════════════════════════╝");

    System.out.println(ConsoleColours.WHITE + "\n\nTaking you back to the log in screen");
    for (int i = 0; i < 6; i++) {
      Helper.load();
    }

    System.out.println("\n");

    clearScreen("Login");
  }
}
