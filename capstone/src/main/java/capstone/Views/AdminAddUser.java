package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;

public final class AdminAddUser extends View {
    public void print(){
        clearScreen("Admin Add User Page");

        while(true){

            System.out.println("Continue? [Y/N]: ");
            String str_input = Helper.sc.nextLine();
            
            if (str_input.equals("N")){
                break;
            }else if (str_input.equals("Y")){
                continue;
            }else {
                System.out.println("Wrong Input, please input Y/N or y/n");
            }
        
        }
    }
}
