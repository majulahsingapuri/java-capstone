package capstone.Views;

import capstone.Extras.Helper;

public final class AdminAddUser extends View {
  public void print() {
    clearScreen("Admin Add User Page");

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
