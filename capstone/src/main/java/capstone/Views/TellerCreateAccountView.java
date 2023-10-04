package capstone.Views;

import capstone.Enums.AccountType;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.Optional;

public class TellerCreateAccountView extends View {

  public void print() {

    while (true) {
      clearScreen("Teller Account Display Page");
      System.out.println(
          ConsoleColours.GREEN_BRIGHT
              + "Please enter the customer username"
              + ConsoleColours.RESET);

      Customer customer = Helper.customer_search();

      Optional<Account> acc = null;
      while (true) {

        System.out.println(
            ConsoleColours.GREEN_BRIGHT
                + "Select which type of Account you would like to create "
                + ConsoleColours.RESET);
        System.out.println("1: Current Account");
        System.out.println("2: Savings Account");
        String account_type = Helper.readLine();

        if (account_type.equals("1")) {
          acc = Database.createAccount(customer, AccountType.CURRENT);
          break;
        } else if (account_type.equals("2")) {
          acc = Database.createAccount(customer, AccountType.SAVINGS);
          break;
        } else {
          System.out.println(
              ConsoleColours.RED + "Invalid input, Select either 1 or 2" + ConsoleColours.RESET);
        }
      }

      if (acc.isEmpty()) {
        System.out.println("Something wrong");
      }

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        continue;
      }
    }
  }
}
