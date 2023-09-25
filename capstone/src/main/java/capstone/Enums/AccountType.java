package capstone.Enums;

/** The type of account to be created */
public enum AccountType {
  /** Used to indicate that the account type is Current */
  CURRENT("CURRENT"),

  /** Used to indicate that the account type is Savings */
  SAVINGS("SAVINGS");

  /** The account type string */
  public final String type;

  /**
   * Constructor for the AccountType Enum
   *
   * @param type the String representing the account type.
   */
  private AccountType(String type) {
    this.type = type;
  }
}
