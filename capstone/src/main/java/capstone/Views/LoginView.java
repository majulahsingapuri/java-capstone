package capstone.Views;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import capstone.Enums.AccessLevel;
import capstone.Extras.Helper;
import capstone.Objects.Database;
import capstone.Objects.User;
import capstone.Objects.Admin;
import capstone.Objects.Teller;
import capstone.Objects.Customer;


/**
 * Initial View seen by {@link User}. Used to log in the User to the System.
 * @author Bhargav, Kah Ee
 * @version 1.0
 * @since 2020-11-1
 */
public final class LoginView extends View {

    /**
     * Constructor method for the login page
     */
    public LoginView() {

    }

    /**
     * Displays Login view for {@link User}, checks the credentials and then logs in the User if the credentials are valid. Required method from {@link View}.
     */
    public void print() {

        clearScreen("Login");

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        String password, username, domain;

        while (true) {

            DateTime now = DateTime.now();
            System.out.println("Current Time: " + formatter.print(now));
            System.out.println("");
            
            System.out.println("Enter user domain");
            System.out.println("1. Admin");
            System.out.println("2. Bank");
            System.out.println("3. Customer");
            while (true) {

                System.out.print(String.format("%-50s: ", "Choice"));
                domain = Helper.readLine();
                
                if (domain.equals("Q")) {
                    return;
                } else if (domain.equals("1")) {
                    System.out.print(String.format("%-50s: ", "Enter admin username"));
                    break;
                } else if (domain.equals("2")) {
                    System.out.print(String.format("%-50s: ", "Enter teller username"));
                    break;
                } else if (domain.equals("3")) {
                    System.out.print(String.format("%-50s: ", "Enter customer username"));
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            }

            username = Helper.readLine();
            
            System.out.print(String.format("%-50s: ", "Enter password"));
            password = Helper.getPasswordInput();
            
            if (Database.containsUser(username)) {
                User result = Database.getUser(username);
                if (result.checkPassword(password)) {
                    if (domain.equals("1") && result.getAccessLevel() == AccessLevel.ADMIN) {
                        try{
                            Database.CURRENT_USER = (Admin) result;
                            Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
                        } catch (Exception e) {
                            System.out.println("Invalid user. Please try again");
                        }
                        AdminMainView adminView = new AdminMainView();
                        adminView.print();
                    } else if (domain.equals("2") && result.getAccessLevel() == AccessLevel.TELLER) {
                        try {
                            Database.CURRENT_USER = (Teller) result;
                            Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
                        } catch (Exception e) {
                            System.out.println("Invalid user. Please enter again!");
                        }
                        TellerMainView view = new TellerMainView();
                        view.print();
                    } else if (domain.equals("3") && result.getAccessLevel() == AccessLevel.CUSTOMER) {
                        try {
                            Database.CURRENT_USER = (Customer) result;
                            Database.CURRENT_ACCESS_LEVEL = Database.CURRENT_USER.getAccessLevel();
                        } catch (Exception e) {
                            System.out.println("Invalid user. Please enter again!");
                        }
                        CustomerMainView view = new CustomerMainView();
                        view.print();
                    } else {
                        System.out.println("Invalid domain. Please enter domain again.\n");
                    }
                } else {
                    System.out.println("Invalid Password\n");
                }
                
            } else {
                System.out.println("Invalid Username\n");
            }        
        }
    }
}