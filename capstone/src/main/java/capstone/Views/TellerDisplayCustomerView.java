package capstone.Views;

import capstone.Extras.Helper;

public final class TellerDisplayCustomerView extends View{
    public void print() {
        clearScreen("Teller Account Display Page");

        while(true){
            Helper.customer_search();// search the target customer from teller side

            // display password & username & balance & access level

            int continue_checker = Helper.continue_checker();
            if (continue_checker == 1){break;}else{continue;}
        }
    }
}
