package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import model.entities.StandardDuck;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import model.gun.BulletType;
import model.gun.Magazine;
import model.gun.MagazineImpl;
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
public final class ModelImpl implements Model {
    /**
     * Game width.
     */
    public static final int GAME_WIDTH = SettingsImpl.getSettings().getSelectedResolution().getKey();
    /**
     * Game height.
     */
    public static final int GAME_HEIGHT = SettingsImpl.getSettings().getSelectedResolution().getValue();
    /**
     * Maximum number of magazines carriable.
     */
    public static final int MAX_MAGAZINES = 20;

    private static final int NEXT_DUCKS_POWERUP = 3;

    /**
     * All objects of the game world.
     */
    private Dog dog;
    private final List<Duck> ducks;
    private final List<PowerUp> powerUp;
    private int currentMagazine;
    private Magazine magazine;
    private Optional<AbstractMatch> match;
    private DuckSpawner spawner;
    private GameMode gameMode;
    private GlobalDifficulty difficulty;
    private DogStatus lastDogStatus;
    private Cleaner cleaner;
    private int duckPowerUp;
    private Optional<PowerUpType> powerUpActive;
    private int timeElapsedPowerUp = 0;
    private int lastRound;

    /**
     * Constructor of the model.
     */
    public ModelImpl() {
        super();
        this.dog = new DogImpl();
        this.ducks = new ArrayList<>();
        this.powerUp = new ArrayList<>();
        this.cleaner = new CleanerImpl();
        this.match = Optional.empty();
        this.difficulty = GlobalDifficulty.EASY;
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.gameMode = gameMode;
        this.dog = new DogImpl();
        ducks.clear();
        powerUp.clear();
        this.magazine = new MagazineImpl(1);
        this.currentMagazine = 1;
        this.duckPowerUp = 0;
        this.powerUpActive = Optional.empty();
        this.timeElapsedPowerUp = 0;
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
        this.getMatchData().addTimeToTimer(timeElapsed);
        this.updateRoundNumber();
        this.dog.update(timeElapsed);
        if (this.dog.getDogStatus() != this.lastDogStatus) {
            this.lastDogStatus = this.dog.getDogStatus();
        }

        // Update spawner when dog is in grass
        if (this.dog.isInGrass()) {
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

        // Update magazine
        this.getCurrentMagazine().update(timeElapsed);

        //Update ducks
        this.ducks.forEach(d -> {
            if (d.canFlyAway()) {
                d.computeFlyAway();
                dog.setDogStatus(DogStatus.LAUGH);
                this.match.get().getMatchData().incrementFlownDucks();
            }
            d.update(timeElapsed);
        });
        // Update PowerUp list
        this.powerUp.forEach(p -> {
            if (p.isVisible()) {
                this.timeElapsedPowerUp += timeElapsed;
            }
            if (p.isHit()) {
                this.duckPowerUp = NEXT_DUCKS_POWERUP;
                this.powerUpActive = Optional.of(p.getType());
            }
            p.update(timeElapsed);
        });
        if (this.duckPowerUp > 0 && this.powerUpActive.isPresent()) {
            this.activePowerUp(powerUpActive.get());
        }
        //Clean objects not necessary
        this.cleaner.clean(this.ducks, this.powerUp);
    }

    private void updateRoundNumber() {
        //Only for STORY MODE
        if (this.gameMode == GameMode.STORY_MODE && this.ducks.isEmpty()) {
            if (this.spawner.getActualRound() != this.lastRound) {
                this.dog = new DogImpl();
                this.ducks.clear();
                this.powerUp.clear();
                this.lastRound = this.spawner.getActualRound();
                this.duckPowerUp = 0;
                this.powerUpActive = Optional.empty();
            }
        }
    }

    @Override
    public void activateInfAmmo() {
        this.magazine.setBulletType(BulletType.INFINITE_BULLETS);
    }

    @Override
    public void deactivateInfAmmo() {
        this.magazine.setBulletType(BulletType.NORMAL_BULLET);
    }

    private void activePowerUp(final PowerUpType powerUp) {
        switch (powerUp) {
            case INFINITE_AMMO:
                this.getCurrentMagazine().setBulletType(BulletType.INFINITE_BULLETS);
                break;
            case SLOW_DOWN:
                this.ducks.stream()
                          .filter(d -> d.isAlive() 
                                  && d.getPosition().getX() >= StandardDuck.COLLISION_X
                                  && d.getPosition().getX() <= ModelImpl.GAME_WIDTH - StandardDuck.COLLISION_X)
                          .forEach(d -> {
                              if (this.duckPowerUp > 0) {
                                  d.setDecelerate();
                                  this.duckPowerUp--;
                              }
                          });
                break;
            case KILL_ALL:
                this.ducks.stream()
                          .filter(d -> d.isAlive() 
                                  && d.getPosition().getX() >= StandardDuck.COLLISION_X
                                  && d.getPosition().getX() <= ModelImpl.GAME_WIDTH - StandardDuck.COLLISION_X)
                          .forEach(d -> {
                              if (this.duckPowerUp > 0) {
                                  d.kill(); 
                                  this.dog.setLastDuckKilled(d);
                                  this.duckPowerUp--;
                                  this.match.get().getMatchData().incrementScoreOf(d.getScore());
                              }
                          });
                break;
            default:
                break;
        }
        if (this.duckPowerUp == 0) {
            this.powerUpActive = Optional.empty();
        }
    }


    @Override
    public Optional<PowerUpType> getPowerUpActive() {
        return this.powerUpActive;
    }

    @Override
    public boolean isGameOver() {
        final MatchData matchData = this.match.get().getMatchData();
        boolean gameOver = false;
        final int matchScore = this.match.get().getDifficulty().getLimitOfDifficulty() * this.lastRound;
        switch (this.gameMode) {
            case STORY_MODE:
                gameOver = this.spawner.getActualRound() > this.lastRound 
                            && matchData.getGlobalScore() < matchScore 
                            || this.currentMagazine > MAX_MAGAZINES
                            || this.getBullets() == 0 && this.currentMagazine == MAX_MAGAZINES
                            || this.spawner.isSpawnFinished();
            break;
            case SURVIVAL_MODE:
                gameOver = matchData.getFlownDucks() >= this.match.get().getDifficulty().getLimitOfDifficulty()
                            || this.spawner.isSpawnFinished();
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
    public int getBullets() {
        return this.getCurrentMagazine().getAmmo();
    }

    @Override
    public void shoot() {
        if (this.canShoot()) {
            this.getCurrentMagazine().shoot();
        }
    }

    @Override
    public boolean canShoot() {
        return this.getCurrentMagazine().getAmmo() > 0;
    }

    @Override
    public void recharge() {
        if (this.gameMode != null) {
            switch (this.gameMode) {
            case STORY_MODE:
                if (this.currentMagazine < MAX_MAGAZINES) {
                    this.currentMagazine++;
                    this.magazine = new MagazineImpl(this.currentMagazine);
                }
                break;
            case SURVIVAL_MODE:
                this.currentMagazine++;
                this.magazine = new MagazineImpl(this.currentMagazine);
                break;
            default:
                break;
            }
        }
    }

    @Override
    public Magazine getCurrentMagazine() {
        return this.magazine;
    }

    @Override
    public List<PowerUp> getPowerUps() {
        return this.powerUp;
    }
}

