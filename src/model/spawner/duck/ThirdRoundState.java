package model.spawner.duck;

import java.util.Optional;

import model.entities.Duck;

/**
 * 
 * Third round state.
 *
 */

public class ThirdRoundState extends AbstractDuckState{

    private static final int FIRST_WAVE = 8;
    private static final int SECOND_WAVE = 3;
    
    public ThirdRoundState() {
        super();
        super.resetDuckSpawned();
    }
    @Override
    public Duck spawnDuck() {
        super.incDuckSpawned();
        final Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity);
        if(super.getDuckSpawned() <= FIRST_WAVE) {
            return standardDuck;
        } else if(super.getDuckSpawned() > FIRST_WAVE && 
            super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE) {
            return super.getDuckFactory().createYellowDuck(standardDuck);
        } else { //Last wave of four ducks
            return super.getDuckFactory().createOrangeDuck(standardDuck);
        }
    }

    @Override
    public boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public Optional<DuckState> getNextState() {
        return Optional.of(new FourthRoundState());
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.THIRD_ROUND_DELAY.getSecondDelay();
    }
}
