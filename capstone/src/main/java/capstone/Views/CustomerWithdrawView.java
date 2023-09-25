package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;

public final class CustomerWithdrawView extends View {
    public void print() {
        clearScreen("Customer Withdraw Page");

        while(true){
            try { 
                System.out.println("Enter the amount you want to withdraw");
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
