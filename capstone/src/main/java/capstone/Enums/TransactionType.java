package capstone.Enums;

public enum TransactionType {
  /** A Credit type transaction */
  CREDIT("CREDIT"),

  /** A Debit type transaction */
  DEBIT("DEBIT");

  /** String to store the type value */
  public final String type;

  /**
   * Constructor for the Transaction type Enum
   *
   * @param type the String value of the transaction type.
   */
  private TransactionType(String type) {
    this.type = type;
  }
}
