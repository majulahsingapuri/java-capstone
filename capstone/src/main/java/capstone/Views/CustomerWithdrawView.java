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

            System.out.println("Continue? [Y/N]: ");
            String str_input = Helper.sc.nextLine();
            
            if (str_input.equals("N")){
                break;
            }else if (str_input.equals("Y")){
                continue;
            }else {
                System.out.println("Wrong Input, please input Y/N or y/n");
            }
            // TODO: 
            // SQL related lines/database lines here, update
        }
    }
}
