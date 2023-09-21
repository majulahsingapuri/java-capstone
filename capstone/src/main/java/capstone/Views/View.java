package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;

/**
 * The abstract superclass of all views in the application.
 * @author Bhargav, Nicolette
 * @version 1.0
 * @since 2020-11-1
 */
public abstract class View {
    
    /**
     * Necessary print functions that will be used as the start point of every view.
     */
    public abstract void print();

    /**
     * ClearScreen function that prints a header as well as the current view that the User is in.
     * @param directory The path of the current view that the user is in.
     */
    protected void clearScreen(String directory) {
        Helper.printLargeSpace();
               
        String spaces = String.format("%" + (71 - directory.length()) + "s", "");

        System.out.println(ConsoleColours.GREEN_BRIGHT + "╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                        ║");
        System.out.println("║" + ConsoleColours.WHITE + "                              Welcome to                                " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║                                                                        ║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      ███╗   ███╗██╗   ██╗███████╗████████╗ █████╗ ██████╗ ███████╗     " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      ████╗ ████║╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝     " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      ██╔████╔██║ ╚████╔╝ ███████╗   ██║   ███████║██████╔╝███████╗     " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      ██║╚██╔╝██║  ╚██╔╝  ╚════██║   ██║   ██╔══██║██╔══██╗╚════██║     " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      ██║ ╚═╝ ██║   ██║   ███████║   ██║   ██║  ██║██║  ██║███████║     " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║" + ConsoleColours.YELLOW_BOLD + "      ╚═╝     ╚═╝   ╚═╝   ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝     " + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("║                                                                        ║");
        System.out.println("║" + ConsoleColours.WHITE + "                    Nanyang Technological University                    " + ConsoleColours.GREEN_BRIGHT + "║");            
        System.out.println("║                                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║" + ConsoleColours.WHITE_BOLD + " " + directory + spaces + "" + ConsoleColours.GREEN_BRIGHT + "║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝" + ConsoleColours.WHITE);
        System.out.println();
    }
}
