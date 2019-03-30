package controller;

import java.util.Optional;
import java.util.function.Supplier;

import controller.files.PodiumManager;
import controller.files.PodiumManagerImpl;
import controller.files.UserManager;
import controller.files.UserManagerImpl;
import controller.input.InputController;
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
    private final Supplier<Model> modelSupplier;
    private Model model;
    private final View view;
    private final InputController input;
    private final PodiumManager podiumManager;
    private final UserManager userManager;
    private Optional<Podium> podium;
    private Optional<UserData> user;
    //Command list, mouse

    // finisci podiumManager e il suo loading

    /**
     * Constructor of the controller.
     * @param modelSupplier the structure of the game
     * @param view the view of the game
     */
    public ControllerImpl(final Supplier<Model> modelSupplier, final View view) {
        this.modelSupplier = modelSupplier;
        this.view = view;
        this.input = new InputController();
        this.podiumManager = new PodiumManagerImpl();
        this.userManager = new UserManagerImpl();
        this.podium = Optional.empty();
        this.user = Optional.empty();
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.model = this.modelSupplier.get();
        this.model.initGame(gameMode);
        this.loadPodium(gameMode);
        gameLoop = new GameLoop();
        gameLoop.start();
    }

    @Override
    public void stopGame() {
        gameLoop.stopGameLoop();
        // view method
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

    @Override
    public boolean loadPodium(final GameMode gamemode) {
        // TODO Auto-generated method stub
        switch (gamemode) {
        case STORY_MODE: 
            this.podium = this.podiumManager.loadStoryHighScores();
            break;
        case SURVIVAL_MODE:
            this.podium = this.podiumManager.loadSurvivalHighScores();
            break;
            default:
                break;
        }
        return this.podium.isPresent();
    }

    @Override
    public boolean isHighScore(final int score) {
        // TODO Auto-generated method stub
        return this.podium.get().isHighScore(score);
    }

    @Override
    public void addToPodium(final int score) {
        // TODO Auto-generated method stub
        this.podium.get().addHighScore(score, this.user.get().getName());
    }

    @Override
    public void emptyPodium() {
        // TODO Auto-generated method stub
        this.podium = Optional.empty();
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
                ControllerImpl.this.stopGame();
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
