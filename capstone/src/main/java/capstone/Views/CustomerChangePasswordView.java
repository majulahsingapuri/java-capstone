package capstone.Views;

import capstone.Objects.Database;

public final class CustomerChangePasswordView extends View {
  public void print() {
    clearScreen("Customer Change Password Page");
    Database.CURRENT_USER.changePassword();
  }
}
