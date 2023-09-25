package capstone.Objects;

import capstone.Enums.AccessLevel;

/**
 * The Class that interacts with the files and handles reading and writing of information.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class Database {

  /** A User object that keeps track of the currently logged in User. */
  public static User CURRENT_USER = null;

  /** The current AccessLevel of the currently logged in User. */
  public static AccessLevel CURRENT_ACCESS_LEVEL = AccessLevel.NONE;

  /**
   * The initialiser for the Database class. Reads Files on startup, resets key Admin Users if they
   * have been deleted and retores all Application Settings from last close.
   */
  public Database() {}

  /**
   * Checks if the database contains the existing username
   *
   * @param username
   * @return
   */
  public static boolean containsUser(String username) {
    // TODO: Implement the logic
    return true;
  }

  public static User getUser(String username) {
    // TODO: Implement the logic
    return new User(username, AccessLevel.NONE);
  }
}
