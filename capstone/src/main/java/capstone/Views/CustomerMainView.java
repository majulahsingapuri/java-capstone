package capstone.Views;

import capstone.Extras.Helper;
import capstone.Objects.Database;

/**
 * The main menu for the Customer side of the Application.
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class CustomerMainView extends View {


    /**
     * Prints menu options for the User to select from. Required method from {@link View}.
     */
    public void print() {
        
        int choice;
        do {
            try {

                clearScreen("Customer Main");

                System.out.println("What would you like to do?");
                System.out.println("1: Change your Password");
                System.out.println("2: Logout");
                System.out.println("3: Deposit");
                System.out.println("4: Withdraw");
                System.out.println("5: Transfer");
                System.out.println("6: Transaction History");
                System.out.println("7: Display Account Info");
                System.out.print(String.format("%-50s: ", "Choice"));
                choice = Integer.parseInt(Helper.readLine());

                switch (choice) { 
                    case 1:
                        changePassword();
                        break;
                    case 2:
                        LogoutView logoutView = new LogoutView();
                        logoutView.print();
                        return;
                    case 3:
                        Deposit();
                        break;
                    case 4:
                        Withdraw();
                        break;
                    case 5:
                        Transfer();
                        break;
                    case 6:
                        Transactions_History();
                        break;
                    case 7:
                        Display();
                        break;
                    default:
                        System.out.println("Please enter valid option.");
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        } while (true);
    }

    /**
     * Changes the Password for the {@link Database#CURRENT_USER}.
     */
    private void changePassword() {

        Database.CURRENT_USER.changePassword();
        Helper.pause();

    }

    private void Deposit() {

        Database.CURRENT_USER.Deposit();
        Helper.pause();
    }

    private void Withdraw() {

        Database.CURRENT_USER.Withdraw();
        Helper.pause();
    }

    private void Transactions_History() {

        Database.CURRENT_USER.Transactions_History();
        Helper.pause();
    }

    private void Display() {

        Database.CURRENT_USER.Display();
        Helper.pause();
    }

    private void Transfer() {

        Database.CURRENT_USER.Transfer();
        Helper.pause();
    }
}
