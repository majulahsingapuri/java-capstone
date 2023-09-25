package capstone.Views;

import capstone.Extras.Helper;

public final class TellerDisplayView extends View{
    public void print() {
        clearScreen("Teller Display Page");

        while(true){
            Helper.customer_search();// search the target customer from teller side

            int continue_checker = Helper.continue_checker();
            if (continue_checker == 1){break;}else{continue;}
        }
    }
}
