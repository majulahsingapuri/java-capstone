package capstone.Views;

import capstone.Extras.Helper;

public final class AdminDisplayView extends View {
  public void print() {
    clearScreen("Admin Account Display Page");
    // Boolean isEmpty;
    while (true) {

      // display password & username & balance & access level
      String domain = Helper.getUserDomain();

      if (domain.equals("admin")) {
        Helper.getUser("admin", "Enter the admin username");

      } else if (domain.equals("teller")) {
        Helper.getUser("teller", "Enter the Teller username");

      } else {
        Helper.getUser("customer", "Enter the customer username");
      }
      ;
      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
