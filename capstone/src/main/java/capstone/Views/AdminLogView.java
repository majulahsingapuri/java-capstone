package capstone.Views;

import capstone.Extras.ConsoleColours;
import capstone.Extras.Helper;
import capstone.Objects.Database;
import capstone.Objects.Log;
import java.util.ArrayList;

public final class AdminLogView extends View {
  public void print() {

    while (true) {
      clearScreen("Admin Log View");
      ArrayList<Log> log_list = Database.getLoggingRecords();
      System.out.println("Logs " + "\uD83D\uDCDC");

      if (log_list.size() > 0) {
        Helper.printLine(80);
        System.out.println("Log ID | User ID | User Name | Date | Task | Error");
        for (Log log : log_list) {
          System.out.println(
              log.getID()
                  + " | "
                  + log.getUser_ID()
                  + " | "
                  + log.getDate()
                  + " | "
                  + log.getUser_Name()
                  + " | "
                  + log.getTask_Name()
                  + " | "
                  + log.getError_Msg());
        }
      } else {
        Helper.printLine(80);
        System.out.println(
            ConsoleColours.YELLOW + "No Log history for now:" + ConsoleColours.RESET);
      }

      int continue_checker = Helper.continue_checker();
      if (continue_checker == 1) {
        break;
      } else {
        continue;
      }
    }

    // display logs

  }
}
