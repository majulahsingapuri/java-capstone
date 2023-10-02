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
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

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

  /** Closes the connection to the DB */
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
              "SELECT user_id, customer_id FROM create_customer(?, ?, ?, ?, ?, ?, ?, ?, ?) AS"
                  + " (user_id INTEGER, customer_id INTEGER)");
      insertstmt.setString(1, username);
      insertstmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
      insertstmt.setString(3, firstName);
      insertstmt.setString(4, lastName);
      insertstmt.setString(5, nric);
      insertstmt.setString(6, email);
      insertstmt.setDate(7, new java.sql.Date(dateOfBirth.getTime()));
      insertstmt.setString(8, address);
      insertstmt.setString(9, phoneNumber);
      ResultSet rs = insertstmt.executeQuery();
      while (rs.next()) {
        customer =
            Optional.of(
                new Customer(
                    rs.getInt(1), // userID
                    username,
                    password,
                    firstName,
                    lastName,
                    rs.getInt(2), // customerID
                    nric,
                    email,
                    dateOfBirth,
                    address,
                    phoneNumber));
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return customer;
  }

  /**
   * Creates a new Admin in the Database
   *
   * @param username Admin's username
   * @param password Admin's password
   * @param firstName Admin's first name
   * @param lastName Admin's last name
   * @return
   */
  public static Optional<Admin> createAdmin(
      String username, String password, String firstName, String lastName) {
    Optional<Admin> admin = Optional.empty();
    try {
      // insert a new user into the user table
      PreparedStatement insertstmt =
          conn.prepareStatement(
              "SELECT user_id, admin_id FROM create_admin(?, ?, ?, ?) AS (user_id INTEGER, admin_id"
                  + " INTEGER)");
      insertstmt.setString(1, username);
      insertstmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
      insertstmt.setString(3, firstName);
      insertstmt.setString(4, lastName);
      ResultSet rs = insertstmt.executeQuery();
      while (rs.next()) {
        admin =
            Optional.of(
                new Admin(
                    rs.getInt(1), // id
                    username, // username
                    password, // password
                    firstName, // firstName
                    lastName, // lastName
                    rs.getInt(2) // adminID
                    ));
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
   * @param firstName new first name
   * @param lastName new last name
   * @return a new teller
   */
  public static Optional<Teller> createTeller(
      String username, String password, String firstName, String lastName) {
    Optional<Teller> teller = Optional.empty();
    try {
      /** Insert a new User */
      PreparedStatement insertUserStatement =
          conn.prepareStatement(
              "SELECT user_id, teller_id FROM create_teller(?, ?, ?, ?) AS (user_id INTEGER,"
                  + " teller_id INTEGER)");
      insertUserStatement.setString(1, username);
      insertUserStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
      insertUserStatement.setString(3, firstName);
      insertUserStatement.setString(4, lastName);
      ResultSet rs = insertUserStatement.executeQuery();
      while (rs.next()) {
        teller =
            Optional.of(
                new Teller(
                    rs.getInt(1), // id
                    username, // username
                    password, // password
                    firstName, // firstName
                    lastName, // lastName
                    rs.getInt(2) // tellerId
                    ));
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
  public static boolean updatePassword(User user, String password) {
    /** Updating the given password in the database */
    try {
      PreparedStatement updateUserPassword =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.NONE.db + " SET password = ? WHERE username = ? ");
      updateUserPassword.setString(1, password);
      updateUserPassword.setString(2, user.getUsername());
      updateUserPassword.execute();
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
              "UPDATE " + AccessLevel.NONE.db + " SET first_name = ?" + " WHERE username = ?");
      upstmt.setString(1, firstName);
      upstmt.setString(2, user.getUsername());
      upstmt.execute();
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
      upstmt.execute();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * Creates an Account in the Account Table
   *
   * @param customer The customer creating the account
   * @param accountType The type of account
   * @return null if the account could not be created else an {@link Account} objct
   */
  public static Optional<Account> createAccount(Customer customer, AccountType accountType) {
    Optional<Account> account = Optional.empty();
    try {
      // create a new account and get account id
      PreparedStatement insertAccount = conn.prepareStatement("SELECT * FROM create_account(?, ?)");
      insertAccount.setInt(1, customer.getCustomerID());
      insertAccount.setString(2, accountType.type);
      ResultSet rs = insertAccount.executeQuery();

      while (rs.next()) {
        account = Optional.of(new Account(rs.getInt(1), 0.0, accountType));
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
   * @param username allows filtering to be done on the username to connect the tables
   * @return an Arraylist of All the Transactions of the user
   */
  public static ArrayList<Account> getCustomerAccounts(String username) {
    ArrayList<Account> result = new ArrayList<Account>();

    try {
      PreparedStatement queryCustomerAccountStatement =
          conn.prepareStatement(
              "SELECT a.id, a.balance, account_type FROM "
                  + AccessLevel.CUSTOMER.db
                  + " AS c JOIN migrations_customeraccount AS ca ON c.customer_id ="
                  + " ca.customer_id_id JOIN migrations_account AS a ON a.id = ca.account_no_id"
                  + " JOIN migrations_user AS u ON c.user_ptr_id = u.id"
                  + " WHERE u.username = ? ");
      queryCustomerAccountStatement.setString(1, username);
      ResultSet rs = queryCustomerAccountStatement.executeQuery();

      while (rs.next()) {
        Account res =
            new Account(
                rs.getInt(1), // id
                rs.getDouble(2),
                AccountType.getAccountType(rs.getString(3)));
        result.add(res);
      }

    } catch (SQLException se) {
      System.out.println(se.getMessage());
    }

    return result;
  }

  /**
   * Fetch the given customer's transactions from the database
   *
   * @param customer the customer whose data to fetch
   * @return an ArrayList of Transactions
   */
  public static ArrayList<Transaction> getCustomerTransactions(Customer customer) {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    try {
      PreparedStatement queryCustomerTransactions =
          conn.prepareStatement(
              "SELECT c.id, c.transaction_ref, c.transaction_type, c.date, c.amount,"
                  + " c.account_no_id, c.customer_id_id\n"
                  + "from migrations_customer as b join migrations_transaction as c on"
                  + " b.customer_id = c.customer_id_id where b.customer_id = ?; ");
      queryCustomerTransactions.setInt(1, customer.getCustomerID());
      ResultSet rs = queryCustomerTransactions.executeQuery();

      while (rs.next()) {
        Transaction res =
            new Transaction(
                rs.getInt(1),
                rs.getString(2),
                TransactionType.getTransactionType(rs.getString(3)),
                DateTime.parse(rs.getDate(4).toString()),
                rs.getDouble(5),
                rs.getInt(6),
                rs.getInt(7));
        transactions.add(res);
      }

    } catch (SQLException se) {
      System.out.println(se.getMessage());
    }
    return transactions;
  }

  /**
   * Updates the new balance for the account
   *
   * @param account the account to be updated
   * @param newBalance the new balance to be updated
   * @return true
   */
  public static boolean updateBalance(Account account, double newBalance) {
    boolean result = false;
    try {
      PreparedStatement stmt =
          conn.prepareStatement(
              "UPDATE migrations_account AS ma " + "SET balance = ? " + "WHERE ma.id = ?");
      stmt.setDouble(1, newBalance);
      stmt.setInt(2, account.getID());
      stmt.executeQuery();
      result = true;
    } catch (SQLException se) {
      System.out.println(se.getMessage());
    }
    return result;
  }

  /**
   * Updates the new balance for the account specifically used for transfer to make it a float
   *
   * @param account the account to be updated
   * @param newBalance the new balance to be updated
   * @return true
   */
  public static boolean updateBalanceForTransfer(Account account, double newBalance) {
    boolean result = false;
    try {
      PreparedStatement stmt =
          conn.prepareStatement("UPDATE migrations_account AS ma SET balance = ? WHERE ma.id = ?");
      stmt.setDouble(1, newBalance);
      stmt.setInt(2, account.getID());

      // Use executeUpdate() for UPDATE statements
      int rowsUpdated = stmt.executeUpdate();

      if (rowsUpdated > 0) {
        result = true;
      }
    } catch (SQLException se) {
      System.out.println(se.getMessage());
    }
    return result;
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
                  + "values (?, ?, ?, ?, ?, ?) returning id");
      insertTransactionStatement.setObject(1, uuid); // uuid type can not take string
      insertTransactionStatement.setString(2, transactionType.type);
      insertTransactionStatement.setDate(
          3,
          new java.sql.Date(
              timestamp.toDate().getTime())); // TODO Check for probable loss of time part
      insertTransactionStatement.setDouble(4, amount);
      insertTransactionStatement.setInt(5, account.getID());
      insertTransactionStatement.setInt(6, customer.getCustomerID());
      ResultSet rs = insertTransactionStatement.executeQuery();
      while (rs.next()) {
        transaction =
            Optional.of(
                new Transaction(
                    rs.getInt(1),
                    uuid.toString(),
                    transactionType,
                    timestamp,
                    amount,
                    account.getID(),
                    customer.getCustomerID()));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return transaction;
  }

  /***
   * creates a log and logs the details
   *
   * @param user cast the object down to a user
   * @param task user's task, login
   * @param error the error for the task
   * @return true if successfully logged
   */
  public static boolean createLogging(User user, String task, String error) {
    boolean result = false;
    DateTime timestamp = DateTime.now();
    JSONObject jo = new JSONObject();
    jo.put("user", user.getUsername());
    jo.put("task", task);
    jo.put("error", error);
    try {
      PreparedStatement stmt =
          conn.prepareStatement(
              "INSERT INTO migrations_log(user_ptr_id, date, data) VALUES (?, ?, ?::json)");
      stmt.setInt(1, user.getID());
      stmt.setDate(
          2,
          new java.sql.Date(
              timestamp.toDate().getTime())); // TODO Check for probable loss of time part
      stmt.setString(3, jo.toString());
      stmt.executeQuery();
      result = true;
    } catch (SQLException se) {
      System.out.println(se.getMessage());
    }
    return result;
  }

  /**
   * allows the teller and admin to change the customer's nric
   *
   * @param customer customer to update
   * @param nric new nric
   * @return true if nric is successfully updated
   */
  public static boolean updateCustomerNRIC(Customer customer, String nric) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.CUSTOMER.db + " SET nric = ?" + " WHERE customer_id = ?");
      upstmt.setString(1, nric);
      upstmt.setInt(2, customer.getCustomerID());
      upstmt.execute();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * allows the teller and admin to change the customer's email
   *
   * @param customer customer to update
   * @param email new email
   * @return true if email is successfully updated
   */
  public static boolean updateCustomerEmail(Customer customer, String email) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.CUSTOMER.db + " SET email = ?" + " WHERE customer_id = ?");
      upstmt.setString(1, email);
      upstmt.setInt(2, customer.getCustomerID());
      upstmt.execute();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * allows the teller and admin to change the customer's date of birth
   *
   * @param customer customer to update
   * @param dob new date of birth
   * @return true if date of birth is successfully updated
   */
  public static boolean updateCustomerDob(Customer customer, Date dob) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE "
                  + AccessLevel.CUSTOMER.db
                  + " SET date_of_birth = ?"
                  + " WHERE customer_id = ?");
      upstmt.setDate(1, new java.sql.Date(dob.getTime()));
      upstmt.setInt(2, customer.getCustomerID());
      upstmt.execute();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * allows the teller and admin to change the customer's address
   *
   * @param customer customer to update
   * @param address new address
   * @return true if address is successfully updated
   */
  public static boolean updateCustomerAddress(Customer customer, String address) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.CUSTOMER.db + " SET address = ?" + " WHERE customer_id = ?");
      upstmt.setString(1, address);
      upstmt.setInt(2, customer.getCustomerID());
      upstmt.execute();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * allows the teller and admin to change the customer's phone number
   *
   * @param customer customer to update
   * @param phone_number new phone number
   * @return true if phone number is successfully updated
   */
  public static boolean updateCustomerPhoneNumber(Customer customer, String phone_number) {
    boolean result = false;
    try {
      PreparedStatement upstmt =
          conn.prepareStatement(
              "UPDATE " + AccessLevel.CUSTOMER.db + " SET phone_no = ?" + " WHERE customer_id = ?");
      upstmt.setString(1, phone_number);
      upstmt.setInt(2, customer.getCustomerID());
      upstmt.execute();
      result = true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public static ArrayList<Log> getLoggingRecords() {
    ArrayList<Log> result = new ArrayList<Log>();

    try {
      PreparedStatement queryCustomerAccountStatement =
          conn.prepareStatement("SELECT id, user_ptr_id, date, data FROM migrations_log");
      ResultSet rs = queryCustomerAccountStatement.executeQuery();

      while (rs.next()) {
        String jsondata = rs.getString("data");

        // Parse the JSON data into a JSON object and able to retrieve value via key
        JSONObject jsonObject = new JSONObject(jsondata);

        // System.out.println(jsonObject.getString("error"));
        Log res =
            new Log(
                rs.getInt(1), // id
                rs.getInt(2), // user_ptr_id
                DateTime.parse(rs.getDate(3).toString()),
                jsonObject.getString("user"),
                jsonObject.getString("task"),
                jsonObject.getString("error"));
        result.add(res);
      }

    } catch (SQLException se) {
      System.out.println(se.getMessage());
    }

    return result;
  }
}
