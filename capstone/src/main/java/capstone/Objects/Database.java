package capstone.Objects;

import capstone.Enums.AccessLevel;
import capstone.Enums.AccountType;
import capstone.Enums.TransactionType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.joda.time.DateTime;

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
      System.out.println("DB connected");
    } catch (SQLException se) {
      System.out.println("DB not connected");
      se.printStackTrace();
    }
  }

  public static void close() {
    if (conn != null) {
      try {
        System.out.println("Closing DB connection");
        conn.close();
        System.out.println("DB connection closed");
      } catch (SQLException se) {
        System.out.println("DB connection failed to close");
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
    boolean result = false;
    try {
      PreparedStatement st =
          conn.prepareStatement(
              "select username from " + AccessLevel.NONE.db + " where username = ?");
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString(1).equals(username)) {
          result = true;
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * Retrives the User object from the database. This is the generic User class and not the specific
   * Admin, Teller or Customer class
   *
   * @param username username in database
   * @return a user's object
   */
  public static Optional<User> getUser(String username) {
    Optional<User> user = Optional.empty();
    try {
      PreparedStatement st =
          conn.prepareStatement(
              "select id, username, password, first_name, last_name from "
                  + AccessLevel.NONE.db
                  + " where username = ?");
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString(2).equals(username)) {
          user =
              Optional.of(
                  new User(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getString(4),
                      rs.getString(5),
                      AccessLevel.NONE));
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return user;
  }

  /**
   * Retrives the Customer object from the database.
   *
   * @param username username in database
   * @return a Customer object
   */
  public static Optional<Customer> getCustomer(String username) {
    Optional<Customer> user = Optional.empty();
    try {
      PreparedStatement st =
          conn.prepareStatement(
              "select a.id, a.username, a.password, a.first_name, a.last_name, b.customer_id,"
                  + " b.nric, b.email, b.date_of_birth, b.address, b.phone_no\n"
                  + "from "
                  + AccessLevel.NONE.db
                  + " as a join "
                  + AccessLevel.CUSTOMER.db
                  + " as b on a.id = b.user_ptr_id\n"
                  + "where a.username = ?");
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString(2).equals(username)) {
          user =
              Optional.of(
                  new Customer(
                      rs.getInt(1), // id
                      rs.getString(2), // username
                      rs.getString(3), // password
                      rs.getString(4), // firstName
                      rs.getString(5), // lastName
                      rs.getInt(6), // customerID
                      rs.getString(7), // nric
                      rs.getString(8), // email
                      rs.getDate(9), // DOB
                      rs.getString(10), // address
                      rs.getString(11) // phoneNo
                      ));
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return user;
  }

  /**
   * Retrives the Admin object from the database.
   *
   * @param username username in database
   * @return a Admin object
   */
  public static Optional<Admin> getAdmin(String username) {
    Optional<Admin> user = Optional.empty();
    try {
      PreparedStatement st =
          conn.prepareStatement(
              "select a.id, a.username, a.password, a.first_name, a.last_name, b.admin_id\n"
                  + "from "
                  + AccessLevel.NONE.db
                  + " as a join "
                  + AccessLevel.ADMIN.db
                  + " as b on a.id = b.user_ptr_id\n"
                  + "where a.username = ?");
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString(2).equals(username)) {
          user =
              Optional.of(
                  new Admin(
                      rs.getInt(1), // id
                      rs.getString(2), // username
                      rs.getString(3), // password
                      rs.getString(4), // firstName
                      rs.getString(5), // lastName
                      rs.getInt(6) // adminID
                      ));
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
    return user;
  }

  /**
   * Retrives the Teller object from the database.
   *
   * @param username username in database
   * @return a Admin object
   */
  public static Optional<Teller> getTeller(String username) {
    Optional<Teller> user = Optional.empty();
    try {
      PreparedStatement st =
          conn.prepareStatement(
              "select a.id, a.username, a.password, a.first_name, a.last_name, b.teller_id\n"
                  + "from "
                  + AccessLevel.NONE.db
                  + " as a join "
                  + AccessLevel.TELLER.db
                  + " as b on a.id = b.user_ptr_id\n"
                  + "where a.username = ?");
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString(2).equals(username)) {
          user =
              Optional.of(
                  new Teller(
                      rs.getInt(1), // id
                      rs.getString(2), // username
                      rs.getString(3), // password
                      rs.getString(4), // firstName
                      rs.getString(5), // lastName
                      rs.getInt(6) // tellerID
                      ));
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
    return user;
  }

  /**
   * Creates a new Customer in the database
   *
   * @param username The username of the customer
   * @param password The customer's password
   * @param firstName The customer's first name
   * @param lastName The customer's last name
   * @param nric The customer's nric
   * @param email The customer's email
   * @param dateOfBirth The customer's date of birth
   * @param address The customer's address
   * @param phoneNumber The customer's phone number
   * @return An Optional customer object. Unwrap this to get the underlying object.
   */
  public static Optional<Customer> createCustomer(
      String username,
      String password,
      String firstName,
      String lastName,
      String nric,
      String email,
      Date dateOfBirth,
      String address,
      String phoneNumber) {
    Optional<Customer> customer = Optional.empty();
    try {
      // insert a new user into the user table
      PreparedStatement insertstmt =
          conn.prepareStatement(
              "INSERT INTO "
                  + AccessLevel.NONE.db
                  + "(username, password, first_name, last_name) VALUES (?, ?, ?, ?)");
      insertstmt.setString(1, username);
      insertstmt.setString(2, password);
      insertstmt.setString(3, firstName);
      insertstmt.setString(4, lastName);
      insertstmt.executeQuery();

      // fetch the user id from the user table
      PreparedStatement selectstmt =
          conn.prepareStatement("SELECT id FROM " + AccessLevel.NONE.db + " WHERE username = ?");
      selectstmt.setString(1, username);
      ResultSet rs = selectstmt.executeQuery();
      int user_id = 0;
      while (rs.next()) {
        user_id = rs.getInt(1);
      }

      if (user_id != 0) {

        // using the user id, insert into the customer table
        PreparedStatement insertCustomer =
            conn.prepareStatement(
                "INSERT INTO "
                    + AccessLevel.CUSTOMER.db
                    + "(user_ptr_id, nric, email, date_of_birth, address, phone_no) VALUES (?, ?,"
                    + " ?, ?, ?, ?)");
        insertCustomer.setInt(1, user_id);
        insertCustomer.setString(2, nric);
        insertCustomer.setString(3, email);
        insertCustomer.setDate(4, new java.sql.Date(dateOfBirth.getTime()));
        insertCustomer.setString(5, address);
        insertCustomer.setString(6, phoneNumber);
        insertCustomer.executeQuery();

        // select the new customer user created
        PreparedStatement stmt =
            conn.prepareStatement(
                "select a.id, a.username, a.password, a.first_name, a.last_name, b.customer_id,"
                    + " b.nric, b.email, b.date_of_birth, b.address, b.phone_no\n"
                    + "from "
                    + AccessLevel.NONE.db
                    + " as a join "
                    + AccessLevel.CUSTOMER.db
                    + " as b on a.id = b.user_ptr_id\n"
                    + "where a.username = ?");
        stmt.setString(1, username);
        ResultSet rs2 = stmt.executeQuery();
        while (rs2.next()) {
          if (rs2.getString(2).equals(username)) {
            customer =
                Optional.of(
                    new Customer(
                        rs.getInt(1), // id
                        rs.getString(2), // username
                        rs.getString(3), // password
                        rs.getString(4), // firstName
                        rs.getString(5), // lastName
                        rs.getInt(6), // customerID
                        rs.getString(7), // nric
                        rs.getString(8), // email
                        rs.getDate(9), // DOB
                        rs.getString(10), // address
                        rs.getString(11) // phoneNo
                        ));
          }
        }
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return customer;
  }

  /**
   * Creates a new Admin for the Database
   *
   * @param username new username
   * @param password new password
   * @return a new admin
   */
  public static Optional<Admin> createAdmin(
      String username, String password, String firstName, String lastName) {
    Optional<Admin> admin = Optional.empty();
    try {
      // insert a new user into the user table
      PreparedStatement insertstmt =
          conn.prepareStatement(
              "INSERT INTO "
                  + AccessLevel.NONE.db
                  + "(username, password, first_name, last_name) VALUES (?, ?, ?, ?)");
      insertstmt.setString(1, username);
      insertstmt.setString(2, password);
      insertstmt.setString(3, firstName);
      insertstmt.setString(4, lastName);
      insertstmt.executeQuery();

      // fetch the user id from the user table
      PreparedStatement selectstmt =
          conn.prepareStatement("SELECT id FROM " + AccessLevel.NONE.db + " WHERE username = ?");
      selectstmt.setString(1, username);
      ResultSet rs = selectstmt.executeQuery();
      int user_id = 0;
      while (rs.next()) {
        user_id = rs.getInt(1);
      }

      if (user_id != 0) {

        // using the user id, insert into the admin table
        PreparedStatement insertAdmin =
            conn.prepareStatement(
                "INSERT INTO " + AccessLevel.ADMIN.db + "(user_ptr_id) VALUES (?)");
        insertAdmin.setInt(1, user_id);
        insertAdmin.executeQuery();

        // select the new admin user created
        PreparedStatement stmt =
            conn.prepareStatement(
                "SELECT a.id, a.username, a.password, a.first_name, a.last_name, b.admin_id FROM "
                    + AccessLevel.NONE.db
                    + " AS a JOIN "
                    + AccessLevel.ADMIN.db
                    + " AS b ON a.id = b.user_ptr_id"
                    + " WHERE username = ?");
        stmt.setString(1, username);
        ResultSet rs2 = stmt.executeQuery();
        while (rs2.next()) {
          if (rs2.getString(2).equals(username)) {
            admin =
                Optional.of(
                    new Admin(
                        rs2.getInt(1), // id
                        rs2.getString(2), // username
                        rs2.getString(3), // password
                        rs2.getString(4), // firstName
                        rs2.getString(5), // lastName
                        rs2.getInt(6) // adminID
                        ));
          }
        }
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return admin;
  }

  /**
   * Creates a new Teller for the Database
   *
   * @param username new username
   * @param password new password
   * @return a new teller
   */
  public static Optional<Teller> createTeller(
      String username, String password, String firstName, String lastName) {
    Optional<Teller> teller = Optional.empty();
    try {
      /** Insert a new User */
      PreparedStatement insertUserStatement =
          conn.prepareStatement(
              "INSERT INTO "
                  + AccessLevel.NONE.db
                  + "(username, password, firstName, lastName) VALUES (?, ?, ?, ?) ");
      insertUserStatement.setString(1, username);
      insertUserStatement.setString(2, password);
      insertUserStatement.setString(3, firstName);
      insertUserStatement.setString(4, lastName);
      insertUserStatement.executeUpdate();
      /** Selects the newly created User */
      PreparedStatement selectStatement =
          conn.prepareStatement("SELECT id FROM " + AccessLevel.NONE.db + " WHERE username = ?");
      selectStatement.setString(1, username);
      ResultSet rs = selectStatement.executeQuery();
      int user_id = 0;
      while (rs.next()) {
        user_id = rs.getInt(1);
      }

      if (user_id != 0) {

        /** Inserts the newly created Teller */
        PreparedStatement insertTellerStatement =
            conn.prepareStatement(
                "INSERT INTO " + AccessLevel.TELLER.db + "(user_ptr_id) VALUES (?)");
        insertTellerStatement.setInt(1, user_id);
        insertTellerStatement.executeUpdate();

        /** Selects the newly created Teller */
        PreparedStatement selectTellerStatement =
            conn.prepareStatement(
                "SELECT a.id, a.username, a.password, a.first_name, a.last_name, b.teller_id FROM "
                    + AccessLevel.NONE.db
                    + " AS a JOIN "
                    + AccessLevel.TELLER.db
                    + " AS b ON a.id = b.user_ptr_id"
                    + " WHERE username = ?");
        selectTellerStatement.setString(1, username);
        ResultSet rs2 = selectTellerStatement.executeQuery();

        while (rs2.next()) {
          if (rs2.getString(2).equals(username)) {
            teller =
                Optional.of(
                    new Teller(
                        rs2.getInt(1), // id
                        rs2.getString(2), // username
                        rs2.getString(3), // password
                        rs2.getString(4), // firstName
                        rs2.getString(5), // lastName
                        rs2.getInt(6) // tellerId
                        ));
          }
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return teller;
  }

  /**
   * Updates the password for the current user in the Database
   *
   * @param user the user to be updated
   * @param password the new user's password
   * @return true
   */
  public static boolean updatePassword(User user, byte[] password) {
    /** Updating the given password in the database */
    try {
      PreparedStatement updateUserPassword =
          conn.prepareStatement(
              "UPDATE a.password FROM" + AccessLevel.NONE.db + " WHERE username = ? ");
      updateUserPassword.setString(1, user.getUsername());
      updateUserPassword.executeQuery();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return false;
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
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE "
                  + AccessLevel.NONE.db
                  + " SET first_name = ?,"
                  + " SET last_name = ?"
                  + " WHERE username = ?");
      upstmt.setString(1, firstName);
      upstmt.setString(2, lastName);
      upstmt.setString(3, user.getUsername());
      upstmt.executeQuery();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * updates user details
   *
   * @param user the user to be updated
   * @param firstName the User's new first name
   * @return true
   */
  public static boolean updateUserFirstName(User user, String firstName) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.NONE.db + " SET first_name = ?," + " WHERE username = ?");
      upstmt.setString(1, firstName);
      upstmt.setString(2, user.getUsername());
      upstmt.executeQuery();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * updates user details
   *
   * @param user the user to be updated
   * @param lastName the User's new last name
   * @return true
   */
  public static boolean updateUserLastName(User user, String lastName) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.NONE.db + " SET last_name = ?" + " WHERE username = ?");
      upstmt.setString(1, lastName);
      upstmt.setString(2, user.getUsername());
      upstmt.executeQuery();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * Creates an Account in the Account Table
   *
   * @return null if the account could not be created else an {@link Account} objct
   */
  public static Optional<Account> createAccount(String username, AccountType accountType) {
    Optional<Account> account = Optional.empty();
    try {
      // create a new account and get account id
      PreparedStatement insertAccount =
          conn.prepareStatement(
              "INSERT INTO migrations_account(balance, account_type) VALUES (?, ?) RETURNING id");
      insertAccount.setInt(1, 0);
      insertAccount.setString(2, accountType.type);
      ResultSet rs = insertAccount.executeQuery();
      int account_no_id = 0;

      while (rs.next()) {
        account_no_id = rs.getInt(1); // get account_no_id
      }

      // fetch customer account and get customer id
      PreparedStatement selectCust =
          conn.prepareStatement(
              "SELECT c.customer_id FROM "
                  + AccessLevel.CUSTOMER.db
                  + " AS c JOIN "
                  + AccessLevel.NONE.db
                  + " AS u ON c.user_ptr_id = c.id WHERE u.username = ?");
      selectCust.setString(1, username);
      ResultSet rs2 = selectCust.executeQuery();
      int customer_id = 0;

      while (rs2.next()) {
        customer_id = rs2.getInt(1); // get customer_id
      }

      if (account_no_id != 0 && customer_id != 0) {
        // create a customer_account relation between customer and account
        PreparedStatement insertCustAcc =
            conn.prepareStatement(
                "INSERT INTO migrations_customeraccount(account_no_id, customer_id_id) "
                    + "VALUES(?, ?)");
        insertCustAcc.setInt(1, account_no_id);
        insertCustAcc.setInt(2, customer_id);
        insertCustAcc.executeQuery();

        // select the new account created
        PreparedStatement stmt =
            conn.prepareStatement(
                "SELECT id, balance, account_type FROM migrations_account as a WHERE a.id = ?");
        stmt.setInt(1, account_no_id);
        ResultSet rs3 = stmt.executeQuery();

        while (rs3.next()) {
          if (rs3.getInt(1) == account_no_id) {
            account = Optional.of(new Account(rs3.getInt(1), rs3.getDouble(2), accountType));
          }
        }
      }
      return account;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
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
   * @param customer The customer that created the transaction
   * @param account The to which the transaction is to be recorded
   * @param transactionType A credit or debit transaction
   * @param amount The amount of the transaction
   * @return an Optional Transaction. Unwrap this object to get the underlying Transaction.
   */
  public static Optional<Transaction> createTransaction(
      Customer customer, Account account, TransactionType transactionType, double amount) {
    Optional<Transaction> transaction = Optional.empty();
    try {
      UUID uuid = UUID.randomUUID();
      DateTime timestamp = DateTime.now();

      // Insert values into database
      PreparedStatement insertTransactionStatement =
          conn.prepareStatement(
              "insert into migrations_transaction (transaction_ref, transaction_type, date, amount,"
                  + " account_no_id, customer_id_id)\n"
                  + "values (?, ?, ?, ?, ?, ?)");
      insertTransactionStatement.setString(1, uuid.toString());
      insertTransactionStatement.setString(2, transactionType.type);
      insertTransactionStatement.setDate(
          3,
          new java.sql.Date(
              timestamp.toDate().getTime())); // TODO Check for probable loss of time part
      insertTransactionStatement.setDouble(4, amount);
      insertTransactionStatement.setInt(5, account.getID());
      insertTransactionStatement.setInt(6, customer.getID());
      insertTransactionStatement.executeQuery();

      // Get updated values from database
      PreparedStatement getTransactionStatement =
          conn.prepareStatement(
              "select id, transaction_ref, transaction_type, date, amount, account_no_id,"
                  + " customer_id_id\n"
                  + "from migrations_transaction\n"
                  + "where transaction_ref = ?");
      getTransactionStatement.setString(1, uuid.toString());
      ResultSet rs = getTransactionStatement.executeQuery();
      while (rs.next()) {
        transaction =
            Optional.of(
                new Transaction(
                    rs.getInt(1),
                    rs.getString(2),
                    transactionType,
                    timestamp,
                    rs.getDouble(5),
                    rs.getInt(6),
                    rs.getInt(7)));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return transaction;
  }
}
