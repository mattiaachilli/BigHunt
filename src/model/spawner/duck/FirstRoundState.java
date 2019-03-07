package model.spawner.duck;

import java.util.Optional;


import model.entities.Duck;

/**
 * 
 * First round state.
 *
 */

public class FirstRoundState extends AbstractDuckState {
   
    private static final int FIRST_WAVE = 12;
    
    public FirstRoundState() {
        super();
        super.resetDuckSpawned();
    }
    
    @Override
    public Duck spawnDuck() {
        super.incDuckSpawned();
        final Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity);
        if(super.getDuckSpawned() <= FIRST_WAVE) {
            return standardDuck;
        } else {
            return super.getDuckFactory().createYellowDuck(standardDuck);
        }
    }

    @Override
    public boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public Optional<DuckState> getNextState() {
       return Optional.of(new SecondRoundState());
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.FIRST_ROUND_DELAY.getSecondDelay();
    }
}
