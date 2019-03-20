package controller;

import java.util.Optional;

import controller.files.PodiumManager;
import controller.files.PodiumManagerImpl;
import controller.files.UserManager;
import controller.files.UserManagerImpl;
import controller.matches.GameMode;
import model.Model;
import model.data.Podium;
import model.data.UserData;
import utility.Utilities;
import view.View;

/**
 * 
 * This class represents the controller.
 * 
 */

public final class ControllerImpl implements Controller {
    /**
     * 60 FPS.
     */
    private static final int PERIOD = 16;

    private GameLoop gameLoop;
    private final Model model;
    private final View view;
    private final PodiumManager podiumManager;
    private final UserManager userManager;
    private Podium podium;
    private Optional<UserData> user;
    //Command list, mouse
    
    

    /**
     * Constructor of the controller.
     * @param model the structure of the game
     * @param view the view of the game
     */
    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
        this.podiumManager = new PodiumManagerImpl();
        this.userManager = new UserManagerImpl();
        this.user = Optional.empty();
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.model.initGame(gameMode);
    }

    @Override
    public void startGame() {
        gameLoop = new GameLoop();
        gameLoop.start();
    }

    @Override
    public void stopGame() {
        gameLoop.stopGameLoop();
    }

    private void endGame() {
        stopGame();
    }

    @Override
    public boolean loginUser(final String username, final String password) {
        // TODO Auto-generated method stub
        this.user = this.userManager.login(username, password);
        return this.user.isPresent();
    }

    @Override
    public void logoutUser() {
        if (this.user.isPresent()) {
            this.userManager.save(this.user.get());
            this.user = Optional.empty();
        }
    }

    @Override
    public boolean registerUser(final String username, final String password) {
        // TODO Auto-generated method stub
        this.user = this.userManager.register(username, password);
        return this.user.isPresent();
    }

    private class GameLoop extends Thread { 
        private volatile boolean running;

        GameLoop() {
            super();
            running = true;
        }

        public void run() {
            long lastTime = System.currentTimeMillis();
            while (running && !model.isGameOver()) { /* Running and not gameover */
                final long current = System.currentTimeMillis();
                final int elapsed = (int) (current - lastTime);
                processInput();
                model.update(elapsed);
                // view.setStateGame(getEntitiesForView(), model.getGameData());
                Utilities.waitForNextFrame(PERIOD, current);
                lastTime = current;
            }
            if (model.isGameOver()) {
                ControllerImpl.this.endGame();
            }
        }

        private void processInput() {
            // Input from mouse command example
        }

        public void stopGameLoop() {
            this.running = false;
        }

        @Override
        public void start() {
            this.running = true;
            super.start();
        }
    }
}
