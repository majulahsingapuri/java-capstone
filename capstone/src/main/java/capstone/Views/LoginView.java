package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Teller;
import capstone.Objects.User;

import javax.xml.crypto.Data;

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

    clearScreen("Login");

    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    String password, username, domain;

    while (true) {

      DateTime now = DateTime.now();
      System.out.println("Current Time: " + formatter.print(now));
      System.out.println("");

      System.out.println("Enter user domain[Enter q to quit]");
      System.out.println("1. Admin");
      System.out.println("2. Teller");
      System.out.println("3. Customer");
      while (true) {

        System.out.print(String.format("%-50s: ", "Choice"));
        domain = Helper.readLine();

        if (domain.equals("Q")) {
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
          System.out.println("Invalid input");
        }
      }

      username = Helper.readLine();

      System.out.print(String.format("%-50s: ", "Enter password"));
      password = Helper.getPasswordInput();

      if (Database.containsUser(username)) {
        // TODO: Change this implementation to get the user based on the user type.
        User user = Database.getUser(username).get();
        if (user.checkPassword(password)) {
          if (domain.equals("1")) {
            // && result.getAccessLevel() == AccessLevel.ADMIN
            try {
              Database.CURRENT_USER = Database.getAdmin(username).get();
              // Database.CURRENT_USER = (Admin) result;  casting error
              Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
            } catch (Exception e) {
              System.out.println("Invalid user. Please try again");
            }
            AdminMainView adminView = new AdminMainView();
            adminView.print();
          } else if (domain.equals("2")) {
            try {
              // Parent p = new child();// yes
              // Parent p = (child) new Parent();// no, can not cast
              Database.CURRENT_USER = Database.getTeller(username).get(); // new
              // Database.CURRENT_USER = (Teller) result; // old
              Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
            } catch (Exception e) {
              System.out.println("Invalid user. Please enter again!");
            }
            TellerMainView view = new TellerMainView();
            view.print();
          } else if (domain.equals("3")) {
            try {
              Database.CURRENT_USER = Database.getCustomer(username).get();
              Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
            } catch (Exception e) {
              System.out.println("Invalid user. Please enter again!");
            }
            CustomerMainView view = new CustomerMainView();
            view.print();
          } else {
            System.out.println("Invalid domain. Please enter domain again.\n");
          }
        } else {
          System.out.println("Invalid Password\n");
        }

      } else {
        System.out.println("Invalid Username\n");
      }
    }
  }
}
