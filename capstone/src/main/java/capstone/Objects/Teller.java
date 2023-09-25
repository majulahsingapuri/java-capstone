package capstone.Objects;

import capstone.Enums.AccessLevel;

/**
 * The User class that defines the actions of a System Administator. Extends {@link User} with Access Level {@link AccessLevel#ADMIN}.
 * @author Jia Hui
 * @version 1.0
 * @since 2020-11-1
 */
public final class Teller extends User {

    /**
     * Unique identifier for Serialisation.
     */
    

    /**
     * Initialiser for Class. Creates new object with new username, default password and Teller AccessLevel.
     * @param username The User's username.
     */
    public Teller(String username){
        super(username, AccessLevel.TELLER);
    }

    /**
     * Initialiser for Class. Creates new object with new username and password, and Teller AccessLevel.
     * @param username The User's username.
     * @param password The User's password.
     */
    public Teller(String username, String password){
        super(username, password, AccessLevel.TELLER);
    }
}
