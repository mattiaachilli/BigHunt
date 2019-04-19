package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import audio.SoundUtil;
import model.cleaner.Cleaner;
import model.cleaner.CleanerImpl;
import model.data.MatchData;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.DogStatus;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.EntityStatus;
import model.entities.StandardDuck;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import model.gun.BulletType;
import model.gun.Magazine;
import model.matches.AbstractMatch;
import model.matches.GameMode;
import model.matches.StoryMatch;
import model.matches.SurvivalMatch;
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

    private static final long MAX_TIME = 3000;

    /**
     * Next ducks to activate the power Up.
     */
    public static final int NEXT_DUCKS_POWERUP = 3;

    /**
     * All the objects of the game world.
     */
    private Dog dog;
    private final List<Duck> ducks;
    private final List<PowerUp> powerUp;
    private AbstractMatch match;
    private DuckSpawner spawner;
    private GameMode gameMode;
    private final Cleaner cleaner;
    private int duckPowerUp;
    private Optional<PowerUpType> powerUpActive;
    private int currentRound;
    private long powerUpTime;

    /**
     * Constructor of the model.
     */
    public ModelImpl() {
        super();
        this.ducks = new ArrayList<>();
        this.powerUp = new ArrayList<>();
        this.cleaner = new CleanerImpl();
    }

    @Override
    public void initGame(final GameMode gameMode) { 
        final GlobalDifficulty difficulty = SettingsImpl.getSettings().getSelectedDifficulty();
        this.gameMode = gameMode;
        this.dog = new DogImpl();
        ducks.clear();
        powerUp.clear();
        this.duckPowerUp = 0;
        this.powerUpTime = 0;
        this.powerUpActive = Optional.empty();
        switch (gameMode) {
            case STORY_MODE:
                this.match = new StoryMatch(difficulty);
                this.spawner = new StoryModeSpawner();
                break;
            case SURVIVAL_MODE:
                this.match = new SurvivalMatch(difficulty);
                this.spawner = new SurvivalModeSpawner();
                break;
            default:
                break;
        }

        this.currentRound = this.match.getCurrentRound();
    }

    @Override
    public void update(final int timeElapsed) {
        this.getMatchData().addTimeToTimer(timeElapsed);
        this.updateRoundNumber();
        this.dog.update(timeElapsed);

        // Update spawner when dog is in grass
        if (this.dog.isInGrass()) {
            spawner.update(timeElapsed);
            if (spawner.canSpawnDuck()) {
                final Optional<Duck> duckSpawn = spawner.spawnDuck();
                if (duckSpawn.isPresent()) {
                    this.ducks.add(duckSpawn.get());
                    if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                        SoundUtil.playSound(SoundUtil.getDuckCall());
                    }
                    if (duckSpawn.get().hasPowerUp()) {
                        this.powerUp.add(duckSpawn.get().getPowerUp().get());
                    }
                } else if (this.spawner.getCurrentRound() > this.currentRound) {
                    this.match.endRound();
                }
            }
        }

        // Update magazine
        if (this.getCurrentMagazine().getBulletType().equals(BulletType.INFINITE_BULLETS)) {
            this.powerUpTime = this.powerUpTime + timeElapsed;
            if (this.powerUpTime > MAX_TIME) {
                this.endPowerUp();
                this.getCurrentMagazine().setBulletType(BulletType.NORMAL_BULLET);
            }
        }

        // Active powerUp if exist
        if (this.powerUpActive.isPresent()) {
            this.activePowerUp(powerUpActive.get());
        }

        //Update ducks
        this.ducks.forEach(d -> {
            d.update(timeElapsed);
            if (d.canFlyAway()) {
                d.computeFlyAway();
                dog.setDogStatus(DogStatus.LAUGH);
            }
            if (d.getStatus() == EntityStatus.FLOWN_AWAY && d.getPosition().getY() <= 0) {
                if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                    SoundUtil.playSound(SoundUtil.getDogLaughAudio());
                }
                this.match.getMatchData().incrementFlownDucks();
            } else if (d.getStatus() == EntityStatus.DEAD && d.getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                this.dog.setLastDuckKilled(d);
                if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                    SoundUtil.playSound(SoundUtil.getCaptureDuckAudio());
                }
            }
        });
        // Update PowerUp list
        this.powerUp.forEach(p -> {
            p.update(timeElapsed);
            if (p.isHit()) {
                this.duckPowerUp = NEXT_DUCKS_POWERUP;
                this.powerUpActive = Optional.of(p.getType());
            }
        });
        //Clean objects not necessary
        this.cleaner.clean(this.ducks, this.powerUp);
    }

    private void updateRoundNumber() {
        //Only for STORY MODE
        if (this.gameMode == GameMode.STORY_MODE && this.ducks.isEmpty() && this.spawner.getCurrentRound() != this.currentRound) {
            this.dog = new DogImpl();
            this.ducks.clear();
            this.powerUp.clear();
            this.match.incrementRound();
            this.currentRound = this.match.getCurrentRound();
            this.match.startRound();
            this.duckPowerUp = 0;
            this.endPowerUp();
            if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                SoundUtil.playSound(SoundUtil.getGameIntroAudio());
            }
        }
    }


    @Override
    public Pair<Integer, Integer> getRounds() {
        return this.match.getRounds();
    }

    @Override
    public int getInfo() {
        int info = 0;
        switch (this.gameMode) {
            case STORY_MODE:
                info = this.currentRound * this.match.getDifficulty().getLimitOfDifficulty();
                break;
            case SURVIVAL_MODE:
                info = this.match.getDifficulty().getLimitOfDifficulty();
                break;
            default:
                break;
        }
        return info;
    }

    private void activePowerUp(final PowerUpType powerUp) {
        switch (powerUp) {
            case INFINITE_AMMO:
                this.getCurrentMagazine().setBulletType(BulletType.INFINITE_BULLETS);
                this.endPowerUp();
                break;
            case SLOW_DOWN:
                this.ducks.stream()
                          .filter(d -> d.isAlive() 
                                  && !d.isDecelerated()
                                  && d.getPosition().getX() >= StandardDuck.COLLISION_X
                                  && d.getPosition().getX() <= (ModelImpl.GAME_WIDTH - StandardDuck.COLLISION_X) * 2)
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
                                  && d.getPosition().getX() <= ModelImpl.GAME_WIDTH - (StandardDuck.COLLISION_X * 3))
                          .forEach(d -> {
                              if (this.duckPowerUp > 0) {
                                  d.kill(); 
                                  this.duckPowerUp--;
                                  this.match.getMatchData().incrementScoreOf(d.getScore());
                              }
                          });
                break;
            default:
                break;
        }
        if (this.duckPowerUp == 0) {
            this.endPowerUp();
        }
    }


    @Override
    public Optional<PowerUpType> getPowerUpActive() {
        return this.powerUpActive;
    }

    @Override
    public void endPowerUp() {
        if (this.powerUpActive.isPresent()) {
            this.powerUpActive = Optional.empty();
        }
    }

    @Override
    public boolean isGameOver() {
        return this.match.isMatchOver() || this.spawner.isSpawnFinished();
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
        return this.match.getMatchData();
    }

    @Override
    public List<Duck> getDucks() {
        return this.ducks;
    }

    @Override
    public Dog getDog() {
        return this.dog;
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
        if (this.getCurrentMagazine().getBulletType().equals(BulletType.INFINITE_BULLETS)) {
            return true;
        }
        return this.getCurrentMagazine().getAmmo() > 0;
    }

    @Override
    public void recharge() {
        this.match.recharge();
    }

    @Override
    public Magazine getCurrentMagazine() {
        return this.match.getCurrentMagazine();
    }

    @Override
    public List<PowerUp> getPowerUps() {
        return this.powerUp;
    }

    @Override
    public GameMode getGameMode() {
        return this.gameMode;
    }
}

