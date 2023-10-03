package capstone.Objects;

import capstone.Enums.TransactionType;
import org.joda.time.DateTime;

/** Represting a transaction executed by a customer in the bank */
public class Transaction {
  /** The User ID associated with each Transaction. */
  private int id;

  /** The Reference associated with each network User. */
  private String transactionRef;

  /** The transaction type: Debit or Credit */
  private TransactionType transactionType;

  /** The datetime object of the transaction */
  private DateTime date;

  /** The transaction type: Debit or Credit */
  private double amount;

  /** The account number of the customer */
  private int account_no_id;

  /** The customer id of the customer */
  private int customer_id;

  /**
   * Initialiser for the
   *
   * @param id The id of the transaction
   * @param transactionRef The transaction reference linked to the Transaction
   * @param transactionType The transaction type linked to the Transaction
   * @param date The date of the transaction
   * @param amount The amount of the transaction
   * @param account_no_id The account no id
   * @param customer_id The customer ID linked to the Transaction
   */
  public Transaction(
      int id,
      String transactionRef,
      TransactionType transactionType,
      DateTime date,
      double amount,
      int account_no_id,
      int customer_id) {
    this.id = id;
    this.transactionRef = transactionRef;
    this.transactionType = transactionType;
    this.date = date;
    this.amount = amount;
    this.account_no_id = account_no_id;
    this.customer_id = customer_id;
  }

  /**
   * retrieves the id of the transaction
   *
   * @return the ID of the Transaction
   */
  public int getID() {
    return this.id;
  }

  /**
   * retrieves the transaction reference number
   *
   * @return the Transaction Reference
   */
  public String getTransactionRef() {
    return this.transactionRef;
  }

  /**
   * retrieves the transaction type
   *
   * @return the Transaction Type
   */
  public TransactionType getTransactionType() {
    return this.transactionType;
  }

  /**
   * retrieves the transaction
   *
   * @return the Date of the Transaction
   */
  public DateTime getDate() {
    return this.date;
  }

  /**
   * retrieves the amount in that transaction
   *
   * @return the Amount of the Transaction
   */
  public double getAmount() {
    return this.amount;
  }

  /**
   * retrieves the account id
   *
   * @return the Account No ID of the Transaction
   */
  public int getAccountNoID() {
    return this.account_no_id;
  }

  /**
   * retrieves the customer id
   *
   * @return the Customer ID of the Transaction
   */
  public int getCustomerID() {
    return this.customer_id;
  }
}
