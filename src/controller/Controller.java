package controller;

import controller.matches.GameMode;

/**
 * 
 * @author mattia Controller of the application, interacts with model and view.
 */

public interface Controller {

    /**
     * Initialize game model.
     * @param gameMode the game mode selected to play
     */
    void initGame(GameMode gameMode);

    /**
     * Start game loop.
     */
    void startGame();

    /**
     * Stop the game.
     */
    void stopGame();

    /**
     * Login of a player.
     * 
     * @param username the player's name
     * @param password the user's password
     * @return true if the user has logged in correctly
     */
    boolean loginUser(String username, String password);

    /**
     * Logout of the user.
     */
    void logoutUser();

    /**
     * Registration of a user.
     * 
     * @param username the player's name
     * @param password the user's password
     * @return true if the user is correctly registered
     */
    boolean registerUser(String username, String password);
}
