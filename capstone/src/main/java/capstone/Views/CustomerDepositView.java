package capstone.Views;

import capstone.Enums.AccessLevel;
import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;

public final class CustomerDepositView extends View{

    public void print() {
        clearScreen("Customer Deposit Page");

        while(true){

            try { 
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

        }
    }
}
