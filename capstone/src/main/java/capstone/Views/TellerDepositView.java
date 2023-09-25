package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;
import capstone.Objects.User;

public final class TellerDepositView extends View{

    public void print() {
        clearScreen("Teller Deposit Page");

        // TODO: add 1 more step for teller 
        // --------------------------------

        while(true){

            Helper.customer_search();// search the target customer from teller side

            try { 
                // enter the account number teller wants to check on

                System.out.println("Enter the amount you want to deposit");
                Double deposit_amount = Helper.sc.nextDouble();
                System.out.println("deposit amount:" + deposit_amount);
                Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input

            } catch (Exception e) {
                System.out.println(ConsoleColours.RED_BOLD + "NOT A VALID NUMBER" + ConsoleColours.RESET);
                Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input
                
            }

            int continue_checker = Helper.continue_checker();
            if (continue_checker == 1){break;}else{continue;}
            // TODO: 
            // SQL related lines/database lines here, update
        }
    }
}
