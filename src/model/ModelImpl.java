package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.data.GlobalData;
import model.data.MatchData;
import model.data.GlobalDataImpl;
import model.data.MatchDataImpl;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.Duck;
import model.entities.Entity;
import utility.GameMode;

public final class ModelImpl implements Model {
    
    private final Dog dog;
    private final List<Duck> ducks;
    private Optional<MatchData> matchdata;
    private final GlobalData globaldata;
    
    public ModelImpl(final GlobalData globaldata) {
	super();
	this.ducks = new ArrayList<>();
	this.dog = new DogImpl(null, null);
	this.globaldata = globaldata;
    }

    @Override
    public void initGame(final GameMode gameMode) {
        this.matchdata = Optional.of(new MatchDataImpl(gameMode));
        
	ducks.clear();
    }

    @Override
    public void update(int timeElapsed) {
	// TODO Auto-generated method stub

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
	listEntity.add(dog);
	listEntity.addAll(ducks);
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

    @Override
    public List<Bullet> getBullets() {
	return null;
    }
}
