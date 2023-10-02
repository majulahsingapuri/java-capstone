package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Database;

public final class TellerDisplayTellerView extends View {
  public void print() {

    while (true) {
      clearScreen("Teller Account Display Page");

      Helper.printUserInfo(Database.CURRENT_USER);
      Helper.printLine(80);

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
