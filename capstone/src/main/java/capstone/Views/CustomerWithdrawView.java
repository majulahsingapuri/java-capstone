package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import java.util.ArrayList;
import java.util.HashMap;

public final class CustomerWithdrawView extends View {
  public void print() {

    while (true) {
      clearScreen("Customer Withdraw Page");

      String username = Database.CURRENT_USER.getUsername();
      ArrayList<Account> account_list = Database.getCustomerAccounts(username);
      Customer customer_user = (Customer) Database.CURRENT_USER;

      String account_exist = Helper.display_customer_accounts(username);
      if (account_exist == "No Account") {
        break;
      }

      /*
       * check on right choice input
       */
      while (true) {
        String choice_result_str = "Valid";
        HashMap<String, Integer> choice_map = Helper.check_account_choice_input(account_list);
        for (String result_str : choice_map.keySet()) {
          choice_result_str = result_str;
        }
        if (choice_result_str != "Valid") {
          break;
        }
        /*
         * Check for the right amount input
         */

        while (true) {
          String withdraw_result =
              Helper.customer_withdraw(customer_user, account_list, choice_map);
          if (withdraw_result == "Withdraw Success") {
            break;
          } else if (withdraw_result == "Insufficient Cash inside") {
            break;
          } else if (withdraw_result == "Invalid Input") {
            continue;
          }
        }
        break;
      }
      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else if (continue_checker == 0) {
        Helper.printLine(80);
        continue;
      }
    }
  }
}
