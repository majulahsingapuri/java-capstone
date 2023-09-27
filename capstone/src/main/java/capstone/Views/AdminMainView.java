package capstone.Views;

import capstone.Extras.Helper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * The main menu for the Admin side of the Application.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class AdminMainView extends View {

  /** Prints menu options for the User to select from. Required method from {@link View}. */
  public void print() {

    clearScreen("Admin Main");
    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    String option;

    while (true) {
      DateTime now = DateTime.now();
      System.out.println("Current Time: " + formatter.print(now));
      System.out.println("");

      System.out.println("What would you like to do?");
      System.out.println("1: Add new user");
      System.out.println("2: Display current user info");
      System.out.println("3: Update current user");
      System.out.println("4: Delete current user");
      System.out.println("5: Logout");
      System.out.print(String.format("%-50s: ", "Choice"));

      option = Helper.readLine();
      switch (option) {
        case "1":
          AdminAddUser adminAddUser_view = new AdminAddUser();
          adminAddUser_view.print();
          break;
        case "2":
          AdminDisplayView adminDisplayUser_view = new AdminDisplayView();
          adminDisplayUser_view.print();
          break;
        case "3":
          AdminUpdateView adminUpdateUser_view = new AdminUpdateView();
          adminUpdateUser_view.print();
          break;
        case "4":
          AdminDeleteUser adminDeleteUser_view = new AdminDeleteUser();
          adminDeleteUser_view.print();
          break;
        case "5":
          LogoutView logoutView = new LogoutView();
          logoutView.print();
          return;
        default:
          System.out.println("Please enter valid option.");
      }
    }
  }
}
