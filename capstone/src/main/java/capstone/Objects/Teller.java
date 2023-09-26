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
public final class Teller extends User {

  /** the id of the Teller */
  private int tellerID;

  /**
   * Initialiser for Class. Creates new object with new username and password, and Teller
   * AccessLevel.
   *
   * @param username The Teller's username.
   * @param password The Teller's password.
   * @param id The Teller's user id
   * @param firstName The Teller's first name
   * @param lastName The Teller's last name
   * @param tellerID The Teller id
   */
  public Teller(
      int id, String username, String password, String firstName, String lastName, int tellerID) {
    super(id, username, password, firstName, lastName, AccessLevel.TELLER);
    this.tellerID = tellerID;
  }

  /**
   * Getter function to get the Teller's id
   *
   * @return int representing the teller id
   */
  public int getTellerID() {
    return this.tellerID;
  }
}
