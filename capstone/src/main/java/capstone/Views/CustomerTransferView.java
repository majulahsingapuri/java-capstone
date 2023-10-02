package capstone.Views;

import capstone.Enums.TransactionType;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.ArrayList;
import java.util.HashMap;

public final class CustomerTransferView extends View {
  public void print() {

    while (true) {
      clearScreen("Customer Transfer Page");
      // find out username 1
      String username1 = Database.CURRENT_USER.getUsername();
      Customer customer_1 = Database.getCustomer(username1).get();

      ArrayList<Account> account_list1 = Database.getCustomerAccounts(username1);

      String account_exist = Helper.display_customer_accounts(username1);
      if (account_exist == "No Account") {
        break;
      }

      int choice1 = 0;
      double balance_current1 = 0.0;

      // int choice1;
      while (true) {
        String choice_result_str = "Valid";
        HashMap<String, Integer> choice_map = Helper.check_account_choice_input(account_list1);
        for (String result_str : choice_map.keySet()) {
          choice_result_str = result_str;
        }
        if (choice_result_str != "Valid") {
          continue;
        }

        choice1 = choice_map.get("Valid");
        balance_current1 = account_list1.get(choice1 - 1).getBalance();
        System.out.println(
            "Current Balance for this account is: " + "\uD83D\uDCB0" + balance_current1);
        Helper.printLine(80);
        break;
      }

      while (true) {
        // find out username 2
        String username2 = Helper.customer_search();
        if (username2 == null) {
          continue;
        }
        Customer customer_2 = Database.getCustomer(username2).get();

        ArrayList<Account> account_list2 = Database.getCustomerAccounts(username2);

        String account_exist2 = Helper.display_customer_accounts(username2);
        if (account_exist2 == "No Account") {
          break;
        }

        int choice2 = 0;
        double balance_current2 = 0.0;

        while (true) {
          String choice_result_str2 = "Valid";
          HashMap<String, Integer> choice_map2 = Helper.check_account_choice_input(account_list2);
          for (String result_str : choice_map2.keySet()) {
            choice_result_str2 = result_str;
          }
          if (choice_result_str2 != "Valid") {
            continue;
          }
          choice2 = choice_map2.get("Valid");
          balance_current2 = account_list2.get(choice2 - 1).getBalance();
          System.out.println(
              "Current Balance for this account is: " + "\uD83D\uDCB0" + balance_current2);
          break;
        }

        Account account1 = account_list1.get(choice1 - 1);
        Account account2 = account_list2.get(choice2 - 1);

        while (true) {
          double transfer_amount = Helper.Amount_Transfer_Checker();

          if (transfer_amount <= balance_current1 && transfer_amount > 0) {
            double balance_after_transfer_transferer = balance_current1 - transfer_amount;
            double balance_after_transfer_transferee = balance_current2 + transfer_amount;
            Database.updateBalanceForTransfer(account1, balance_after_transfer_transferer);
            Database.updateBalanceForTransfer(account2, balance_after_transfer_transferee);
            Database.createTransaction(
                customer_1, account1, TransactionType.CREDIT, transfer_amount);
            Database.createTransaction(
                customer_2, account2, TransactionType.DEBIT, transfer_amount);
            System.out.println(
                ConsoleColours.GREEN
                    + "Transfer Successful of "
                    + transfer_amount
                    + " from "
                    + username1
                    + " account: "
                    + account1.getID()
                    + " to "
                    + username2
                    + " account: "
                    + account2.getID()
                    + " !"
                    + ConsoleColours.RESET);
            break;
          } else if (transfer_amount > balance_current1) {
            System.out.println(
                ConsoleColours.YELLOW + "Insufficient Cash inside!" + ConsoleColours.RESET);
            continue;
          } else if (transfer_amount <= 0) {
            System.out.println(
                ConsoleColours.RED_BOLD
                    + "Please enter a valid and positive number!"
                    + ConsoleColours.RESET);
            continue;
          }
        }
        break;
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
