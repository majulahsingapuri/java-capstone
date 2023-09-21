package capstone.Views;

import capstone.Enums.AccessLevel;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;

/**
 * The log out page before being redirected to the {@link LoginView}.
 * @author Nicolette
 * @version 1.0
 * @since 2020-11-1
 */
public final class LogoutView extends View {

    /**
     * Required method from View. Logs out the {@link Database}.CURRENT_USER and redirects user back to the log in page.
     */
    public void print() {
        clearScreen("Logout");

        Database.CURRENT_USER = null;
        Database.CURRENT_ACCESS_LEVEL = AccessLevel.NONE;
           
        System.out.println(ConsoleColours.GREEN_BRIGHT + "╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║" + ConsoleColours.WHITE + "           You have been logged out of            " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "       __  ___     _____________   ___  ____      " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      /  |/  /_ __/ __/_  __/ _ | / _ \\/ __/      " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "     / /|_/ / // /\\ \\  / / / __ |/ , _/\\ \\        " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "    /_/  /_/\\_, /___/ /_/ /_/ |_/_/|_/___/        " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "           /___/                                  " + ConsoleColours.GREEN_BRIGHT + "║");
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
