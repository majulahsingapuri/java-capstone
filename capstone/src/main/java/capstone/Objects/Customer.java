package capstone.Objects;

import capstone.Enums.AccessLevel;

/**
 * The User class that defines the actions of a System Administator. Extends {@link User} with
 * Access Level {@link AccessLevel#ADMIN}.
 *
 * @author Jia Hui
 * @version 1.0
 * @since 2020-11-1
 */
public final class Customer extends User {

  /** Unique identifier for Serialisation. */
  private static final long serialVersionUID = 26L;

  /**
   * Initialiser for Class. Creates new object with new username, default password and Customer
   * AccessLevel.
   *
   * @param username The User's username.
   */
  public Customer(String username) {
    super(username, AccessLevel.CUSTOMER);
  }

  /**
   * Initialiser for Class. Creates new object with new username and password, and Customer
   * AccessLevel.
   *
   * @param username The User's username.
   * @param password The User's password.
   */
  public Customer(String username, String password) {
    super(username, password, AccessLevel.CUSTOMER);
  }
}
