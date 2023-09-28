package capstone.Extras;

import capstone.Enums.DayOfWeek;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.User;
import java.io.Console;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.*;
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

    return sc.nextLine();
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

  public static Double Amount_input_Checker() {
    // TODO: minus??
    double input_amount = 0;
    try {
      System.out.println("Enter the amount:");
      input_amount = Helper.sc.nextDouble();
      System.out.println("Amount Number:" + input_amount);
      Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input
      return input_amount;
    } catch (Exception e) {
      System.out.println(
          ConsoleColours.RED_BOLD + "NOT A VALID NUMBER" + ConsoleColours.RESET + "\uD83E\uDD7A");
      Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input
      return input_amount;
    }
  }

  public static int continue_checker() {
    // for most view, decide whether continue this service or back to upper menu
    System.out.println("Continue in this service? [Y/N]: ");
    String str_input = Helper.sc.nextLine();
    if (str_input.equals("N")) {
      return 1;
    } else if (str_input.equals("Y")) {
      return 0;
    } else {
      System.out.println(
          ConsoleColours.RED_BOLD
              + "Wrong Input, please input Y/N or y/n"
              + ConsoleColours.RESET
              + "\u274C");
      return 0;
    }
  }

  public static String customer_search() {
    // for admin and teller use
    System.out.println("Enter the customer username:");
    String username = Helper.readLine(); // convert to int type
    if (Database.containsUser(username)) {
      Optional<Customer> queryRes = Database.getCustomer(username);
      Boolean isEmpty = queryRes.isEmpty();
      if (isEmpty) {
        System.out.println(
            ConsoleColours.RED_BOLD
                + "Customer with username "
                + username
                + " cannot be found"
                + ConsoleColours.RESET
                + "\uD83E\uDD7A");
        return null;
      }
      return username;
    } else {
      System.out.println(
          ConsoleColours.RED_BOLD
              + "This username does not exist! Please re-enter!"
              + ConsoleColours.RESET
              + "\uD83E\uDD7A");
      Helper.pause();
      return null;
    }
  }

  public static String getUserDomain() {
    // to print and get the domain of user
    String domain;
    System.out.println("Enter the user's domain");
    while (true) {
      System.out.println("1. Admin");
      System.out.println("2. Teller");
      System.out.println("3. Customer");

      System.out.print(String.format("%-50s: ", "Choice"));
      domain = Helper.readLine();

      if (domain.equals("1")) return "admin";
      else if (domain.equals("2")) return "teller";
      else if (domain.equals("3")) return "customer";
      else
        System.out.println(
            ConsoleColours.RED_BOLD
                + "Invalid input, try again"
                + ConsoleColours.RESET
                + "\uD83E\uDD7A");
    }
  }

  public static String[] getUserAttributes() {
    String[] ret = new String[4];

    System.out.print(String.format("%-50s: ", "Enter username"));
    String username = Helper.readLine();
    ret[0] = username;

    String password =
        getInputWithValidation(
            "Enter password (contain at least one uppercase letter, one lowercase letter, one"
                + " digit, one special character, and be at least 8 characters long)",
            Helper::isValidPassword,
            true);
    ret[1] = password;

    System.out.print(String.format("%-50s: ", "Enter firstName"));
    String firstName = Helper.readLine();
    ret[2] = firstName;

    System.out.print(String.format("%-50s: ", "Enter lastName"));
    String lastName = Helper.readLine();
    ret[3] = lastName;

    return ret;
  }

  public static void printUserInfo(User user) {
    System.out.println("Account Information " + "\u2B07");
    Helper.printLine(80);
    System.out.print(String.format("%-50s: %s%n", "userName", user.getUsername()));
    System.out.print(String.format("%-50s: %s%n", "firstName", user.getFirstName()));
    System.out.print(String.format("%-50s: %s%n", "lastName", user.getLastName()));
  }

  public static String getInputWithValidation(
      String prompt, Function<String, Boolean> validator, boolean isPassword) {
    String input;
    while (true) {
      System.out.print(String.format("%-50s: ", prompt));
      if (isPassword) input = Helper.getPasswordInput();
      else input = Helper.readLine();

      if (validator.apply(input)) {
        return input;
      } else {
        System.out.println("\u001B[31mInvalid input format. Please try again.\u001B[0m");
      }
    }
  }

  public static boolean isValidPassword(String password) {
    String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=~`!]).{8,}$";
    return password.matches(passwordPattern);
  }

  public static boolean isValidNric(String nric) {
    // NRIC format check: 9 characters with uppercase first and last characters
    return nric.length() == 9
        && Character.isUpperCase(nric.charAt(0))
        && Character.isUpperCase(nric.charAt(8));
  }

  public static boolean isValidEmail(String email) {
    // Email format check using a simple regular expression
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    return email.matches(emailPattern);
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    // Phone number format check: 8 characters, all digits
    return phoneNumber.length() == 8 && phoneNumber.matches("\\d+");
  }
}
