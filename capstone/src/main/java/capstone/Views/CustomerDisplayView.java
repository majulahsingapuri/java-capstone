package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.ArrayList;

public final class CustomerDisplayView extends View {
  public void print() {
    clearScreen("Customer Account Display Page");

    while (true) {
      // display password & username & balance & access level

      Customer customer = (Customer) Database.CURRENT_USER;

      Helper.printUserInfo(Database.CURRENT_USER);
      System.out.print(String.format("%-50s: %s%n", "NRIC", customer.getNRIC()));
      System.out.print(String.format("%-50s: %s%n", "email", customer.getEmail()));
      System.out.print(String.format("%-50s: %s%n", "dateOfBirth", customer.getDateOfBirth()));
      System.out.print(String.format("%-50s: %s%n", "phoneNumber", customer.getPhoneNumber()));
      System.out.print(String.format("%-50s: %s%n", "address", customer.getAddress()));
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
        System.out.println("No Accounts Available for current customer:" + customer.getUsername());
      }

      Helper.printLine(80);

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
