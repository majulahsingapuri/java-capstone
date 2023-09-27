package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Database;
import java.util.ArrayList;

public final class CustomerWithdrawView extends View {
  public void print() {
    clearScreen("Customer Withdraw Page");

    while (true) {

      String username = Database.CURRENT_USER.getUsername();

      try {
        ArrayList<Account> account_list = Database.getCustomerAccounts(username);
        System.out.println("Here are all the accounts under customer: " + username);
        System.out.println("#################");
        System.out.println("Choice | Account ID | Type");
        account_list.forEach(
            (account) -> {
              System.out.println(
                  (account_list.indexOf(account) + 1)
                      + "      | "
                      + account.getID()
                      + "          | "
                      + account.getAccountType());
            });
        System.out.println("#################");
      } catch (Exception e) {
        System.out.println(
            ConsoleColours.RED_BOLD
                + "This customer does not have a bank account!"
                + ConsoleColours.RESET);
        continue;
      }

      ArrayList<Account> account_list = Database.getCustomerAccounts(username);
      /*
       * check on right choice input
       */
      while (true) {
        try {
          System.out.println("Select one account you want to continue on: ");
          System.out.print(String.format("%-50s: ", "Choice"));
          int choice = Integer.parseInt(Helper.readLine());
          double balance_current = account_list.get(choice - 1).getBalance();
          System.out.println("Current Balance for this account is: " + balance_current);

          /*
           * Check for the right amount input
           */
          // TODO: savings has a min amount?
          while (true) {
            double input_amount = Helper.Amount_input_Checker();
            if (input_amount <= balance_current && input_amount > 0) {
              float balance_after_withdraw = (float) (balance_current - input_amount);
              Database.updateBalance(
                  account_list.get(choice - 1), balance_after_withdraw); // add try
              System.out.println(
                  ConsoleColours.GREEN + "Withdraw Successful!" + ConsoleColours.RESET);
              break;
            } else if (input_amount > balance_current) {
              System.out.println(
                  ConsoleColours.YELLOW + "Insufficient Cash inside!" + ConsoleColours.RESET);
              break;
            } else if (input_amount <= 0) {
              System.out.println(
                  ConsoleColours.RED_BOLD
                      + "Please enter a valid and positive number!"
                      + ConsoleColours.RESET);
              continue;
            }
          }
          break;
        } catch (Exception e) {
          System.out.println(
              ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
          continue;
        }
      }

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
