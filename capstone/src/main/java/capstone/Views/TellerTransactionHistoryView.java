package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;
import capstone.Objects.User;

public final class TellerTransactionHistoryView extends View{
    public void print() {
        clearScreen("Teller Transaction History Page");

        // TODO:
        while(true){
            try {
                // enter the account number teller wants to check on
                System.out.println("Enter the customer account number:");
                String customer_account_string = Helper.readLine();// convert to int type
                if (Database.containsUser(customer_account_string)) {
                    User customer_user = Database.getUser(customer_account_string);
                }else {
                    System.out.println("This customer does not exist! Please re-enter!");
                    Helper.pause();
                }
            }catch (Exception e){

            }
        }
    }
}
