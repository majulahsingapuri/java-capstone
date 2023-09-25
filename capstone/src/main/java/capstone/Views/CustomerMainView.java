package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Database;

/**
 * The main menu for the Customer side of the Application.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class CustomerMainView extends View {

  /** Prints menu options for the User to select from. Required method from {@link View}. */
  public void print() {

    int choice;
    do {
      try {

        clearScreen("Customer Main");

        System.out.println("What would you like to do?");
        System.out.println("1: Change your Password");
        System.out.println("2: Logout");
        System.out.println("3: Deposit");
        System.out.println("4: Withdraw");
        System.out.println("5: Transfer");
        System.out.println("6: Transaction History");
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
            CustomerDepositView customerDepositView_view = new CustomerDepositView();
            customerDepositView_view.print();
            break;
          case 4:
            CustomerWithdrawView customerWithdrawView_view = new CustomerWithdrawView();
            customerWithdrawView_view.print();
            break;
          case 5:
            CustomerTransferView customerTransferView_view = new CustomerTransferView();
            customerTransferView_view.print();
            break;
          case 6:
            CustomerTransactionHistoryView customerTransactionHistoryView_view =
                new CustomerTransactionHistoryView();
            customerTransactionHistoryView_view.print();
            break;
          case 7:
            CustomerDisplayView customerDisplayView_view = new CustomerDisplayView();
            customerDisplayView_view.print();
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
