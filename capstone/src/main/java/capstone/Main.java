package capstone;

import java.util.Date;

import org.joda.time.DateTime;

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
    
    Date dob_date = DateTime.now().toDate();
    Database.createAdmin("ADMIN11", "PASSWORD", "firstname1", "lastname1");
    Database.createTeller("teller1", "password", "tellerF", "tellerL");
    Database.createCustomer(
      "customer_5",
      "123456",
      "cus_fn_1",
      "cus_fn_1",
      "newNric",
      "ddl@email.com",
      dob_date,
      "hello road_1",
      "67265511");
    LoginView view = new LoginView();
    view.print();
    Database.close();
  }
}
