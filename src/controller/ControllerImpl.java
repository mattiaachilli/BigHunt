package controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import controller.converter.EntitiesConverter;
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
import view.entities.ViewEntity;

/**
 * 
 * This class represents the controller.
 * 
 */

public final class ControllerImpl implements Controller {

    private static final int GREEN_SEMAPHORE = 1;
    /**
     * 60 FPS.
     */
    private static final int PERIOD = 16;

    private GameLoop gameLoop;
    private final Supplier<Model> modelSupplier;
    private Model model;
    private final Semaphore mutex;
    private final View view;
    private final PodiumManager podiumManager;
    private final UserManager userManager;
    private Podium storyPodium;
    private Podium survivalPodium;
    private Optional<UserData> user;
    // Command list, mouse

    // finisci podiumManager e il suo loading

    /**
     * Constructor of the controller.
     * 
     * @param modelSupplier the structure of the game
     * @param view          the view of the game
     */
    public ControllerImpl(final Supplier<Model> modelSupplier, final View view) {
        this.modelSupplier = modelSupplier;
        this.view = view;
        this.podiumManager = new PodiumManagerImpl();
        this.storyPodium = this.podiumManager.loadStoryPodium().get();
        this.survivalPodium = this.podiumManager.loadSurvivalPodium().get();
        this.userManager = new UserManagerImpl();
        this.user = Optional.empty();
        this.mutex = new Semaphore(GREEN_SEMAPHORE);
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.model = this.modelSupplier.get();
        this.model.initGame(gameMode);
        this.view.render(getEntitiesForView(0), this.model.getMatchData());
    }

    @Override
    public void startGameLoop() {
        gameLoop = new GameLoop();
        gameLoop.start();
    }

    @Override
    public void stopGameLoop() {
        gameLoop.stopGameLoop();
        // view method
    }

    @Override
    public boolean loginUser(final String username, final String password) {
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
    public Optional<UserData> getUser() {
        if (this.user.isPresent()) {
            return this.user;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean registerUser(final String username, final String password) {
        this.user = this.userManager.register(username, password);
        return this.user.isPresent();
    }

    @Override
    public boolean isHighScore(final GameMode gameMode, final int score) {
        switch (gameMode) {
        case STORY_MODE:
            return this.storyPodium.isHighScore(score);
        case SURVIVAL_MODE:
            return this.survivalPodium.isHighScore(score);
        default:
            return false;
        }
    }

    @Override
    public void addToPodium(final GameMode gameMode, final int score) {
        switch (gameMode) {
        case STORY_MODE:
            this.storyPodium.addHighScore(score, this.user.get().getName());
            break;
        case SURVIVAL_MODE:
            this.survivalPodium.addHighScore(score, this.user.get().getName());
            break;
        default:
            break;
        }
    }

    @Override
    public void pause() {
        this.gameLoop.pauseLoop();
    }

    @Override
    public void resume() {
        this.gameLoop.resumeLoop();
    }

    private List<Optional<ViewEntity>> getEntitiesForView(final int elapsed) {
        return this.model.getEntities().stream().map(e -> EntitiesConverter.convertEntity(e, elapsed))
        .collect(Collectors.toList());
    }

    private class GameLoop extends Thread {
        private volatile boolean running;
        private boolean paused;

        GameLoop() {
            super();
            running = true;
            paused = false;
        }

        public void run() {
            long lastTime = System.currentTimeMillis();
            while (running && !model.isGameOver()) { /* Running and not gameover */
                if (!this.paused) {
                    final long current = System.currentTimeMillis();
                    final int elapsed = (int) (current - lastTime);
                    processInput();
                    model.update(elapsed);
                    view.render(getEntitiesForView(elapsed), model.getMatchData());
                    Utilities.waitForNextFrame(PERIOD, current);
                    lastTime = current;
                }
            }
            if (model.isGameOver()) {
                ControllerImpl.this.stopGameLoop();
                System.exit(0);
            }
        }

        private void processInput() {
            /*
             * try { mutex.acquire(); mutex.release(); } catch (InterruptedException e) {
             * e.printStackTrace(); }
             */
        }

        public void pauseLoop() {
            this.paused = true;
        }

        public void resumeLoop() {
            this.paused = false;
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
