package model;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import controller.matches.AbstractMatch;
import controller.matches.GameMode;
import controller.matches.StoryMatch;
import controller.matches.SurvivalMatch;
import model.cleaner.Cleaner;
import model.cleaner.CleanerImpl;
import model.data.MatchData;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.DogStatus;
import model.entities.Duck;
import model.entities.Entity;

import model.entities.EntityStatus;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import model.spawner.duck.DuckSpawner;
import model.spawner.duck.StoryModeSpawner;
import model.spawner.duck.SurvivalModeSpawner;
import settings.GlobalDifficulty;

import settings.SettingsImpl;

/**
 * 
 * Model implementation, contains all objects of the game.
 *
 */
public final class ModelImpl extends Canvas implements Model {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Game width.
     */
    public static final int GAME_WIDTH = SettingsImpl.getSettings().getSelectedResolution().getKey();
    /**
     * Game height.
     */
    public static final int GAME_HEIGHT = SettingsImpl.getSettings().getSelectedResolution().getValue();

    /**
     * Max of time elapsed before the update.
     */
    private static final int UPDATE_TIME = 6000;

    /**
     * All objects of the game world.
     */
    private Dog dog;
    private final List<Duck> ducks;
    private final List<PowerUp> powerUp;
    private Optional<AbstractMatch> match;
    private DuckSpawner spawner;
    private GameMode gameMode;
    private GlobalDifficulty difficulty;
    private DogStatus lastDogStatus;
    private Cleaner cleaner;
    private int duckDoubleScore;
    private int timeElapsed = 0;
    private int lastRound;

    /**
     * Constructor of the model.
     */
    public ModelImpl() {
        super();
        this.dog = new DogImpl();
        this.ducks = new ArrayList<>();
        this.powerUp = new ArrayList<>();
        this.match = Optional.empty();
        this.difficulty = GlobalDifficulty.EASY;
        this.cleaner = new CleanerImpl();
        this.duckDoubleScore = 0;
        this.initGame(GameMode.STORY_MODE);
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.gameMode = gameMode;
        this.dog = new DogImpl();
        ducks.clear();
        powerUp.clear();
        switch (gameMode) {
        case STORY_MODE:
            this.match = Optional.of(new StoryMatch(this.difficulty));
            this.spawner = new StoryModeSpawner();
            this.lastRound = this.spawner.getActualRound();
            break;
        case SURVIVAL_MODE:
            this.match = Optional.of(new SurvivalMatch(this.difficulty));
            this.spawner = new SurvivalModeSpawner();
            this.lastRound = this.spawner.getActualRound();
            break;
        default:
            break;
        }
    }

