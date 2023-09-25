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

            try { 
                // enter the account number teller wants to check on
                System.out.println("Enter the customer account number:");
                String account_string = Helper.readLine();// convert to int type
                if (Database.containsUser(account_string)) {
                    User customer_user = Database.getUser(account_string);
                }else {
                    System.out.println("This payee does not exist! Please re-enter!");
                    Helper.pause();
                }

                System.out.println("Enter the amount you want to deposit");
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
