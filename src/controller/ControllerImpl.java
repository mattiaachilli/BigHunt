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
import controller.input.CommandType;
import controller.input.InputController;
import controller.input.Recharge;
import controller.input.Shoot;
import model.Model;
import model.data.MatchData;
import model.data.Podium;
import model.data.UserData;
import model.matches.GameMode;
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
    private InputController input;
    private final Semaphore mutex;
    private final View view;
    private final PodiumManager podiumManager;
    private final UserManager userManager;
    private Podium storyPodium;
    private Podium survivalPodium;
    private Optional<UserData> user;

    /**
     * Constructor of the controller.
     * 
     * @param modelSupplier the structure of the game
     * @param view          the view of the game
     */
    public ControllerImpl(final Supplier<Model> modelSupplier, final View view) {
        this.modelSupplier = modelSupplier;
        this.view = view;
        this.input = new InputController();
        this.podiumManager = new PodiumManagerImpl();
        this.storyPodium = this.podiumManager.loadStoryPodium().get();
        this.survivalPodium = this.podiumManager.loadSurvivalPodium().get();
        this.userManager = new UserManagerImpl();
        this.user = Optional.empty();
        this.mutex = new Semaphore(GREEN_SEMAPHORE);
    }

    @Override
    public void initGame(final GameMode gameMode) {
        //System.out.println("Nuovo model");
        this.model = this.modelSupplier.get();
        //System.out.println("Nuova partita");
        this.model.initGame(gameMode);
        this.input.clearCommands();
        this.view.render(getEntitiesForView(0), this.model.getMatchData(), this.model.getCurrentMagazine(), this.model.getInfo());
    }

    @Override
    public void initGameLoop() {
        this.gameLoop = new GameLoop();
    }

    @Override
    public void startGameLoop() {
        this.gameLoop.start();
    }

    @Override
    public void resumeGameLoop() {
        this.input.clearCommands();
        this.gameLoop.resumeLoop();
    }

    @Override
    public void stopGameLoop() {
        gameLoop.stopGameLoop();
        view.stopRender();
    }

    @Override
    public void notifyCommand(final CommandType command, final double x, final double y) {
        try {
            mutex.acquire();
            switch (command) {
                case PAUSE:
                    if (!this.gameLoop.isPaused()) {
                        this.gameLoop.pauseLoop();
                        this.view.pauseRender();
                        this.view.getSceneFactory().openPauseScene();
                    } else {
                        this.input.clearCommands();
                        this.gameLoop.resumeLoop();
                        this.view.getSceneFactory().openGameScene();
                        this.view.resumeRender();
                    }
                    break;
                case RECHARGE:
                    this.input.setCommand(new Recharge());
                    break;
                case SHOOT:
                    this.input.setCommand(new Shoot(x, y));
                    break;
                default:
                    break;
            }
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private List<Optional<ViewEntity>> getEntitiesForView(final int elapsed) {
        return this.model.getEntities().stream().map(e -> EntitiesConverter.convertEntity(e, elapsed)).collect(Collectors.toList());
    }

    /**
     * 
     * Stop the game loop.
     * Refresh all the values obtained from the match (achievements and eventual high scores).
     */
    private void endGame() {
        this.view.closeGame(this.model.getMatchData(), true);
        this.stopGameLoop();


        final MatchData matchdata = this.model.getMatchData();

        this.user.get().addMatchData(matchdata);

        switch (this.model.getGameMode()) {
        case STORY_MODE:
            if (this.storyPodium.isHighScore(matchdata.getGlobalScore())) {
                this.storyPodium.addHighScore(matchdata.getGlobalScore(), this.user.get().getName());
                this.podiumManager.saveStoryHighScores(this.storyPodium);
            }
            break;
        case SURVIVAL_MODE:
            if (this.survivalPodium.isHighScore(matchdata.getGlobalScore())) {
                this.survivalPodium.addHighScore(matchdata.getGlobalScore(), this.user.get().getName());
                this.podiumManager.saveSurvivalHighScores(this.survivalPodium);
            }
            break;
            default:
                break;
        }

        this.model.endMatch();
        this.userManager.save(this.user.get());
    }

    private class GameLoop extends Thread {
        private volatile boolean running;
        private volatile boolean paused;

        GameLoop() {
            super();
            running = true;
            paused = false;
        }

        public void run() {
            //System.out.println("Game loop partito");
            long lastTime = System.currentTimeMillis();
            while (running) {
                while (!model.isGameOver()) {
                    final long current = System.currentTimeMillis();
                    final int elapsed = (int) (current - lastTime);
                    if (!this.paused) {
                        processInput();
                        model.update(elapsed);
                        view.render(getEntitiesForView(elapsed), model.getMatchData(), model.getCurrentMagazine(),
                            model.getInfo());
                    }
                    Utilities.waitForNextFrame(PERIOD, current);
                    lastTime = current;
                }
                if (model.isGameOver()) {
                    endGame();
                }
            }
        }

        private void processInput() {
            try {
                ControllerImpl.this.mutex.acquire();
                if (!input.getCommands().isEmpty()) {
                    input.executeCommand(model);
                }
                ControllerImpl.this.mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void pauseLoop() {
            this.paused = true;
        }

        public void resumeLoop() {
            this.paused = false;
        }

        public boolean isPaused() {
            return this.paused;
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
