package capstone.Views;

import capstone.Enums.TransactionType;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.ArrayList;

public final class TellerWithdrawView extends View {
  public void print() {
    clearScreen("Teller Withdraw Page");

    while (true) {
      String username = Helper.customer_search();
      if (username == null) {
        continue;
      }

      ArrayList<Account> account_list = Database.getCustomerAccounts(username);
      if (account_list.size() > 0) {
        System.out.println("Here are all the accounts under customer: " + username);
        Helper.printLine(80);
        System.out.println("Choice | Account ID | Type    | Balance");
        account_list.forEach(
            (account) -> {
              System.out.println(
                  (account_list.indexOf(account) + 1)
                      + "      | "
                      + account.getID()
                      + "          | "
                      + account.getAccountType()
                      + " | "
                      + account.getBalance());
            });
        Helper.printLine(80);
      } else {
        System.out.println(
            ConsoleColours.RED_BOLD
                + "This customer does not have a bank account!"
                + ConsoleColours.RESET
                + "\u274C");
        Helper.pause();
        break;
      }
      /*
       * check on right choice input
       */
      while (true) {
        try {
          System.out.println("Select one account you want to continue on: ");
          System.out.print(String.format("%-50s: ", "Choice"));
          int choice = Integer.parseInt(Helper.readLine());
          double balance_current = account_list.get(choice - 1).getBalance();
          System.out.println(
              "Current Balance for this account is: " + "\uD83D\uDCB0" + balance_current);

          /*
           * Check for the right amount input
           */
          // TODO: savings has a min amount?
          while (true) {
            double input_amount = Helper.Amount_input_Checker();
            Customer customer_user = Database.getCustomer(username).get();
            Account account = account_list.get(choice - 1);
            if (input_amount <= balance_current && input_amount > 0) {
              float balance_after_withdraw = (float) (balance_current - input_amount);
              Database.updateBalance(account, balance_after_withdraw);
              Database.createTransaction(
                  customer_user, account, TransactionType.DEBIT, input_amount);
              System.out.println(
                  ConsoleColours.GREEN
                      + "Withdraw Successful!"
                      + ConsoleColours.RESET
                      + "\uD83C\uDF89");
              break;
            } else if (input_amount > balance_current) {
              System.out.println(
                  ConsoleColours.YELLOW
                      + "Insufficient Cash inside!"
                      + ConsoleColours.RESET
                      + "\uD83E\uDD7A");
              break;
            } else if (input_amount <= 0) {
              System.out.println(
                  ConsoleColours.RED_BOLD
                      + "Please enter a valid and positive number!"
                      + ConsoleColours.RESET
                      + "\uD83E\uDD7A");
              continue;
            }
          }
          break;
        } catch (Exception e) {
          System.out.println(
              ConsoleColours.RED_BOLD
                  + "Invalid choice input!"
                  + ConsoleColours.RESET
                  + "\uD83E\uDD7A");
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
