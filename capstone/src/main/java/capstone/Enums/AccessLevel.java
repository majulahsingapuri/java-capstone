package capstone.Enums;

/**
 * Sets the Access permissions for application and ensures that the person is logging into the correct domain.
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public enum AccessLevel {
    /**
     * Used to indicate that no user is logged in.
     */
    NONE,

    /**
     * Used to indicate a Customer on the Bank Domain.
     */
    CUSTOMER,

    /**
     * Used to indicate a Teller on the Bank Domain.
     */
    TELLER,

    /**
     * Used to indicate a User on the Admin Domain.
     */
    ADMIN
}
