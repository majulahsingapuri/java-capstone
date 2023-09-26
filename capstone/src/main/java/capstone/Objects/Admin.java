package capstone.Objects;

import capstone.Enums.AccessLevel;

/**
 * The User class that defines the actions of a System Administator. Extends {@link User} with
 * Access Level {@link AccessLevel#ADMIN}.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class Admin extends User {

  /** Admin's id */
  private int adminID;

  /**
   * Initialiser for Class. Creates new object with new username and password, and Admin
   * AccessLevel.
   *
   * @param username The User's username.
   * @param password The User's password.
   * @param id The User's id
   * @param firstName the User's first name
   * @param lastName the User's last name
   * @param adminID the User's Admin id
   */
  public Admin(
      int id, String username, String password, String firstName, String lastName, int adminID) {
    super(id, username, password, firstName, lastName, AccessLevel.ADMIN);
    this.adminID = adminID;
  }

  /**
   * Getter function for the admin's id
   *
   * @return int representing the admin's id
   */
  public int getAdminID() {
    return this.adminID;
  }
}
