package capstone.Objects;

import java.io.Serializable;
import java.util.Base64;

import capstone.Enums.AccessLevel;
import capstone.Extras.Helper;
import capstone.Views.CustomerChangePassword;
import capstone.Views.CustomerDepositView;
import capstone.Views.CustomerDisplayView;
import capstone.Views.CustomerMainView;
import capstone.Views.CustomerWithdrawView;
import capstone.Views.View;

/**
 * The superclass of all User classes in the application.
 * @author Jia Hui, Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public class User implements Serializable {

    /**
     * The unique ID of the class for Serialisation.
     */
    private static final long serialVersionUID = 29L;

    /**
     * The username associated with each network User.
     */
    private String username;

    /**
     * The {@link AccessLevel} of each User.
     */
    private AccessLevel accessLevel;

    /**
     * The encrypted password of each user.
     */
    private byte[] password;

    /**
     * Initialiser for the User Class with a new username, password and {@link AccessLevel}.
     * @param username The username of the User.
     * @param password The password of the User.
     * @param accessLevel The AccessLevel of the user.
     */
    protected User(String username, String password, AccessLevel accessLevel) {
        this.username = username;
        this.password = encryptPassword(password);
        this.accessLevel = accessLevel;
    }

    /**
     * Initialiser for the User Class with a default password and a new username and {@link AccessLevel}.
     * @param username The username of the User.
     * @param accessLevel The AccessLevel of the user.
     */
    protected User(String username, AccessLevel accessLevel) {
        this.username = username;
        this.accessLevel = accessLevel;
        this.password = encryptPassword("javaInsecureP@ssword");
    }

    /**
     * Changes the password for a User.
     * @return {@code true} if password is successfully changed.
     */
    public boolean changePassword() {
        // TODO: need to tell from 3 access types
        CustomerChangePassword customerChangePassword_view = new CustomerChangePassword();
        customerChangePassword_view.print();

        while (true) {
            System.out.print("Enter current password or Q to quit: ");
            String oldPassword = Helper.getPasswordInput();
            if (oldPassword.equals("Q")) {
                break;
            }
            if (this.checkPassword(oldPassword)) {
                System.out.print("Enter new password: ");
                String newPassword1 = Helper.getPasswordInput();
                System.out.print("Enter the new password again: ");
                String newPassword2 = Helper.getPasswordInput();
                if (newPassword1.equals(newPassword2)){
                    this.password = encryptPassword(newPassword1);
                    // TODO: Call the DB method that writes the password to the DB
                    System.out.println("Password updated successfully.");
                    return true;
                } else {
                    System.out.println("The passwords you entered do not match. Please try again.");
                    Helper.pause();
                }
            } else {
                System.out.println("Invalid password!");
                Helper.pause();
            }
        }
        return false;
    }

    /**
     * Encrypts the raw password string using the Base64 encoding scheme as specified in RFC 4648 and RFC 2045.
     * @param rawString the raw password string
     * @return the encoded bytes
     */
    private byte[] encryptPassword(String rawString){
        byte[] encodedBytes = Base64.getEncoder().encode(rawString.getBytes());
        return encodedBytes;
    }

    /**
     * Decrypts the password stored as byte data, using the Base 64 encoding scheme as specified in RFC 4648 and RFC 2045.
     * @return the raw password string
     */
    private String decryptPassword(){
        byte[] bytes = Base64.getDecoder().decode(this.password);
        return new String(bytes);
    }

    /**
     * Checks if the password entered by the User is the correct password.
     * @param input the password entered by the User.
     * @return {@code true} if the password is correct.
     */
    public boolean checkPassword(String input) {
        String rawPassword = decryptPassword();
        return input.equals(input);
    }

    /**
     * Changes the {@link AccessLevel} for the User.
     * @param accessLevel The new AccessLevel
     * @return {@code true} once AccessLevel has been changed.
     */
    protected boolean changeAccessLevel(AccessLevel accessLevel) {
        
        if (this.accessLevel == accessLevel) {
            System.out.println("User is already a " + accessLevel.name() + "!");
            return false;
        } else {
            this.accessLevel = accessLevel;
            return true;
        }
    }

    /**
     * Getter method that retrieves the Username of the User.
     * @return The username as a String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method that retireves the AccessLevel of the User.
     * @return the AccessLevel of the User.
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public static void Deposit(){
        // TODO: need to tell from 3 access types
        CustomerDepositView customerDepositView_view = new CustomerDepositView();
        customerDepositView_view.print();

        View.clearScreen("Deposit Page");

        System.out.println("Enter the amount you want to deposit");
        Double deposit_amount = Helper.sc.nextDouble();// can be 89.99

        // TODO: check is a number or not

        System.out.println("deposit amount:"+deposit_amount);
        
        Helper.pause();

        CustomerMainView view = new CustomerMainView();
        view.print();

        // TODO: 
        // SQL related lines/database lines here, update
        // 
    }

    public static void Withdraw(){
        CustomerWithdrawView customerWithdrawView_view = new CustomerWithdrawView();
        customerWithdrawView_view.print();

        System.out.println("Enter the amount you want to withdraw");
        Double withdraw_amount = Helper.sc.nextDouble();// convert to int type

        System.out.println("withdraw amount:"+withdraw_amount);

        Helper.pause();

        // TODO: 
        // SQL related lines/database lines here, update
    }

    public static void Transaction(){
        CustomerDisplayView customerDisplayView_view = new CustomerDisplayView();
        customerDisplayView_view.print();

        System.out.println("Enter the account number you want to transfer to:");
        String account_receiver = Helper.readLine();// convert to int type

        // check whether account exist
        if (Database.containsUser(account_receiver)) {
            User reveiver_user = Database.getUser(account_receiver);
        }else {
            System.out.println("This payee does not exist! Please re-enter!");
            Helper.pause();
        }
        

        System.out.println("Enter the amount you want to transfer");
        int transfer_amount = Integer.parseInt(Helper.readLine());// convert to int type

        Helper.pause();

        // TODO: 
        // SQL related lines/database lines here, update both for 2 accounts in database, 
    }

    public static void Display(){
        View.clearScreen("Display Page");

        Helper.pause();

        // TODO: 
        // SQL related lines/database lines here, update
    }
}
