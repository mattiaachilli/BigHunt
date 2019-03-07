package model.spawner.duck;

import java.util.Optional;
import model.entities.Duck;

/**
 * 
 * Survival round state.
 *
 */

public class SurvivalState extends AbstractDuckState {
    
    private static final int CHANGE_DIFFICULTY = 10; //Difficulty + 1
    private static final int BEGINNER_DIFFICULTY = 1;
    private static final int NORMAL_DIFFICULTY = 2;
    private static final int HARD_DIFFICULTY = 3;
    
    private int currentDifficulty;

    public SurvivalState() {
        super();
        this.currentDifficulty = 0;
    }
    
    @Override
    public Duck spawnDuck() {
        this.calculateDifficulty();
        super.incDuckSpawned();
        
        return this.computeSpawn();
    }
    
    private Duck computeSpawn() {
        Duck duck;
        final Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity);
        if(this.currentDifficulty < BEGINNER_DIFFICULTY) {
            duck = standardDuck;
        } else if(currentDifficulty >= BEGINNER_DIFFICULTY 
            && this.currentDifficulty < NORMAL_DIFFICULTY) {
            duck = super.getDuckFactory().createYellowDuck(duck);
        } else if(this.currentDifficulty >= NORMAL_DIFFICULTY 
            && this.currentDifficulty < HARD_DIFFICULTY) {
            duck = super.getDuckFactory().createOrangeDuck(duck);
        } else {
            duck = super.getDuckFactory().createPinkDuck(duck);
        }
        return duck;
    }
    
    private void calculateDifficulty() {
        this.currentDifficulty = super.getDuckSpawned() / CHANGE_DIFFICULTY;
    }

    @Override
    public boolean isStateEnded() {
        return false; //Infinite
    }

    @Override
    public Optional<DuckState> getNextState() {
        return Optional.empty();
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.SURVIVAL_DELAY.getSecondDelay();
    }

}
