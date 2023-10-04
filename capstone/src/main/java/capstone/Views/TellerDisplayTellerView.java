package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Database;
import capstone.Objects.Teller;

public final class TellerDisplayTellerView extends View {
  public void print() {

    while (true) {
      clearScreen("Teller Account Display Page");

      String username = Database.CURRENT_USER.getUsername();

      Teller teller = Database.getTeller(username).get();

      // [x] how to get password to display
      Helper.printUserCredentials(
          "Customer Credentials",
          teller.getFirstName(),
          teller.getLastName(),
          null,
          null,
          null,
          null,
          null);

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
