package capstone.Views;

import capstone.Enums.TransactionType;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.ArrayList;

public final class TellerTransferView extends View {
  public void print() {
    while (true) {
      clearScreen("Teller Transfer Page");

      System.out.println(
          ConsoleColours.GREEN_BRIGHT
              + "Please enter the transferer username"
              + ConsoleColours.RESET);
      String username1 = Helper.customer_search();
      Customer customer_1 = Database.getCustomer(username1).get();
      if (username1 == null) {
        continue;
      }

      ArrayList<Account> account_list1 = Database.getCustomerAccounts(username1);
      if (account_list1.isEmpty()) {
        System.out.println(
            ConsoleColours.RED_BOLD
                + "This customer does not have a bank account!"
                + ConsoleColours.RESET
                + "\u274C");
        Helper.pause();
        break;
      }

      System.out.println("Here are all the accounts under customer: " + username1);
      Helper.printLine(80);
      System.out.println("Choice | Account ID | Type    | Balance");

      for (int i = 0; i < account_list1.size(); i++) {
        Account account1 = account_list1.get(i);
        System.out.println(
            (i + 1)
                + "      | "
                + account1.getID()
                + "          | "
                + account1.getAccountType()
                + " | "
                + account1.getBalance());
      }

      Helper.printLine(80);

      int choice1;
      while (true) {
        System.out.println("Select one account you want to continue on: ");
        System.out.print(String.format("%-50s: ", "Choice"));
        try {
          choice1 = Integer.parseInt(Helper.readLine());
          if (choice1 >= 1 && choice1 <= account_list1.size()) {
            break;
          } else {
            System.out.println(
                ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
          }
        } catch (NumberFormatException e) {
          System.out.println(
              ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
        }
      }

      double balance_current1 = account_list1.get(choice1 - 1).getBalance();
      System.out.println("Current Balance for this account is: " + balance_current1);

      System.out.println(
          ConsoleColours.GREEN_BRIGHT
              + "Please enter the transferee username"
              + ConsoleColours.RESET);
      String username2 = Helper.customer_search();
      Customer customer_2 = Database.getCustomer(username2).get();

      if (username2 == null) {
        continue;
      }

      ArrayList<Account> account_list2 = Database.getCustomerAccounts(username2);
      if (account_list2.isEmpty()) {
        System.out.println(
            ConsoleColours.RED_BOLD
                + "This customer does not have a bank account!"
                + ConsoleColours.RESET
                + "\u274C");
        Helper.pause();
        break;
      }

      System.out.println("Here are all the accounts under customer: " + username2);
      Helper.printLine(80);
      System.out.println("Choice | Account ID | Type    | Balance");

      for (int i = 0; i < account_list2.size(); i++) {
        Account account2 = account_list2.get(i);
        System.out.println(
            (i + 1)
                + "      | "
                + account2.getID()
                + "          | "
                + account2.getAccountType()
                + " | "
                + account2.getBalance());
      }

      Helper.printLine(80);

      int choice2;
      while (true) {
        System.out.println("Select one account you want to continue on: ");
        System.out.print(String.format("%-50s: ", "Choice"));
        try {
          choice2 = Integer.parseInt(Helper.readLine());
          if (choice2 >= 1 && choice2 <= account_list2.size()) {
            break;
          } else {
            System.out.println(
                ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
          }
        } catch (NumberFormatException e) {
          System.out.println(
              ConsoleColours.RED_BOLD + "Invalid choice input!" + ConsoleColours.RESET);
        }
      }

      double balance_current2 = account_list2.get(choice2 - 1).getBalance();
      System.out.println("Current Balance for this account is: " + balance_current2);

      Account account1 = account_list1.get(choice1 - 1);
      Account account2 = account_list2.get(choice2 - 1);

      double transfer_amount = Helper.Amount_Transfer_Checker();

      if (transfer_amount <= balance_current1 && transfer_amount > 0) {
        double balance_after_transfer_transferer = balance_current1 - transfer_amount;
        double balance_after_transfer_transferee = balance_current2 + transfer_amount;
        Database.updateBalanceForTransfer(account1, balance_after_transfer_transferer);
        Database.updateBalanceForTransfer(account2, balance_after_transfer_transferee);
        Database.createTransaction(customer_1, account1, TransactionType.CREDIT, transfer_amount);
        Database.createTransaction(customer_2, account2, TransactionType.DEBIT, transfer_amount);
        System.out.println(
            ConsoleColours.GREEN
                + "Transfer Successful of "
                + transfer_amount
                + " from "
                + username1
                + " to "
                + username2
                + " !"
                + ConsoleColours.RESET);
      } else if (transfer_amount > balance_current1) {
        System.out.println(
            ConsoleColours.YELLOW + "Insufficient Cash inside!" + ConsoleColours.RESET);
      } else if (transfer_amount <= 0) {
        System.out.println(
            ConsoleColours.RED_BOLD
                + "Please enter a valid and positive number!"
                + ConsoleColours.RESET);
      }

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        Helper.printLine(80);
        continue;
      }
    }
  }
}
