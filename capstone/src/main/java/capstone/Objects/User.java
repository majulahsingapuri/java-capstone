package capstone.Objects;

import capstone.Enums.AccessLevel;
import capstone.Extras.Helper;
import org.mindrot.jbcrypt.BCrypt;

/**
 * The superclass of all User classes in the application.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public class User {

  /** The User ID associated with each network User. */
  private int id;

  /** The username associated with each network User. */
  private String username;

  /** The first name associated with each network User. */
  private String firstName;

  /** The last name associated with each network User. */
  private String lastName;

  /** The {@link AccessLevel} of each User. */
  private AccessLevel accessLevel;

  /** The encrypted password of each user. */
  private String password;

  /**
   * Initialiser for the User Class with a new username, password and {@link AccessLevel}.
   *
   * @param id the id of the User
   * @param username The username of the User.
   * @param password The password of the User.
   * @param firstName The User's first name.
   * @param lastName The User's last name.
   * @param accessLevel The AccessLevel of the user.
   */
  protected User(
      int id,
      String username,
      String password,
      String firstName,
      String lastName,
      AccessLevel accessLevel) {
    this.id = id;
    this.username = username;
    this.password = encryptPassword(password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.accessLevel = accessLevel;
  }

  /**
   * Changes the password for a User.
   *
   * @return {@code true} if password is successfully changed.
   */
  public boolean changePassword() {

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
        if (newPassword1.equals(newPassword2)) {
          String password = encryptPassword(newPassword1);
          boolean result = Database.updatePassword(this, password);
          if (result) {
            this.password = password;
          }
          return result;
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
   * Encrypts the raw password string using the BCrypt tool
   *
   * @param rawString the raw password string
   * @return the encoded bytes
   */
  private String encryptPassword(String rawString) {
    return BCrypt.hashpw(rawString, BCrypt.gensalt());
  }

  /**
   * Checks if the password entered by the User is the correct password.
   *
   * @param input the password entered by the User.
   * @return {@code true} if the password is correct.
   */
  public boolean checkPassword(String input) {
    return BCrypt.checkpw(input, this.password);
  }

  /**
   * Getter method that retrieves the Username of the User.
   *
   * @return The username as a String.
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Getter method that retireves the AccessLevel of the User.
   *
   * @return the AccessLevel of the User.
   */
  public AccessLevel getAccessLevel() {
    return this.accessLevel;
  }

  /**
   * Getter method that retrieves the User's first name
   *
   * @return the first name as String
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Getter method that retrieves the User's last name
   *
   * @return the last name as String
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Setter method that sets the User's first name
   *
   * @param firstName the new first name of the user.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Setter method that sets the User's last name
   *
   * @param lastName the new last name of the user
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Getter method that retrieves the User's id
   *
   * @return the User's id
   */
  public int getID() {
    return this.id;
  }
}
