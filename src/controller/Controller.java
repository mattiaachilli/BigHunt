package controller;

import java.util.Optional;

import controller.input.CommandType;
import model.data.UserData;
import model.matches.GameMode;

/**
 * 
 * Controller of the application, interacts with model and view.
 */

public interface Controller {

    /**
     * Initializes the game model and starts the game loop.
     * 
     * @param gameMode the game mode selected to play
     */
    void initModel(GameMode gameMode);

    /**
     * Initializes the game loop.
     */
    void initGameLoop();

    /**
     * Starts the game loop.
     */
    void startGameLoop();

    /**
     * Resumes the game loop.
     * Method to be called by the button RESUME.
     */
    void resumeGameLoop();

    /**
     * Stops the game loop.
     */
    void stopGameLoop();

    /**
     * 
     * @return true if the game loop is paused.
     */
    boolean isGameLoopPaused();

    /**
     * Adds the command to the command list.
     * @param command
     *          the commandType to add.
     * @param x
     *          x coordinate.
     * @param y
     *          y coordinate.
     */
    void notifyCommand(CommandType command, double x, double y);

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
     * 
     * @return an optional of the  current user.
     */
    Optional<UserData> getUser();

    /**
     * Registration of a user.
     * 
     * @param username the player's name
     * @param password the user's password
     * @return true if the user is correctly registered
     */
    boolean registerUser(String username, String password);
}
