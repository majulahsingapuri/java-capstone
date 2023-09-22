package capstone.Objects;

import capstone.Enums.AccessLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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

  /** The Connection to the Postgresql */
  private static Connection conn;

  /**
   * The initialiser for the Database class. Reads Files on startup, resets key Admin Users if they
   * have been deleted and retores all Application Settings from last close.
   */
  public Database() {
    String jdbcUrl = "jdbc:postgresql://localhost:5432/capstone2";
    String username = "capstone2";
    String password = "password";
    try {
      conn = DriverManager.getConnection(jdbcUrl, username, password);
    } catch (SQLException se) {
      se.printStackTrace();
    } finally {
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException se) {
          se.printStackTrace();
        }
    }
  }

  /**
   * Checks if the database contains the existing username
   *
   * @param username username in database
   * @return boolean indicating if the database contains a user that is specified
   */
  public static boolean containsUser(String username) {
    return true;
  }

  /**
   * Retrives the username from the database
   *
   * @param username username in database
   * @return a user's object
   */
  public static User getUser(String username) {
    return new User(0, username, "password", "firstname", "lastname", AccessLevel.NONE);
  }

  /**
   * Creates a new Customer for the Database
   *
   * @param username new username
   * @param password new password
   * @return a new customer
   */
  public static Customer createCustomer(String username, String password) {
    return new Customer(
        1,
        "username",
        "password",
        1,
        "tom",
        "hanks",
        "s234324j",
        "tom@hanks.com",
        new Date(),
        "1 bitch road",
        "0198011");
  }

  /**
   * Creates a new Admin for the Database
   *
   * @param username new username
   * @param password new password
   * @return a new admin
   */
  public static Admin createAdmin(String username, String password) {
    return new Admin(0, username, password, 0, "admin", "lastname");
  }

  /**
   * Creates a new Teller for the Database
   *
   * @param username new username
   * @param password new password
   * @return a new teller
   */
  public static Teller createTeller(String username, String password) {
    return new Teller(0, username, password, 0, "teller", "lastname");
  }

  /**
   * Updates the password for the current user in the Database
   *
   * @param user the user to be updated
   * @param password the new user's password
   * @return true
   */
  public static boolean updatePassword(User user, String password) {
    return true;
  }

  /**
   * updates user details
   *
   * @param user the user to be updated
   * @param firstName the User's new first name
   * @param lastName the User's new last name
   * @return true
   */
  public static boolean updateUser(User user, String firstName, String lastName) {
    return true;
  }

  /**
   * Creates an Account inthe Account Table
   *
   * @return null if the account could not be created else an {@link Account} objct
   */
  public static Optional<Account> createAccount() {
    return null;
  }

  /**
   * Retrieves the records in the CustomerAccount table from the SQL DATABASE
   *
   * @param customer the customer whose account to be fetched
   * @return an Arraylist of account objects
   */
  public static ArrayList<Account> getCustomerAccounts(Customer customer) {
    // query the database for accounts for this user
    /**
     * select * from acccounts a join customer_accounts ca on a.id = ca.acc_no join customer c on
     * ca.cust_id = c.id where c.username = ?
     */
    return new ArrayList<Account>();
  }

  /**
   * Updates the new balance for the account
   *
   * @param account the account to be updated
   * @param newBalance the new balance to be updated
   * @return true
   */
  public static boolean updateBalance(Account account, float newBalance) {
    return true;
  }

  /**
   * creates a Transaction in the transaction table
   *
   * @param customer the customer that created the transaction
   * @param account the to which the transaction is to be recorded
   * @return the created Transaction, if any
   */
  public static Optional<Transaction> createTransaction(Customer customer, Account account) {
    return null;
  }
}
