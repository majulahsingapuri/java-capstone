package capstone.Views;

import capstone.Extras.Helper;

public final class TellerTransactionHistoryView extends View {
  public void print() {
    clearScreen("Teller Transaction History Page");

    // TODO:
    while (true) {
      Helper.customer_search(); // search the target customer from teller side

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}