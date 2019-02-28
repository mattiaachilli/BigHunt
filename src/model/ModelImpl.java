package model;

import java.util.List;

import model.entities.Dog;
import model.entities.DogImpl;

public class ModelImpl implements Model {
    
    private Dog dog;
    
    public ModelImpl() {
	super();
	//Inizializzare tutti i campi
    }

    @Override
    public void initGame() {
	//this.dog = new DogImpl();

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
	// TODO Auto-generated method stub
	return null;
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
