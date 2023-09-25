package capstone.Extras;

import capstone.Enums.DayOfWeek;
import capstone.Objects.Database;
import capstone.Objects.User;
import java.io.Console;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 * A Helper Class with all properties and methods that are generally needed across the application.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class Helper {

  /**
   * A single Scanner object that reads input from console so that memory resources are efficiently
   * used.
   */
  public static Scanner sc = new Scanner(System.in);

  /** A load function that prints progress bars across the screen at 500ms intervals. */
  public static void load() {

    System.out.printf(ConsoleColours.GREEN_BRIGHT + "║║║║║║║║║║║║" + ConsoleColours.RESET);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
    }
  }

  /**
   * A pause function that waits for the user to press the Enter key to proceed on with the next
   * task.
   */
  public static void pause() {
    System.out.print("Press <Enter> to continue... ");
    sc.nextLine();
  }

  /**
   * A date formatter that takes in the current day and time and returns a {@link
   * org.joda.time.DateTime} object.
   *
   * @param day A {@link DayOfWeek} Enum corresponding to the day of the week.
   * @param hour_24 The hour in 24 hour format.
   * @param minute The minutes of the hour.
   * @return A DateTime Object.
   */
  public static DateTime formatTime(DayOfWeek day, int hour_24, int minute) {

    String dateStr = "2020-06-0" + day.value + "T" + hour_24 + ":" + minute + ":00";

    return new DateTime(dateStr);
  }

  /**
   * A method that sends an email from the Administrative Gmail Account to the user if their course
   * has been registered.
   *
   * @param receipient The network username of the student.
   * @param course The String value of the course that they have been successfully registered for.
   * @throws Exception Throws Exception when unable to send mail successfully.
   */

  /**
   * A method to read passwords from Console without displaying the characters on the screen.
   *
   * @return A String representing the password that was keyed in.
   */
  public static String getPasswordInput() {
    Console console = System.console();
    String password = null;
    try {
      char[] input = console.readPassword();
      password = String.copyValueOf(input);
    } catch (Exception e) {
      password = Helper.sc.nextLine();
    }
    return password;
  }

  /**
   * Reads standard input using the Scanner.
   *
   * @return The read input in capitalised form.
   */
  public static String readLine() {

    return sc.nextLine().toUpperCase();
  }

  /**
   * Reads time input using Scanner. Prints error message if invalid format.
   *
   * @return 24 hour time.
   * @throws Exception if the time does not meet the specified format.
   */
  public static String readTime() throws Exception {
    String time = sc.nextLine();

    if (!time.matches("\\p{Digit}{4}$")) {
      throw new Exception();
    }
    return time;
  }

  /** Prints 3 lines of space */
  public static void printSmallSpace() {

    for (int i = 0; i < 3; i++) {
      System.out.println();
    }
  }

  /** Prints 5 lines of space */
  public static void printMediumSpace() {

    for (int i = 0; i < 5; i++) {
      System.out.println();
    }
  }

  /** Prints 7 lines of space */
  public static void printLargeSpace() {

    for (int i = 0; i < 7; i++) {
      System.out.println();
    }
  }

  /**
   * Method that prints a horizontal line across the screen.
   *
   * @param size The length of the line to print
   */
  public static void printLine(int size) {
    String line = String.format("%" + size + "s", "").replace(" ", "═");
    System.out.println(line);
  }

  public static int continue_checker() {
    // for most view, decide whether continue this service or back to upper menu
    System.out.println("Continue? [Y/N]: ");
    String str_input = Helper.sc.nextLine();
    if (str_input.equals("N")) {
      return 1;
    } else if (str_input.equals("Y")) {
      return 0;
    } else {
      System.out.println("Wrong Input, please input Y/N or y/n");
      return 0;
    }
  }

  public static void customer_search() {
    // for admin and teller use
    System.out.println("Enter the customer account number:");
    String account_string = Helper.readLine(); // convert to int type
    if (Database.containsUser(account_string)) {
      User customer_user = Database.getUser(account_string);
    } else {
      System.out.println("This payee does not exist! Please re-enter!");
      Helper.pause();
    }
  }

  //
}
