package controller;

import controller.matches.GameMode;

/**
 * 
 * @author mattia Controller of the application, interacts with model and view.
 */

public interface Controller {

    /**
     * Initialize game model and start the game loop.
     * 
     * @param gameMode the game mode selected to play
     */
    void initGame(GameMode gameMode);

    /**
     * Stop the game loop.
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

    /**
     * 
     * @param gamemode the game mode of the match, used to decide which is the
     *                 podium to load
     * @return true if the podium is correctly loaded
     */
    boolean loadPodium(GameMode gamemode);

    /**
     * 
     * @param score the score to control
     * @return true if the @param score is an high score
     */
    boolean isHighScore(int score);

    /**
     * Adds an high score to the podium.
     * 
     * @param score the new high score
     */
    void addToPodium(int score);

    /**
     * No more podium needed.
     */
    void emptyPodium();

    /**
     * Pauses the game loop.
     */
    void pause();

    /**
     * Resume the game loop.
     */
    void resume();
}
