package capstone.Objects;

import capstone.Enums.AccessLevel;
import java.util.ArrayList;
import java.util.Date;

/**
 * The User class that defines the actions of a System Administator. Extends {@link User} with
 * Access Level {@link AccessLevel#ADMIN}.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public final class Customer extends User {

  /** The id associated with each network Customer. */
  private int customerID;

  /** The nric associated with each network Customer. */
  private String nric;

  /** The email associated with each network Customer. */
  private String email;

  /** The date of birth associated with each network Customer. */
  private Date dateOfBirth;

  /** The address associated with each network Customer. */
  private String address;

  /** The phone number associated with each network Customer. */
  private String phoneNumber;

  /**
   * Initialiser for Customer Class,
   *
   * @param id the User's id
   * @param username The User's username.
   * @param password The User's password.
   * @param customerID The User's password.
   * @param firstName The User's password.
   * @param lastName The User's password.
   * @param nric The User's password.
   * @param email The User's password.
   * @param dateOfBirth The User's password.
   * @param address The User's password.
   * @param phoneNumber The User's password.
   */
  public Customer(
      int id,
      String username,
      String password,
      int customerID,
      String firstName,
      String lastName,
      String nric,
      String email,
      Date dateOfBirth,
      String address,
      String phoneNumber) {
    super(id, username, password, firstName, lastName, AccessLevel.CUSTOMER);
    this.customerID = customerID;
    this.nric = nric;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  /**
   * retrieves the id of the customer
   *
   * @return the ID of the Custome
   */
  public int getCustomerID() {
    return this.customerID;
  }

  /**
   * retrieves the nric of the transaction
   *
   * @param nric the new nric of the Customer
   */
  public void setNRIC(String nric) {
    this.nric = nric;
  }

  /**
   * retrieves the email of the customer
   *
   * @param email the email of the customer
   * @return true if the email was successfully set
   */
  public boolean setEmail(String email) {
    // TODO: use REGEX to check email validity
    this.email = email;
    return true;
  }

  /**
   * retrieves the date of birth of the customer
   *
   * @param dateOfBirth the new date of birth of the customer.
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /***
   * Sets the address of the Customer
   * @param address set the address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /***
   * Sets the phone number of the Customer
   * @param phoneNumber set the number
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * retrieves the nric of the customer
   *
   * @return the NRIC of the Customer
   */
  public String getNRIC() {
    return this.nric;
  }

  /**
   * retrieves the email of the customer
   *
   * @return the email of the Customer
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * retrieves the date of birth of the customer
   *
   * @return the date of birth of the Customer
   */
  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

  /**
   * retrieves the customer's address
   *
   * @return the customer's address as a string
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * retrieves the customer's phone number
   *
   * @return the customer's Phone Number
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Wrapper function that gets the Customer's bank accounts.
   *
   * @return the customer accounts
   */
  public ArrayList<Account> getCustomerAccounts() {
    return Database.getCustomerAccounts(this);
  }
}
