package capstone.Views;

import capstone.Objects.Database;

public final class TellerChangePassword extends View {
  public void print() {
    clearScreen("teller Change Password Page");
    Database.CURRENT_USER.changePassword();
  }
}
