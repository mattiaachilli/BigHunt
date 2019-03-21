package model;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import model.data.GlobalData;
import model.data.MatchData;
import model.data.MatchDataImpl;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.DogStatus;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.EntityStatus;
import model.entities.powerup.PowerUpType;
import model.spawner.duck.DuckSpawner;
import model.spawner.duck.StoryModeSpawner;
import model.spawner.duck.SurvivalModeSpawner;
import settings.SettingsImpl;
import utility.GameMode;

/**
 * 
 * Model implementation, contains all objects of the game.
 *
 */
public final class ModelImpl extends Canvas implements Model {
    /**
     * Game width.
     */
    public static final int GAME_WIDTH = SettingsImpl.getSettings().getSelectedResolution().getKey();
    /**
     * Game height.
     */
    public static final int GAME_HEIGHT = SettingsImpl.getSettings().getSelectedResolution().getValue();

    /**
     * All objects of the game world.
     */
    private final Dog dog;
    private final List<Duck> ducks;
    private Optional<MatchData> matchdata;
    private DuckSpawner spawner;
    private final GlobalData globaldata;
    private int lastRound;
    private GameMode gameMode;
    private DogStatus lastDogStatus;
    private int timeElapsed = 0;

    /**
     * Constructor of the model.
     */
    public ModelImpl(final GlobalData globaldata) {
        super();
        this.dog = new DogImpl();
        this.ducks = new ArrayList<>();
        this.globaldata = globaldata;
        this.initGame(GameMode.SURVIVAL_MODE);
        this.lastRound = this.spawner.getActualRound();
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.matchdata = Optional.of(new MatchDataImpl(gameMode));
        this.gameMode = gameMode;
        ducks.clear();
        switch (gameMode) {
            case STORY_MODE:
                this.spawner = new StoryModeSpawner();
                break;
            case SURVIVAL_MODE:
                this.spawner = new SurvivalModeSpawner();
                break;
            default:
                break;
        }
    }

    @Override
    public void update(final int timeElapsed) {
        //Update dog
        this.dog.update(timeElapsed);
        if (this.dog.getDogStatus() != this.lastDogStatus) {
            this.lastDogStatus = this.dog.getDogStatus();
        }

        //Update spawner when dog is in grass
        if (this.dog.isInGrass()) {
            this.timeElapsed += timeElapsed;
            spawner.update(timeElapsed);
            if (spawner.canSpawnDuck()) {
                Duck duck = spawner.spawnDuck().get();
                this.ducks.add(duck);
            }
        }
        //Update ducks
        for (Duck d: this.ducks) {
            if (this.timeElapsed >= 6000) {
                if (d.getStatus() == EntityStatus.ALIVE) {
                    this.timeElapsed -= 6000;
                    d.kill();
                    dog.setDogStatus(DogStatus.HAPPY);
                }
            }
            if(d.getStatus() == EntityStatus.DEAD && d.hasPowerUp()) {
                d.getPowerUp().get().update(timeElapsed);
            }
            /*if (d.canFlyAway()) {
                d.computeFlyAway();
                dog.setDogStatus(DogStatus.LAUGH);
            }
            */
            d.update(timeElapsed);
        }
        //Only for STORY MODE
        if (this.gameMode == GameMode.STORY_MODE) {
            if (this.spawner.getActualRound() != this.lastRound) {
                this.ducks.clear();
                this.lastRound = this.spawner.getActualRound();
            }
        }
    }

    @Override
    public boolean isGameOver() {
        return this.matchdata.isPresent();
    }

    @Override
    public boolean isHighScore() {
        return this.globaldata.isHighScore(this.matchdata.get().getGlobalScore());
    }

    @Override
    public void endMatch() {
        this.matchdata = Optional.empty();
    }

    @Override
    public void setAimX() {

    }

    @Override
    public void setAimY() {

    }

    @Override
    public List<Entity> getEntities() {
        final List<Entity> listEntity = new ArrayList<>();
        listEntity.addAll(ducks);
        listEntity.add(dog);
        return listEntity;
    }

    @Override
    public MatchData getMatchData() {
        return this.matchdata.get();
    }

    @Override
    public GlobalData getGlobalData() {
        return this.globaldata;
    }

    /*
    @Override
    public List<Bullet> getBullets() {
        return null;
    }
    */
}
