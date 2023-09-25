package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;
import capstone.Objects.User;

public final class TellerTransferView extends View {
    public void print() {
        clearScreen("Teller Transfer Page");

        while(true){
            Helper.customer_search();// search the target customer from teller side

            try {
                // input account info
                System.out.println("Enter the account number you want to transfer to:");
                String account_string = Helper.readLine();// convert to int type
                // check whether account exist
                if (Database.containsUser(account_string)) {
                    User reveiver_user = Database.getUser(account_string);
                }else {
                    System.out.println("This payee does not exist! Please re-enter!");
                    Helper.pause();
                }

                System.out.println("Enter the amount you want to transfer");
                Double deposit_amount = Helper.sc.nextDouble();
                System.out.println("Transfer amount to account: " + account_string + " is: " +deposit_amount);
                Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input

                // TODO: double check on the transfer info?

            } catch (Exception e) {
                System.out.println(ConsoleColours.RED_BOLD + "NOT A VALID NUMBER" + ConsoleColours.RESET);
                Helper.sc.nextLine(); // this line ensures next .nextLine() consume propoerly for next input
                
            }


            int continue_checker = Helper.continue_checker();
            if (continue_checker == 1){break;}else{continue;}
    }
}
}
