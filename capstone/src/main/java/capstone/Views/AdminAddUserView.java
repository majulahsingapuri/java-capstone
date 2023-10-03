package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Teller;
import java.util.Date;
import java.util.Optional;

public final class AdminAddUserView extends View {
  public void print() {
    clearScreen("Admin Add User Page");

    while (true) {

      String domain = Helper.getUserDomain();
      Boolean isEmpty;
      if (domain.equals("admin")) {
        System.out.println(
            Helper.ANSI_BLUE + "Please enter the new admin credentials" + Helper.ANSI_RESET);
        Object[] ret = Helper.getUserAttributes(false, true);
        Optional<Admin> queryRes =
            Database.createAdmin(
                ret[0].toString(), ret[1].toString(), ret[2].toString(), ret[3].toString());
        isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Admin failed, please try again");
        else System.out.println("Admin has been created successfully");
      } else if (domain.equals("teller")) {
        System.out.println(
            Helper.ANSI_BLUE + "Please enter the new teller credentials" + Helper.ANSI_RESET);
        Object[] ret = Helper.getUserAttributes(false, true);
        Optional<Teller> queryRes =
            Database.createTeller(
                ret[0].toString(), ret[1].toString(), ret[2].toString(), ret[3].toString());
        isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Teller failed, please try again");
        else System.out.println("Teller has been created successfully");
      } else { // Customer daomin
        System.out.println(
            Helper.ANSI_BLUE + "Please enter the new customer credentials" + Helper.ANSI_RESET);
        Object[] ret = Helper.getUserAttributes(true, true);

        Optional<Customer> queryRes =
            Database.createCustomer(
                (String) ret[0],
                (String) ret[1],
                (String) ret[2],
                (String) ret[3],
                (String) ret[4],
                (String) ret[5],
                (Date) ret[6],
                (String) ret[7],
                (String) ret[8]);
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
