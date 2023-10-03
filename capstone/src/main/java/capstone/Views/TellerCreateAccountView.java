package capstone.Views;

import capstone.Enums.AccountType;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Customer;
import capstone.Objects.Database;

public class TellerCreateAccountView extends View {

  public void print() {

    while (true) {
      clearScreen("Teller Account Display Page");
      System.out.println(
          ConsoleColours.GREEN_BRIGHT
              + "Please enter the customer username"
              + ConsoleColours.RESET);
      String username = Helper.customer_search();
      if (username == null) {
        continue;
      }
      Customer customer = Database.getCustomer(username).get();

      System.out.println(
          ConsoleColours.GREEN_BRIGHT
              + "Select which type of Account you would like to create "
              + ConsoleColours.RESET);
      System.out.println("1: Current Account");
      System.out.println("2: Savings Account");
      String account_type = Helper.readLine();
      ;

      if (account_type.equals("1")) {
        Database.createAccount(customer, AccountType.CURRENT).get();
        break;
      } else if (account_type.equals("2")) {
        Database.createAccount(customer, AccountType.SAVINGS).get();
        break;
      } else {
        System.out.println(
            ConsoleColours.RED + "Invalid input, Select either 1 or 2" + ConsoleColours.RESET);
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
