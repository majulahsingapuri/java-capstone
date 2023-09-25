package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Database;

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

    int choice;
    do {
      try {

        clearScreen("Admin Main");

        System.out.println("What would you like to do?");
        System.out.println("1: Change your Password");
        System.out.println("2: Logout");
        System.out.println("3: Add new user");
        System.out.println("4: Delete current user");
        System.out.println("5: Freeze current user");
        System.out.println("6: Update current user");
        System.out.println("7: Display Account Info");
        System.out.print(String.format("%-50s: ", "Choice"));
        choice = Integer.parseInt(Helper.readLine());

        switch (choice) {
          case 1:
            changePassword();
            break;
          case 2:
            LogoutView logoutView = new LogoutView();
            logoutView.print();
            return;
          case 3:
            AdminAddUser adminAddUser_view = new AdminAddUser();
            adminAddUser_view.print();
            break;
          case 4:
            AdminDeleteUser adminDeleteUser_view = new AdminDeleteUser();
            adminDeleteUser_view.print();
            break;
          case 5:
            AdminFreezeUser adminFreezeUser_view = new AdminFreezeUser();
            adminFreezeUser_view.print();
            break;
          case 6:
            AdminUpdateView adminUpdateView_view = new AdminUpdateView();
            adminUpdateView_view.print();
            break;
          case 7:
            AdminDisplayView adminDisplayView = new AdminDisplayView();
            adminDisplayView.print();
            break;
          default:
            System.out.println("Please enter valid option.");
        }
      } catch (Exception e) {
        System.out.println(e.getLocalizedMessage());
      }
    } while (true);
  }

  /** Changes the Password for the {@link Database#CURRENT_USER}. */
  private void changePassword() {
    Database.CURRENT_USER.changePassword();
    Helper.pause();
  }
}
