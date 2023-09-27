package capstone.Views;

import capstone.Extras.Helper;

public final class CustomerWithdrawView extends View {
  public void print() {
    clearScreen("Customer Withdraw Page");

    while (true) {
      double input_amount = Helper.number_checker();

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
