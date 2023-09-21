package capstone;

import capstone.Objects.Database;
import capstone.Views.LoginView;

public class Main {
    /**
     * Main function that is the starting point of the application. Initialises a new {@link Database} and loads in the first view.
     * @param args Arguments passsed to the App.
     */
    public static void main( String[] args ) {
        new Database();
        LoginView view = new LoginView();
        view.print();
    }
}