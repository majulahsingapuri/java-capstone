package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Teller;
import capstone.Objects.User;
import java.util.Optional;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Initial View seen by {@link User}. Used to log in the User to the System.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class LoginView extends View {

  /** Constructor method for the login page */
  public LoginView() {}

  /**
   * Displays Login view for {@link User}, checks the credentials and then logs in the User if the
   * credentials are valid. Required method from {@link View}.
   */
  public void print() {

    clearScreen("Login" + "\uD83D\uDC4B");

    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    String password, username, domain;
    Boolean isEmpty;

    while (true) {

      DateTime now = DateTime.now();
      System.out.println("Current Time: " + formatter.print(now));
      System.out.println("");

      System.out.println("Enter user domain[Enter q to quit]");
      System.out.println("1. Admin" + "\uD83D\uDE00");
      System.out.println("2. Teller" + "\uD83D\uDC40");
      System.out.println("3. Customer" + "\uD83E\uDD70");
      while (true) {

        System.out.print(String.format("%-50s: ", "Choice"));
        domain = Helper.readLine();

        if (domain.equals("q") || domain.equals("Q")) {
          return;
        } else if (domain.equals("1")) {
          System.out.print(String.format("%-50s: ", "Enter admin username"));
          break;
        } else if (domain.equals("2")) {
          System.out.print(String.format("%-50s: ", "Enter teller username"));
          break;
        } else if (domain.equals("3")) {
          System.out.print(String.format("%-50s: ", "Enter customer username"));
          break;
        } else {
          System.out.println(ConsoleColours.RED + "Invalid input" + ConsoleColours.RESET);
        }
      }

      username = Helper.readLine();

      System.out.print(String.format("%-50s: ", "Enter password"));
      password = Helper.getPasswordInput();

      if (Database.containsUser(username)) {
        User user = Database.getUser(username).get();
        if (user.checkPassword(password)) {
          if (domain.equals("1")) {
            try {
              Optional<Admin> result = Database.getAdmin(username);
              isEmpty = result.isEmpty();
              if (isEmpty) throw new Exception("Admin not found");
              Database.CURRENT_USER = result.get();
              Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
            } catch (Exception e) {
              System.out.println(
                  ConsoleColours.RED + "Invalid user. Please try again" + ConsoleColours.RESET);
            }
            AdminMainView adminView = new AdminMainView();
            adminView.print();
          } else if (domain.equals("2")) {
            try {
              Optional<Teller> result = Database.getTeller(username);
              isEmpty = result.isEmpty();
              if (isEmpty) throw new Exception("Teller not found");
              Database.CURRENT_USER = result.get();
              Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
            } catch (Exception e) {
              System.out.println(
                  ConsoleColours.RED + "Invalid user. Please try again" + ConsoleColours.RESET);
            }
            TellerMainView view = new TellerMainView();
            view.print();
          } else if (domain.equals("3")) {
            try {
              Optional<Customer> result = Database.getCustomer(username);
              isEmpty = result.isEmpty();
              if (isEmpty) throw new Exception("Customer not found");
              Database.CURRENT_USER = result.get();
              Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
            } catch (Exception e) {
              System.out.println(
                  ConsoleColours.RED + "Invalid user. Please try again" + ConsoleColours.RESET);
            }
            CustomerMainView view = new CustomerMainView();
            view.print();
          } else {
            System.out.println(
                ConsoleColours.RED
                    + "Invalid domain. Please enter domain again.\n"
                    + ConsoleColours.RESET);
          }
        } else {
          System.out.println(ConsoleColours.RED + "Invalid Password\n" + ConsoleColours.RESET);
        }

      } else {
        System.out.println(ConsoleColours.RED + "Invalid Username\n" + ConsoleColours.RESET);
      }
    }
  }
}
