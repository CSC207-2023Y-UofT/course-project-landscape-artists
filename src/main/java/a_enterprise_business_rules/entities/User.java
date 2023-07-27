package a_enterprise_business_rules.entities;

import DBControllers.DBManagerInsertController;
import UUIDsToHashMap.UUIDMap;

import java.util.*;

/**
 * An entity class to represent a user.
 */
public class User {
    /**
     * The ID of this user.
     */
    private UUID ID;; // temporary placeholder

    /**
     * The username of this user.
     */
    private String username;

    /**
     * The password of this user.
     */
    private String password; // temporary placeholder

    /**
     * The email address of this user.
     */
    private String email;


    /**
     * Constructs a User, username, and password.
     *
     * @param username    The user's username.
     * @param password    The user's password.
     */
    public User(String username, String password) {
        this.ID = getValidUserID();
        this.username = username;
        this.password = password;
    }

    /**
     * Constructs a User, username, password, email.
     *
     * @param username    The user's username.
     * @param password    The user's password.
     * @param email       The user's email.
     */
    public User(String username, String password, String email) {
        this.ID = getValidUserID();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user.
     */
    public String getID() { return this.ID; }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user.
     */
    public String getPassword() { return password; }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user.
     */
    public String getEmail() { return email; }

    /**
     * Sets the email of the user.
     *
     * @param email the new email of the user.
     */
    public void setEmail(String email) { this.email = email; }



}
