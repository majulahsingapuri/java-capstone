package capstone.Views;

import capstone.Objects.Database;

public final class AdminChangePasswordView extends View {
  public void print() {
    clearScreen("Admin Change Password Page");
    Database.CURRENT_USER.changePassword();
  }
}
