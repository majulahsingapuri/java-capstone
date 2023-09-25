package capstone.Views;

import capstone.Extras.Helper;

public final class TellerDisplayTellerView extends View {
  public void print() {
    clearScreen("Teller Account Display Page");

    while (true) {

      // display password & username & balance & access level for this teller user

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
