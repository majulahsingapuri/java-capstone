package capstone.Extras;

import capstone.Enums.DayOfWeek;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.User;
import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.*;
import org.fusesource.jansi.Ansi;
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

  public static Double Amount_Transfer_Checker() {
    // TODO: minus??
    double transfer_amount = 0;
    try {
      System.out.println("Enter the amount to be transferred:");
      transfer_amount = Helper.sc.nextDouble();
      System.out.println("Amount Number:" + transfer_amount);
      Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input
      return transfer_amount;
    } catch (Exception e) {
      System.out.println(
          ConsoleColours.RED_BOLD + "NOT A VALID NUMBER" + ConsoleColours.RESET + "\uD83E\uDD7A");
      Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input
      return transfer_amount;
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

  public static Object[] getUserAttributes(boolean isCustomer, boolean requireUsername) {
    Object[] ret = new Object[9];

    if (requireUsername) {
      System.out.print(String.format("%-50s: ", "Enter username"));
      String username = Helper.readLine();
      ret[0] = username;
    }
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

    if (isCustomer) {
      String nric = Helper.getInputWithValidation("Enter nric", Helper::isValidNric, false);
      ret[4] = nric;

      String email = Helper.getInputWithValidation("Enter email", Helper::isValidEmail, false);
      ret[5] = email;

      while (true) {
        System.out.print(String.format("%-50s: ", "Enter dateOfBirth in the format yyyy-mm-dd"));
        String date = Helper.readLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
          Date dateOfBirth = dateFormat.parse(date);
          ret[6] = dateOfBirth;
          break;
        } catch (ParseException e) {
          System.out.println("\u001B[31mInvalid input format. Please try again.\u001B[0m");
        }
      }

      System.out.print(String.format("%-50s: ", "Enter address"));
      String address = Helper.readLine();
      ret[7] = address;

      String phoneNumber =
          Helper.getInputWithValidation("Enter phonenumber", Helper::isValidPhoneNumber, false);
      ret[8] = phoneNumber;
    }

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

  public static String[] getUpdatedParticulars(boolean isCustomer) {
    while (true) {
      System.out.print(String.format("%-50s: ", "Enter new firstName"));
      System.out.print(String.format("%-50s: ", "Enter new lastName"));
      System.out.print(String.format("%-50s: ", "Enter new passWord"));
      if (isCustomer) {
        System.out.print(String.format("%-50s: ", "Enter new nric"));
        System.out.print(String.format("%-50s: ", "Enter new "));
        System.out.print(String.format("%-50s: ", "Enter new "));
        System.out.print(String.format("%-50s: ", "Enter new "));
      }
    }
  }

  public static <T extends User> T validateUser(String userType) throws Exception {
    System.out.println("Please provide username & password for validation");
    System.out.print(String.format("%-50s: ", "Enter username"));
    String username = Helper.readLine();
    System.out.print(String.format("%-50s: ", "Enter password"));
    String password = Helper.getPasswordInput();

    Optional<? extends User> result2;
    Optional<User> result = Database.getUser(username);
    if (userType.equals("customer")) result2 = Database.getCustomer(username);
    else if (userType.equals("teller")) result2 = Database.getTeller(username);
    else result2 = Database.getAdmin(username);

    if (result.isEmpty() || result2.isEmpty()) {
      throw new Exception("User not found");
    }

    if (!(result.get().checkPassword(password))) {
      throw new Exception("Password is incorrect");
    }
    return (T) result2.get();
  }

  public static void printUserUpdateCredentials(
      String firstName,
      String lastName,
      String password,
      String nric,
      String email,
      Date dob,
      String address,
      String phoneNumber) {
    String table =
        String.format(
            Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("+----------------------------------------+")
                    .reset()
                + "\n"
                + Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("| Field          | Updated Value         |")
                    .reset()
                + "\n"
                + Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("+----------------------------------------+")
                    .reset()
                + "\n"
                + Ansi.ansi().a(String.format("| First Name     | %-21s |", firstName))
                + "\n"
                + Ansi.ansi().a(String.format("| Last Name      | %-21s |", lastName))
                + "\n"
                + Ansi.ansi().a(String.format("| Password       | %-21s |", password))
                + "\n"
                + Ansi.ansi().a(String.format("| NRIC           | %-21s |", nric))
                + "\n"
                + Ansi.ansi().a(String.format("| Email          | %-21s |", email))
                + "\n"
                + Ansi.ansi().a(String.format("| Date of Birth  | %-21s |", dob))
                + "\n"
                + Ansi.ansi().a(String.format("| Address        | %-21s |", address))
                + "\n"
                + Ansi.ansi().a(String.format("| Phone Number   | %-21s |", phoneNumber))
                + "\n"
                + Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("+----------------------------------------+")
                    .reset());
    System.out.println(table);
  }

  public static void UpdateDb(
      User user,
      String firstName,
      String lastName,
      String password,
      String nric,
      String email,
      Date dob,
      String address,
      String phoneNumber) {
    System.out.println(user instanceof Admin);
    if (firstName != null) Database.updateUserFirstName(user, firstName);
    if (lastName != null) Database.updateUserLastName(user, lastName);
    if (password != null) Database.updatePassword(user, password);
    if (user instanceof Customer && nric != null)
      Database.updateCustomerNRIC((Customer) user, nric);
    if (user instanceof Customer && email != null)
      Database.updateCustomerEmail((Customer) user, email);
    if (user instanceof Customer && dob != null) Database.updateCustomerDob((Customer) user, dob);
    if (user instanceof Customer && address != null)
      Database.updateCustomerAddress((Customer) user, address);
    if (user instanceof Customer && phoneNumber != null)
      Database.updateCustomerPhoneNumber((Customer) user, phoneNumber);
  }
}
