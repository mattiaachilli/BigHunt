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
import model.data.Podium;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.DogStatus;
import model.entities.Duck;
import model.entities.Entity;

import model.entities.EntityStatus;
import model.spawner.duck.DuckSpawner;
import model.spawner.duck.StoryModeSpawner;
import model.spawner.duck.SurvivalModeSpawner;
import settings.GlobalDifficulty;



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
    public static final int GAME_WIDTH = 1366;
    /**
     * Game height.
     */
    public static final int GAME_HEIGHT = 1000;

    /**
     * Max of time elapsed before the update.
     */
    private static final int UPDATE_TIME = 6000;

    /**
     * All objects of the game world.
     */
    private final Dog dog;
    private final List<Duck> ducks;
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
        this.match = Optional.empty();
        this.difficulty = GlobalDifficulty.MEDIUM;
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.gameMode = gameMode;
        ducks.clear();
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
        return this.match.isPresent();
    }

    @Override
    public void endMatch() {
        this.match = Optional.empty();
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
        //return this.matchdata.get();
        return this.match.get().getMatchData();
    }

    @Override
    public List<Duck> getDucks() {
        return this.ducks;
    }

    /*
    @Override
    public List<Bullet> getBullets() {
        return null;
    }
    */
}


