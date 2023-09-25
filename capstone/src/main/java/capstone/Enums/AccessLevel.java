package capstone.Enums;

/**
 * Sets the Access permissions for application and ensures that the person is logging into the
 * correct domain.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public enum AccessLevel {
  /** Used to indicate that no user is logged in. */
  NONE("migrations_user"),

  /** Used to indicate a Customer on the Bank Domain. */
  CUSTOMER("migrations_customer"),

  /** Used to indicate a Teller on the Bank Domain. */
  TELLER("migrations_teller"),

  /** Used to indicate a User on the Admin Domain. */
  ADMIN("migrations_admin");

  public final String db;

  private AccessLevel(String db) {
    this.db = db;
  }
}
