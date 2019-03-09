package model.spawner.duck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.entities.Duck;

public abstract class AbstractSpawner implements DuckSpawner {
    
    private static final int NUMBER_MAX_DUCK = 3; //Max number of ducks at the same time
    
    private int spawnDelay;
    private int ducksSpawned; //Total in all round
    private int timeElapsed; 
    
    private Optional<DuckState> actualState;
    private final List<Duck> listDucksSpawned; //List of ducks spawned
    
    public AbstractSpawner(final int initDelay, final DuckState initialState) {
        this.spawnDelay = initDelay;
        this.ducksSpawned = 0;
        this.timeElapsed = 0;
        this.actualState = Optional.of(initialState);
        this.listDucksSpawned = new ArrayList<>();
    }
    
    @Override
    public void update(int elapsed) {
        this.timeElapsed += elapsed;
    }
    
    protected void resetTimeElapsed() {
        this.timeElapsed = 0;
    }
    
    protected void clearDucksSpawned() {
        if(this instanceof StoryModeSpawner) {
            listDucksSpawned.clear();
        }
    }
    
    protected void addDuck(final Duck duck) {
        this.listDucksSpawned.add(Objects.requireNonNull(duck));
    }

    private boolean checkNumberDuck() {
        int numDuckAlive = 0;
        for(Duck duck: listDucksSpawned) {
            if(duck.isAlive()) {
                numDuckAlive++;
            }
        }
        return numDuckAlive <= NUMBER_MAX_DUCK;
    }

    @Override
    public boolean canSpawnDuck() {
        return this.checkNumberDuck() 
            && this.timeElapsed >= this.spawnDelay 
            && this.actualState.isPresent();
    }

    @Override
    public void setSpawnDelay(int delay) {
        this.spawnDelay = delay;
    }
    
    @Override
    public int getNumberDuckSpawned() {
        return this.ducksSpawned;
    }
    
    protected void incDuckSpawned() {
        this.ducksSpawned++;
    }
    
    @Override
    public Optional<DuckState> getCurrentDuckState() {
        return this.actualState;
    }
    
    @Override
    public void setState(Optional<DuckState> state) {
        this.actualState = state;
    }

    @Override
    public abstract Optional<Duck> spawnDuck();
    
    @Override
    public abstract boolean isSpawnFinished();
}
