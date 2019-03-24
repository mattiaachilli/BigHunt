package model;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import controller.matches.AbstractMatch;
import controller.matches.GameMode;
import controller.matches.StoryMatch;
import controller.matches.SurvivalMatch;
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
    private int lastRound;
    private GameMode gameMode;
    private GlobalDifficulty difficulty;
    private DogStatus lastDogStatus;
    private int timeElapsed = 0;

    /**
     * Constructor of the model.
     */
    public ModelImpl() {
        super();
        this.dog = new DogImpl();
        this.ducks = new ArrayList<>();
        this.powerUp = new ArrayList<>();
        this.match = Optional.empty();
        this.difficulty = GlobalDifficulty.MEDIUM;
        this.initGame(GameMode.SURVIVAL_MODE);
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
        // Update dog
        this.dog.update(timeElapsed);
        if (this.dog.getDogStatus() != this.lastDogStatus) {
            this.lastDogStatus = this.dog.getDogStatus();
        }

        // Update spawner when dog is in grass
        if (this.dog.isInGrass()) {
            this.timeElapsed += timeElapsed;
            spawner.update(timeElapsed);
            if (spawner.canSpawnDuck()) {
                final Duck duck = spawner.spawnDuck().get();
                this.ducks.add(duck);
                if (duck.hasPowerUp()) {
                    this.powerUp.add(duck.getPowerUp().get());
                }
            }
        }
        // Update ducks

        for (Duck d : this.ducks) {
            if (this.timeElapsed >= UPDATE_TIME) {
                if (d.getStatus() == EntityStatus.ALIVE) {
                    this.timeElapsed -= UPDATE_TIME;
                    d.kill();
                    dog.setDogStatus(DogStatus.HAPPY);
                }
            }
            if (d.canFlyAway()) {
                d.computeFlyAway();
                dog.setDogStatus(DogStatus.LAUGH);
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
        this.deleteUnnecessaryPowerUp();
        // Only for STORY MODE
        if (this.gameMode == GameMode.STORY_MODE) {
            if (this.spawner.getActualRound() != this.lastRound) {
                this.ducks.clear();
                this.lastRound = this.spawner.getActualRound();
            }
        }
    }

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
        }
    }

    @Override
    public boolean isGameOver() {
        return this.match.isPresent();
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

    }

    @Override
    public void setAimY() {
        // TODO Auto-generated method stub

    }

    /*
     * @Override public List<Bullet> getBullets() { return null; }
     */
}
