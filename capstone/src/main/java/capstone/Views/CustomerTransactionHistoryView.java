package capstone.Views;

import capstone.Extras.Helper;

public final class CustomerTransactionHistoryView extends View {
  public void print() {
    clearScreen("Customer Transaction History Page");

    while (true) {

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
