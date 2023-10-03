package capstone.Extras;

import capstone.Enums.DayOfWeek;
import capstone.Enums.TransactionType;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.User;
import java.io.Console;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;
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

  /** Color printing on console */
  public static final String ANSI_RESET = "\u001B[0m";

  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

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

  public static double Amount_input_Checker() {
    String input_amount;
    double input_failure = 0.0;
    Pattern twoDp = Pattern.compile("^\\d+(\\.\\d{1,2})?$");

    try {
      System.out.println("Enter the amount (up to 2 decimal places):");
      input_amount = Helper.sc.nextLine();
      if (twoDp.matcher(input_amount).matches()) {
        double inputValue = Double.parseDouble(input_amount);
        System.out.println("Amount Number:" + input_amount);
        return inputValue; // Return the correctly formatted double
      }

    } catch (Exception e) {
      Helper.sc.nextLine(); // Ensure next .nextLine() consumes properly for the next input
      return input_failure;
    }
    return input_failure;
  }

  public static int continue_checker() {
    // for most view, decide whether continue this service or back to upper menu
    System.out.println(
        "Please enter [Y] to continue with this service. Enter [N] to return to the main menu : ");
    String str_input = Helper.sc.nextLine();
    if (str_input.equals("N") || str_input.equals("n")) {
      return 1;
    } else if (str_input.equals("Y") || str_input.equals("y")) {
      return 0;
    } else {
      System.out.println(
          ConsoleColours.RED_BOLD
              + "Wrong Input, please input Y/N or y/n"
              + ConsoleColours.RESET
              + "\u274C");
      return -1;
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

  /** Method that prints and acquires the user domain. */
  public static String getUserDomain() {
    // to print and get the domain of user
    String domain;
    System.out.println("Enter domain of user you want to see");
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

  /**
   * Method that prints and acquires user attributes.
   *
   * @param isCustomer false when it is Teller or Admin else true
   * @param requireUsername the function will not print and acquire Username filed when this sets to
   *     false
   */
  public static Object[] getUserAttributes(boolean isCustomer, boolean requireUsername) {

    Object[] ret = new Object[9];

    if (requireUsername) {
      System.out.print(String.format("%-50s: ", "Enter username"));
      String username = Helper.readLine();
      ret[0] = username;
    }
    String password =
        getInputWithValidation(
            "Enter password"
                + ANSI_RED
                + "(At least 1 uppercase, 1 lower case, 1 special character and length > 8)"
                + ANSI_RESET,
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
        try {
          LocalDate dob =
              LocalDate.parse(date, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          LocalDate minAgeDate = LocalDate.now().minusYears(16);
          if (dob.isAfter(minAgeDate)) {
            System.out.println(
                ANSI_RED + "Invalid date. You need to be at least 16 years old." + ANSI_RESET);
            System.out.println("Please provide correct Date of birth");
            continue;
          }

          // Convert LocalDate to Date
          Date dobDate = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());

          ret[6] = dobDate;
          break;
        } catch (DateTimeParseException e) {
          System.out.println(ANSI_RED + "Invalid date. Please try again." + ANSI_RESET);
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

  /**
   * Method that acquires inputs with validation.
   *
   * @param prompt guiding message to user for the input field
   * @param validator function checks if the input is valid; return false if it's invalid
   * @param isPassword true when the input field is a password.
   */
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
        System.out.println(ANSI_RED + "Invalid input format. Please try again." + ANSI_RESET);
      }
    }
  }

  /**
   * Method that checks password pattern for validation.
   *
   * @param password the password input
   */
  public static boolean isValidPassword(String password) {
    String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=~`!]).{8,}$";
    return password.matches(passwordPattern);
  }

  /**
   * Method that checks nric pattern for validation.
   *
   * @param nric the nric input
   */
  public static boolean isValidNric(String nric) {
    // NRIC format check: 9 characters with uppercase first and last characters
    String nricPattern = "^[GSTFM]{1}[0-9]{7}[A-Z]{1}$";
    return nric.matches(nricPattern);
  }

  /**
   * Method that checks email pattern for validation.
   *
   * @param email the email input
   */
  public static boolean isValidEmail(String email) {
    // Email format check using a simple regular expression
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    return email.matches(emailPattern);
  }

  /**
   * Method that checks phoneNumber pattern for validation.
   *
   * @param phoneNumber the phoneNumber input
   */
  public static boolean isValidPhoneNumber(String phoneNumber) {
    // Phone number format check: 8 characters, all digits
    return phoneNumber.matches("\\d{8}");
  }

  /**
   * Method that asks for userName and print the userCredentials accordingly. This function words
   * for all roles including Customer,admin and teller.
   *
   * @param userType role of the user. customer for Customer, teller for Teller and admin for Admin
   * @param prompt initial prompt message to acquire username
   */
  public static void getUser(String userType, String prompt) {
    System.out.print(String.format("%-50s: ", prompt));
    String username = Helper.readLine();

    Optional<?> queryRes;
    Boolean isEmpty;
    if (userType.equals("admin")) queryRes = Database.getAdmin(username);
    else if (userType.equals("teller")) queryRes = Database.getTeller(username);
    else queryRes = Database.getCustomer(username); // Customer type

    isEmpty = queryRes.isEmpty();
    if (isEmpty)
      System.out.println(
          ANSI_RED
              + userType
              + " with username "
              + username
              + " can not be found"
              + ANSI_RESET
              + "\uD83E\uDD7A");
    else {
      String tableHeader,
          firstName,
          lastName,
          password,
          nric = "",
          email = "",
          address = "",
          phoneNumber = "";
      Date dob = null;
      Customer customer;

      User user = (User) queryRes.get();
      if (queryRes.get() instanceof Customer) customer = (Customer) queryRes.get();

      tableHeader = userType + " credentials";
      firstName = user.getFirstName();
      lastName = user.getLastName();
      password = "you have no access to read the password";

      if (queryRes.get() instanceof Customer) {
        customer = (Customer) queryRes.get();
        nric = customer.getNRIC();
        email = customer.getEmail();
        address = customer.getAddress();
        phoneNumber = customer.getPhoneNumber();
        dob = customer.getDateOfBirth();
      }

      printUserCredentials(
          tableHeader, firstName, lastName, password, nric, email, dob, address, phoneNumber);
      ;
      Helper.printLine(80);
    }
  }

  /**
   * Method that acquires username and password; Validates if it's a exisiting user in the db
   *
   * @param userType customer or admin or teller type
   */
  public static <T extends User> T validateUser(String userType) throws Exception {
    System.out.println("\nPlease enter the username of the user you wish to update:");
    System.out.print(String.format("%-50s: ", "Enter username"));
    String username = Helper.readLine();

    Optional<? extends User> result2;
    Optional<User> result = Database.getUser(username);
    if (userType.equals("customer")) result2 = Database.getCustomer(username);
    else if (userType.equals("teller")) result2 = Database.getTeller(username);
    else result2 = Database.getAdmin(username);

    if (result.isEmpty() || result2.isEmpty()) {
      throw new Exception("User not found");
    }

    System.out.println(ANSI_GREEN + "User validated successfully" + ANSI_RESET);
    return (T) result2.get();
  }

  /**
   * Method that prints the user's credentials in table format.
   *
   * @param tableHeader the tableheader
   * @param firstName user's firsname
   * @param lastName user's lassname
   * @param password user's password
   * @param nric user's nric
   * @param email user's email
   * @param dob user's date of birth
   * @param address user's address
   * @param phoneNumber user's phoneNumber
   */
  public static void printUserCredentials(
      String tableHeader,
      String firstName,
      String lastName,
      String password,
      String nric,
      String email,
      Date dob,
      String address,
      String phoneNumber) {
    String formattedDob = (dob != null) ? dob.toString() : "";
    String formattedNRIC = (nric != null) ? nric : "";
    String formattedEmail = (email != null) ? email : "";
    String formattedAddress = (address != null) ? address : "";
    String formattedPhoneNum = (phoneNumber != null) ? phoneNumber : "";
    String table =
        String.format(
            Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("+-----------------------------------------------------------+")
                    .reset()
                + "\n"
                + Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("| Field          | " + String.format("%-40s", tableHeader) + " |")
                    .reset()
                + "\n"
                + Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("+-----------------------------------------------------------+")
                    .reset()
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " First Name     "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", firstName)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " Last Name      "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", lastName)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " Password       "
                            + ANSI_RESET
                            + "| "
                            + ANSI_RED
                            + String.format("%-40s", password)
                            + ANSI_RESET
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " NRIC           "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", formattedNRIC)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " Email          "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", formattedEmail)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " Date of Birth  "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", formattedDob)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " Address        "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", formattedAddress)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .a(
                        "|"
                            + ANSI_GREEN
                            + " Phone Number   "
                            + ANSI_RESET
                            + "| "
                            + String.format("%-40s", formattedPhoneNum)
                            + " |")
                + "\n"
                + Ansi.ansi()
                    .fg(Ansi.Color.YELLOW)
                    .a("+-----------------------------------------------------------+")
                    .reset());
    System.out.println(table);
  }

  /**
   * Method that attempts to update user's credentials into database.
   *
   * @param user the to be updated
   * @param firstName user's updated firsname
   * @param lastName user's updated lassname
   * @param password user's updated password
   * @param nric user's updated nric
   * @param email user's updated email
   * @param dob user's updated date of birth
   * @param address user's updated address
   * @param phoneNumber user's updated phoneNumber
   */
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

    if (firstName != null && !firstName.isEmpty()) {
      if (Database.updateUserFirstName(user, firstName))
        System.out.println(ANSI_GREEN + "Firstname updated successfully" + ANSI_RESET);
    }
    if (lastName != null && !lastName.isEmpty()) {
      if (Database.updateUserLastName(user, lastName))
        System.out.println(ANSI_GREEN + "Lastname updated successfully" + ANSI_RESET);
    }
    if (password != null && !password.isEmpty()) {
      if (Database.updatePassword(user, password))
        System.out.println(ANSI_GREEN + "Password updated successfully" + ANSI_RESET);
    }
    if (user instanceof Customer && nric != null && !nric.isEmpty()) {
      if (Database.updateCustomerNRIC((Customer) user, nric))
        System.out.println(ANSI_GREEN + "Nric updated successfully" + ANSI_RESET);
    }
    if (user instanceof Customer && email != null && !email.isEmpty()) {
      if (Database.updateCustomerEmail((Customer) user, email))
        System.out.println(ANSI_GREEN + "Email updated successfully" + ANSI_RESET);
    }
    if (user instanceof Customer && dob != null) {
      if (Database.updateCustomerDob((Customer) user, dob))
        System.out.println(ANSI_GREEN + "DateofBirth updated successfully" + ANSI_RESET);
    }
    if (user instanceof Customer && address != null && !address.isEmpty()) {
      if (Database.updateCustomerAddress((Customer) user, address))
        System.out.println(ANSI_GREEN + "Address updated successfully" + ANSI_RESET);
    }
    if (user instanceof Customer && phoneNumber != null && !phoneNumber.isEmpty()) {
      if (Database.updateCustomerPhoneNumber((Customer) user, phoneNumber))
        System.out.println(ANSI_GREEN + "PhoneNum updated successfully" + ANSI_RESET);
    }
  }

  public static String display_customer_accounts(String username) {
    ArrayList<Account> account_list = Database.getCustomerAccounts(username);
    if (account_list.isEmpty()) {
      System.out.println(
          ConsoleColours.RED_BOLD
              + "This customer does not have a bank account!"
              + ConsoleColours.RESET
              + "\u274C");
      Helper.pause();
      return "No Account";
    }
    System.out.println("Here are all accounts under customer: " + username);
    Helper.printLine(80);
    System.out.println("Choice | Account ID | Type    | Balance");

    for (int i = 0; i < account_list.size(); i++) {
      Account account1 = account_list.get(i);
      System.out.println(
          (i + 1)
              + "      | "
              + account1.getID()
              + "          | "
              + account1.getAccountType()
              + " | "
              + account1.getBalance());
    }
    Helper.printLine(80);
    return "Account Exist";
  }

  public static HashMap<String, Integer> check_account_choice_input(
      ArrayList<Account> account_list) {
    HashMap<String, Integer> choice_map = new HashMap<String, Integer>();
    System.out.println("Select one account you want to continue on: ");
    System.out.print(String.format("%-50s: ", "Choice"));
    try {
      int choice = Integer.parseInt(Helper.readLine());
      if (choice >= 1 && choice <= account_list.size()) {
        choice_map.put("Valid", choice);
        return choice_map;
      } else {
        System.out.println(
            ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
        choice_map.put("Choice out of range", 0);
        return choice_map;
      }
    } catch (NumberFormatException e) {
      System.out.println(ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
      choice_map.put("Invalid input choice format", 0);
      return choice_map;
    }
  }

  public static String customer_withdraw(
      Customer customer_user,
      ArrayList<Account> account_list,
      HashMap<String, Integer> choice_map) {
    double input_amount = Amount_input_Checker();
    int choice = choice_map.get("Valid");
    Account account = account_list.get(choice - 1);
    double balance_current = account_list.get(choice - 1).getBalance();
    String withdraw_result = "";

    if (input_amount <= balance_current && input_amount > 0) {
      float balance_after_withdraw = (float) (balance_current - input_amount);
      Database.updateBalance(account, balance_after_withdraw); // add try
      Database.createTransaction(customer_user, account, TransactionType.DEBIT, input_amount);
      System.out.println(
          ConsoleColours.GREEN + "Withdraw Successful!" + ConsoleColours.RESET + "\uD83C\uDF89");
      withdraw_result = "Withdraw Success";
      System.out.println(
          "Current Balance for this account is: " + "\uD83D\uDCB0" + balance_after_withdraw);
      display_customer_accounts(customer_user.getUsername());
    } else if (input_amount > balance_current) {
      System.out.println(
          ConsoleColours.YELLOW
              + "Insufficient Cash inside!"
              + ConsoleColours.RESET
              + "\uD83E\uDD7A");
      withdraw_result = "Insufficient Cash inside";
    } else if (input_amount <= 0) {
      System.out.println(
          ConsoleColours.RED_BOLD
              + "Please enter a number greater than or equal to zero with at least two decimal"
              + " places"
              + ConsoleColours.RESET
              + "\uD83E\uDD7A");
      withdraw_result = "Invalid Input";
    }
    return withdraw_result;
  }

  public static String customer_deposit(
      Customer customer_user,
      ArrayList<Account> account_list,
      HashMap<String, Integer> choice_map) {
    double input_amount = Amount_input_Checker();
    int choice = choice_map.get("Valid");
    Account account = account_list.get(choice - 1);
    double balance_current = account_list.get(choice - 1).getBalance();
    String deposit_result = "";

    if (input_amount > 0) {
      float balance_after_withdraw = (float) (balance_current + input_amount);
      Database.updateBalance(account, balance_after_withdraw); // add try
      Database.createTransaction(customer_user, account, TransactionType.DEBIT, input_amount);
      System.out.println(
          ConsoleColours.GREEN + "Deposit Successful!" + ConsoleColours.RESET + "\uD83C\uDF89");
      deposit_result = "Deposit Success";
      System.out.println(
          "Current Balance for this account is: " + "\uD83D\uDCB0" + balance_after_withdraw);
      display_customer_accounts(customer_user.getUsername());
    } else if (input_amount <= 0) {
      System.out.println(
          ConsoleColours.RED_BOLD
              + "Please enter a number greater than or equal to zero with at least two decimal"
              + " places"
              + ConsoleColours.RESET
              + "\uD83E\uDD7A");
      deposit_result = "Invalid Input";
    }
    return deposit_result;
  }
}
