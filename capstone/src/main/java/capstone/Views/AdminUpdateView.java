package capstone.Views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import capstone.Extras.Helper;
import capstone.Objects.Admin;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Teller;
import capstone.Objects.User;

public final class AdminUpdateView extends View {
  public void print() {
    clearScreen("Admin Update User Page");
    User user;
    String input;
    Object[] ret;
    while (true) {
      
      String domain = Helper.getUserDomain();
      
      try {
        if (domain.equals("admin")) user = Helper.validateUser("admin");
        else if (domain.equals("teller")) user = Helper.validateUser("teller");
        else user = Helper.validateUser("customer"); 

      } catch (Exception e) {
        System.out.println(e);
        System.out.println("The page is to be reloaded");
        Helper.pause();
        continue;
      }
      while (true){
      //get new fields and check with user  
        if (domain.equals("customer")) ret = Helper.getUserAttributes(true, false);  
        else ret = Helper.getUserAttributes(false, false); //teller and admin

        Helper.printUserUpdateCredentials((String)ret[2], (String)ret[3],(String)ret[1], (String)ret[4],(String)ret[5],(Date)ret[6],(String)ret[7],(String)ret[8]);
        System.out.println("Please confirm the updated fields are correct.[Y/N]");
        input = Helper.readLine();
        if (input.equals("Y")) break;
      }
      //call db
      //TODO:Check if any is not updated successfully 
      Helper.UpdateDb(user, (String)ret[2],(String)ret[3],(String)ret[1],(String)ret[4],(String)ret[5],(Date)ret[6],(String)ret[7],(String)ret[8]);
      
      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }
  }
}
