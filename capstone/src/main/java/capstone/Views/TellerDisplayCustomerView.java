package capstone.Views;

import capstone.Extras.Helper;

public final class TellerDisplayCustomerView extends View {
  public void print() {

    while (true) {
      clearScreen("Teller Account Display Page");
      Helper.customer_search(); // search the target customer from teller side

      // display password & username & balance & access level

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
