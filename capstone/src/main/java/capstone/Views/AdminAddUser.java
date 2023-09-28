package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Teller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public final class AdminAddUser extends View {
  public void print() {
    clearScreen("Admin Add User Page");

    while (true) {

      String domain = Helper.getUserDomain();
      Boolean isEmpty;
      if (domain.equals("admin")) {
        String[] ret = (String[])Helper.getUserAttributes(false,true);
        Optional<Admin> queryRes = Database.createAdmin(ret[0],ret[1],ret[2],ret[3]);
        isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Admin failed, please try again");
        else System.out.println("Admin has been created successfully");
      }
      else if (domain.equals("teller")) {
        String[] ret = (String[])Helper.getUserAttributes(false,true);
        Optional<Teller> queryRes = Database.createTeller(ret[0], ret[1], ret[2], ret[3]);
        isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Teller failed, please try again");
        else System.out.println("Teller has been created successfully");
      } 
      else { //Customer daomin

        Object[] ret = Helper.getUserAttributes(true,true);

        Optional<Customer> queryRes =
            Database.createCustomer(
                (String)ret[0], (String)ret[1], (String)ret[2], (String)ret[3], (String)ret[4], (String)ret[5], (Date)ret[6], (String)ret[7], (String)ret[8]);
        isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Customer failed, please try again");
        else System.out.println("Customer has been created successfully");
      }

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
