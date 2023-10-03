package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Transaction;
import java.util.ArrayList;

public final class CustomerTransactionHistoryView extends View {
  public void print() {

    while (true) {
      clearScreen("Customer Transaction History Page");

      Customer customer = (Customer) Database.CURRENT_USER;
      String username = Database.CURRENT_USER.getUsername();

      ArrayList<Transaction> transaction_list = Database.getCustomerTransactions(customer);
      System.out.println("Transaction History For User:" + username + "\uD83D\uDCDC");
      if (transaction_list.size() > 0) {
        Helper.printLine(80);
        System.out.println(
            "Account ID | Ref no.                               | Transaction Type   | Amount  |"
                + " Date ");
        for (Transaction transaction : transaction_list) {
          System.out.println(
              String.format(
                  "%-10s | %-37s | %-18s | %-7s | %-31s",
                  transaction.getAccountNoID(),
                  transaction.getTransactionRef(),
                  transaction.getTransactionType(),
                  transaction.getAmount(),
                  transaction.getDate()));
        }
      } else {
        Helper.printLine(80);
        System.out.println(
            ConsoleColours.YELLOW
                + "No Transactions History for current customer:"
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