    @Override
    public void update(final int timeElapsed) {
<<<<<<< HEAD
        // Update dog
=======
        this.updateRoundNumber();
        //Update dog
>>>>>>> 1a53ce0e4484952cc17a1d09d62067654a2f22a5
        this.dog.update(timeElapsed);
        if (this.dog.getDogStatus() != this.lastDogStatus) {
            this.lastDogStatus = this.dog.getDogStatus();
        }

        // Update spawner when dog is in grass
        if (this.dog.isInGrass()) {
            this.timeElapsed += timeElapsed;
                spawner.update(timeElapsed);
                if (spawner.canSpawnDuck()) {
                final Optional<Duck> duckSpawn = spawner.spawnDuck();
                if (duckSpawn.isPresent()) {
                    this.ducks.add(duckSpawn.get());
                    if (duckSpawn.get().hasPowerUp()) {
                        this.powerUp.add(duckSpawn.get().getPowerUp().get());
                    }
                }
            }
        }
<<<<<<< HEAD
        // Update ducks

        for (Duck d : this.ducks) {
            if (this.timeElapsed >= UPDATE_TIME) {
                if (d.getStatus() == EntityStatus.ALIVE) {
                    this.timeElapsed -= UPDATE_TIME;
=======
        //Update ducks
        this.ducks.forEach(d -> {
            //SIMULATE KILL EACH 4000ms = 5s
            if (this.timeElapsed >= 4000) {
                if (d.getStatus() == EntityStatus.ALIVE) {
                    this.timeElapsed -= 4000;
>>>>>>> 1a53ce0e4484952cc17a1d09d62067654a2f22a5
                    d.kill();
                    if (d.hasPowerUp()) {
                        d.getPowerUp().get().hit();
                    }
                    final int score = this.duckDoubleScore > 0 ? d.getScore() * 2 : d.getScore();
                    this.duckDoubleScore = this.duckDoubleScore > 0 ? this.duckDoubleScore-- : 0;
                    this.match.get().getMatchData().incrementScoreOf(score);
                    dog.setDogStatus(DogStatus.HAPPY);
                }
            }
            if (d.canFlyAway()) {
                d.computeFlyAway();
                dog.setDogStatus(DogStatus.LAUGH);
                this.match.get().getMatchData().incrementFlownDucks();
            }
            d.update(timeElapsed);
        }

        // Update PowerUp list
        this.powerUp.forEach(p -> {
            if (p.isHit()) {
                this.activePowerUp(p.getType());
            }
            p.update(timeElapsed);
        });
<<<<<<< HEAD
        this.deleteUnnecessaryPowerUp();
        // Only for STORY MODE
=======
        //Clean the powerUp out of screen
        this.cleaner.cleanPowerUp(this.powerUp);
    }

    private void updateRoundNumber() {
        //Only for STORY MODE
>>>>>>> 1a53ce0e4484952cc17a1d09d62067654a2f22a5
        if (this.gameMode == GameMode.STORY_MODE) {
            if (this.spawner.getActualRound() != this.lastRound) {
                this.ducks.clear();
                this.lastRound = this.spawner.getActualRound();
            }
        }
    }

<<<<<<< HEAD
    private void deleteUnnecessaryPowerUp() {
        final List<Integer> indexesPowUp = new ArrayList<>();
        for (int i = 0; i < this.powerUp.size(); i++) {
            if (this.powerUp.get(i).isHit() || this.powerUp.get(i).getPosition().getY() <= 0
            || this.powerUp.get(i).getPosition().getY() >= GAME_HEIGHT) {
                indexesPowUp.add(i);
            }
        }
        for (final Integer index : indexesPowUp) {
            this.powerUp.remove((int) index);
        }
    }

    private void activePowerUp(final PowerUpType powerUp) {
        System.out.println(powerUp.toString());
        switch (powerUp) {
        case INFINITE_AMMO:
            /* Bullet */
            break;
        case KILL_ALL:
            for (final Duck duck : this.ducks) {
                if (duck.isAlive()) {
                    duck.kill();
                }
            }
            break;
        default:
            break;
=======
    private void activePowerUp(final PowerUpType powerUp) {
        System.out.println(powerUp.toString());
        switch (powerUp) {
            case DOUBLE_SCORE:
                this.ducks.stream()
                          .filter(d -> d.isAlive())
                          .forEach(d -> {
                              this.duckDoubleScore++;
                          });
                break;
            case INFINITE_AMMO:
                /* Bullet */
                break;
            case SLOW_DOWN:
                this.ducks.stream()
                          .filter(d -> d.isAlive())
                          .forEach(d -> d.setDecelerate());
                break;
            case KILL_ALL:
                this.ducks.stream()
                          .filter(d -> d.isAlive())
                          .forEach(d -> d.kill());
                break;
            default:
                break;
>>>>>>> 1a53ce0e4484952cc17a1d09d62067654a2f22a5
        }
    }

    @Override
    public boolean isGameOver() {
        final MatchData matchData = this.match.get().getMatchData();
        boolean gameOver = false;
        final int matchScore = this.match.get().getDifficulty().getLimitOfDifficulty() * this.lastRound;
        switch (this.gameMode) {
            case STORY_MODE:
                gameOver = this.spawner.getActualRound() > this.lastRound 
                            && matchData.getGlobalScore() < matchScore;
            break;
            case SURVIVAL_MODE:
                gameOver = matchData.getFlownDucks() >= this.match.get().getDifficulty().getLimitOfDifficulty();
            break;
            default:
                break;
        }
        return gameOver;
    }

    @Override
    public void endMatch() {
        this.match = Optional.empty();
    }

    @Override
    public List<Entity> getEntities() {
        final List<Entity> listEntity = new ArrayList<>();
        listEntity.addAll(this.ducks);
        listEntity.addAll(this.powerUp);
        listEntity.add(this.dog);
        return listEntity;
    }

    @Override
    public MatchData getMatchData() {
        return this.match.get().getMatchData();
    }

    @Override
    public List<Duck> getDucks() {
        return this.ducks;
    }

    @Override
    public void setAimX() {
        // TODO Auto-generated method stub
<<<<<<< HEAD

=======
>>>>>>> 1a53ce0e4484952cc17a1d09d62067654a2f22a5
    }

    @Override
    public void setAimY() {
        // TODO Auto-generated method stub
<<<<<<< HEAD

=======
>>>>>>> 1a53ce0e4484952cc17a1d09d62067654a2f22a5
    }

    /*
     * @Override public List<Bullet> getBullets() { return null; }
     */
}
