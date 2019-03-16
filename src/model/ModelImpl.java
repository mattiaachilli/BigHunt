package model;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javafx.scene.shape.Rectangle;
import model.data.GlobalData;
import model.data.MatchData;
import model.data.MatchDataImpl;
import model.decorator.OrangeDuck;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.VelocityImpl;
import model.spawner.duck.DuckSpawner;
import model.spawner.duck.StoryModeSpawner;
import model.spawner.duck.SurvivalModeSpawner;
import utility.GameMode;

public final class ModelImpl extends Canvas implements Model {
    
    
    /**
     * Game width
     */
    public static final int GAME_WIDTH = 1366;
    /**
     * Game height
     */
    public static final int GAME_HEIGHT = 1000;
    
    /**
     * All objects of the game world.
     */
    private final Dog dog;
    private final List<Duck> ducks;
    private Optional<MatchData> matchdata;
    private DuckSpawner spawner;
    private final GlobalData globaldata;
    private int lastRound;
    private String lastDogStatus;

    /**
     * 

     */
    public ModelImpl(final GlobalData globaldata) {
        super();
        this.dog = new DogImpl();
        this.ducks = new ArrayList<>();
        this.globaldata = globaldata;
        this.initGame(GameMode.STORY_MODE);
        this.lastRound = this.spawner.getActualRound();
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.matchdata = Optional.of(new MatchDataImpl(gameMode));

        ducks.clear();
        /*
        switch(gameMode) {
            case STORY_MODE:
                this.spawner = new StoryModeSpawner();
                break;
            case SURVIVAL_MODE:
                this.spawner = new SurvivalModeSpawner();
                break;
        }
        */
        this.spawner = new StoryModeSpawner();

    }

    @Override
    public void update(final int timeElapsed) {

        this.dog.update(timeElapsed);
        if (this.dog.getDogStatus().toString() != this.lastDogStatus) {
            this.lastDogStatus = this.dog.getDogStatus().toString();
            System.out.println(dog.getDogStatus());
        }

        //System.out.println(dog.getDogStatus());
        /*
        spawner.update(timeElapsed);
        if(spawner.canSpawnDuck()) {
            this.ducks.add(spawner.spawnDuck().get());
        }
        for (Duck d: this.ducks) {
	    if(d.canFlyAway()) {
	        d.computeFlyAway();
	    }
	    d.update(timeElapsed);
	}
        if(this.spawner.getActualRound() != this.lastRound) {
            this.ducks.clear();
            this.lastRound = this.spawner.getActualRound();
        }
        */
    }

    @Override
    public boolean isGameOver() {
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub

    }

    @Override
    public void setAimY() {
	// TODO Auto-generated method stub

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
