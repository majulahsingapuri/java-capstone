package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Teller;
import capstone.Objects.User;
import java.util.Optional;

public final class AdminDisplayView extends View {
  public void print() {
    clearScreen("Admin Account Display Page");
    // Boolean isEmpty;
    while (true) {

      // display password & username & balance & access level
      String domain = Helper.getUserDomain();

      if (domain.equals("admin")) {
        Helper.getUser("admin", "Enter the admin username");
        // System.out.print(String.format("%-50s: ", "Enter the admin username"));
        // String username = Helper.readLine();
        // Optional<Admin> queryRes = Database.getAdmin(username);
        // isEmpty = queryRes.isEmpty();
        // if (isEmpty)
        //   System.out.println(
        //       "Admin with username " + username + " can not be found" + "\uD83E\uDD7A");
        // else {
        //   Helper.printUserInfo((User) queryRes.get());
        //   Helper.printLine(80);
        // }
      } else if (domain.equals("teller")) {
        Helper.getUser("teller", "Enter the Teller username");
        // System.out.print(String.format("%-50s: ", "Enter the Teller username"));
        // String username = Helper.readLine();
        // Optional<Teller> queryRes = Database.getTeller(username);
        // isEmpty = queryRes.isEmpty();
        // if (isEmpty)
        //   System.out.println(
        //       "Teller with username " + username + " can not be found" + "\uD83E\uDD7A");
        // else {
        //   Helper.printUserInfo((User) queryRes.get());
        //   Helper.printLine(80);
        // }
      } else {
        Helper.getUser("customer", "Enter the customer username");
        // System.out.print(String.format("%-50s: ", "Enter the customer username"));
        // String username = Helper.readLine();
        // Optional<Customer> queryRes = Database.getCustomer(username);
        // isEmpty = queryRes.isEmpty();
        // Customer customer = queryRes.get();
        // if (isEmpty)
        //   System.out.println(
        //       "Customer with username " + username + " can not be found" + "\uD83E\uDD7A");
        // else {
        //   Helper.printUserInfo((User) customer);
        //   System.out.print(String.format("%-50s: %s%n", "NRIC", customer.getNRIC()));
        //   System.out.print(String.format("%-50s: %s%n", "email", customer.getEmail()));
        //   System.out.print(String.format("%-50s: %s%n", "dateOfBirth", customer.getDateOfBirth()));
        //   System.out.print(String.format("%-50s: %s%n", "phoneNumber", customer.getPhoneNumber()));
        //   System.out.print(String.format("%-50s: %s%n", "address", customer.getAddress()));
        //   Helper.printLine(80);
        // }
      }
      ;
      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
