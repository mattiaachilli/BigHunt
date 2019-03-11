package controller.files;

import java.util.Optional;

import model.data.UserData;

/**
 * 
 * @author simone
 * Interface that manages the login, the registration and the saving of a user
 */
public interface UserManager {
    
    /**
     * Method that makes the login for a player
     * @param userName, the player's username
     * @param password, the player's password
     * @return an optional containing the data of the user
     */
    public Optional<UserData> login(String userName, String password);
    
    /**
     * Method that saves modifications to a user's account
     * @param data, the new modified data
     */
    public boolean save(UserData data);
    
    /**
     * Method that registers a new user
     * @param userName, the player's username
     * @param password, the player's password
     * @return an optional containing the data of the user
     */
    public Optional<UserData> register(String userName, String password);
}
