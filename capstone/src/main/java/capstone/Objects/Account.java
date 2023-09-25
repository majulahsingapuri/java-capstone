package capstone.Objects;

import capstone.Enums.AccountType;

/** Represents a bank account that a customer has. */
public class Account {

  /** The id associated with each network Account. */
  private int id;

  /** The balance associated with each network Account. */
  private double balance;

  /** The accountType of the Account - 'Current' or 'Savings' */
  private AccountType accountType;

  /**
   * Initialiser for the Account Class with id, balance, account type.
   *
   * @param id The id of the Account.
   * @param balance The balance of the Account.
   * @param accountType The Account's account type
   */
  public Account(int id, double balance, AccountType accountType) {
    this.id = id;
    this.balance = balance;
    this.accountType = accountType;
  }

  /**
   * Getter method that retrieves the Account's id
   *
   * @return The ID as an int
   */
  public int getID() {
    return this.id;
  }

  /**
   * Getter method that retrieves the Balance in the Account
   *
   * @return The balance as a Double.
   */
  public double getBalance() {
    return this.balance;
  }

  /**
   * Getter method that retrieves the AccountType of the Account
   *
   * @return the AccountType of the Account.
   */
  public AccountType getAccountType() {
    return this.accountType;
  }

  /**
   * Setter method that sets the Account's balance
   *
   * @param amount the new amount to be set
   * @return true if the balance was set
   */
  public boolean setBalance(double amount) {
    if (amount >= 0) {
      this.balance = amount;
      return true;
    } else {
      return false;
    }
  }
}
