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

      if (domain.equals("admin")) {
        String[] ret = Helper.getUserAttributes();
        Optional<Admin> queryRes = Database.createAdmin(ret[0], ret[1], ret[2], ret[3]);
        Boolean isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Admin failed, please try again");
        else System.out.println("Admin has been created successfully");
      }
      else if (domain.equals("teller")) {
        String[] ret = Helper.getUserAttributes();
        Optional<Teller> queryRes = Database.createTeller(ret[0], ret[1], ret[2], ret[3]);
        Boolean isEmpty = queryRes.isEmpty();
        if (isEmpty) System.out.println("Create Teller failed, please try again");
        else System.out.println("Teller has been created successfully");
      } 
      else {
        Date dateOfBirth;
        String[] ret = Helper.getUserAttributes();

        String nric = Helper.getInputWithValidation("Enter nric", Helper::isValidNric, false);

        String email = Helper.getInputWithValidation("Enter email", Helper::isValidEmail, false);

        while (true) {
          System.out.print(String.format("%-50s: ", "Enter dateOfBirth in the format yyyy-mm-dd"));
          String date = Helper.readLine();
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          try {
            dateOfBirth = dateFormat.parse(date);
            break;
          } catch (ParseException e) {
            System.out.println("\u001B[31mInvalid input format. Please try again.\u001B[0m");
          }
        }

        System.out.print(String.format("%-50s: ", "Enter address"));
        String address = Helper.readLine();

        String phoneNumber = Helper.getInputWithValidation("Enter phonenumber", Helper::isValidPhoneNumber, false);

        Optional<Customer> queryRes =
            Database.createCustomer(
                ret[0], ret[1], ret[2], ret[3], nric, email, dateOfBirth, address, phoneNumber);
        Boolean isEmpty = queryRes.isEmpty();
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
