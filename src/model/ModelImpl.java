package model;

import java.util.ArrayList;
import java.util.List;

import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.Duck;
import model.entities.Entity;

public final class ModelImpl implements Model {
    
    private final Dog dog;
    private final List<Duck> ducks;
    
    public ModelImpl() {
	super();
	this.ducks = new ArrayList<>();
	//this.dog = new DogImpl();
    }

    @Override
    public void initGame() {


    }

    @Override
    public void update(int timeElapsed) {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean isGameOver() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isHighScore() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void endMatch() {
	// TODO Auto-generated method stub

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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public GlobalData getGlobalData() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Bullet> getBullets() {
	// TODO Auto-generated method stub
	return null;
    }
}
