package capstone.Views;

import capstone.Extras.Helper;

public final class AdminUpdateView extends View {
    public void print(){
        clearScreen("Admin Update User Page");

        while(true){
            Helper.customer_search();// customer_user is the target 

            int continue_checker = Helper.continue_checker();
            if (continue_checker == 1){break;}else{continue;}
        }
    }
}
