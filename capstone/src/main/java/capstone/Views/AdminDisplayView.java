package capstone.Views;

import capstone.Extras.Helper;

public final class AdminDisplayView extends View {
    public void print() {
        clearScreen("Admin Account Display Page");

        while(true){

            // display password & username & balance & access level

            int continue_checker = Helper.continue_checker();
            if (continue_checker == 1){break;}else{continue;}
        }
    }
}
