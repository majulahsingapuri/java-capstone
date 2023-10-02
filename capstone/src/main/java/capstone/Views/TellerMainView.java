package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;

/**
 * The main menu for the Teller side of the Application.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class TellerMainView extends View {

  /** Prints menu options for the User to select from. Required method from {@link View}. */
  public void print() {

    int choice;
    do {
      try {

        clearScreen("Teller Main" + "\uD83D\uDC40");

        String username = Database.CURRENT_USER.getUsername();
        System.out.println(
            "Welcome Teller: " + ConsoleColours.GREEN_BRIGHT + username + ConsoleColours.RESET);

        System.out.println("What would you like to do?");
        System.out.println("1: Change your Password");
        System.out.println("2: Logout");
        System.out.println("3: Deposit");
        System.out.println("4: Withdraw");
        System.out.println("5: Transfer");
        System.out.println("6: Transaction History");
        System.out.println("7: Display Customer Account Info");
        System.out.println("8: Display Current Teller Account Info");
        System.out.print(String.format("%-50s: ", "Choice"));
        choice = Integer.parseInt(Helper.readLine());

        switch (choice) {
          case 1:
            TellerChangePassword tellerChangePassword_view = new TellerChangePassword();
            tellerChangePassword_view.print();
            break;
          case 2:
            LogoutView logoutView = new LogoutView();
            logoutView.print();
            return;
          case 3:
            TellerDepositView tellerDepositView_view = new TellerDepositView();
            tellerDepositView_view.print();
            break;
          case 4:
            TellerWithdrawView tellerWithdrawView_view = new TellerWithdrawView();
            tellerWithdrawView_view.print();
            break;
          case 5:
            TellerTransferView tellerTransferView_view = new TellerTransferView();
            tellerTransferView_view.print();
            break;
          case 6:
            TellerTransactionHistoryView tellerTransactionHistoryView_view =
                new TellerTransactionHistoryView();
            tellerTransactionHistoryView_view.print();
            break;
          case 7:
            TellerDisplayCustomerView tellerDisplayView_view = new TellerDisplayCustomerView();
            tellerDisplayView_view.print();
            break;
          case 8:
            TellerDisplayTellerView tellerDisplayTellerView_view = new TellerDisplayTellerView();
            tellerDisplayTellerView_view.print();
            break;
          default:
            System.out.println("Please enter valid option." + "\uD83E\uDD7A");
        }
      } catch (Exception e) {
        System.out.println(e.getLocalizedMessage());
      }
    } while (true);
  }
}
