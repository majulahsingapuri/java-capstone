package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.ArrayList;

public final class TellerDisplayCustomerView extends View {
  public void print() {

    while (true) {
      clearScreen("Teller Account Display Page");

      Customer customer = Helper.customer_search();

      // [x] how to get password to display
      Helper.printUserCredentials(
          "Customer Credentials",
          customer.getFirstName(),
          customer.getLastName(),
          customer.getNRIC(),
          customer.getEmail(),
          customer.getDateOfBirth(),
          customer.getAddress(),
          customer.getPhoneNumber());
      Helper.printLine(80);

      System.out.println("Accounts List" + "\uD83D\uDCDA");

      ArrayList<Account> account_list = Database.getCustomerAccounts(customer.getUsername());
      if (account_list.size() > 0) {
        for (Account account : account_list) {
          Helper.printLine(80);
          System.out.print(String.format("%-50s: %s%n", "Account ID", account.getID()));
          System.out.print(String.format("%-50s: %s%n", "Account Type", account.getAccountType()));
          System.out.print(String.format("%-50s: %s%n", "Balance", account.getBalance()));
        }
      } else {
        Helper.printLine(80);
        System.out.println(
            ConsoleColours.YELLOW
                + "No Accounts Available for current customer:"
                + customer.getUsername()
                + ConsoleColours.RESET);
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
