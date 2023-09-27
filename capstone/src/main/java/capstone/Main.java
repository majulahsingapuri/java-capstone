package capstone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import capstone.Objects.Database;
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
    // Database.createAdmin("ADMIN","PASSWORD", "firstname1", "lastname1");
    LoginView view = new LoginView();
    view.print();
    Database.close();
  
  }
}
