package capstone;

import javax.xml.crypto.Data;

import capstone.Objects.Database;
import capstone.Objects.User;
import capstone.Views.LoginView;

/** Main Class that runs the program. */
public class Main {
  /**
   * Main function that is the starting point of the application. Initialises a new {@link Database}
   * and loads in the first view.
   *
   * @param args Arguments passsed to the App.
   */
  public static void main(String[] args) {
    new Database();
    // Database.createAdmin("aa","pp", "firstname12", "lastname12");
    User user = Database.getUser("aa").get();
    Database.updateUserFirstName(user, "sdas");
    // LoginView view = new LoginView();
    // view.print();
    Database.close();
  }
}
