package model.spawner.duck;

import java.util.Optional;

import model.entities.Duck;

public class FourthRoundState extends AbstractDuckState {
    
    private static final int FIRST_WAVE = 5;
    private static final int SECOND_WAVE = 5;
    private static final int THIRD_WAVE = 3;
    
    public FourthRoundState() {
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
        } else if(super.getDuckSpawned() > FIRST_WAVE + SECOND_WAVE && 
            super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE + THIRD_WAVE) { //Last wave of four ducks
            return super.getDuckFactory().createOrangeDuck(standardDuck);
        } else {
            return super.getDuckFactory().createPinkDuck(standardDuck);
        }
    }

    @Override
    public boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public Optional<DuckState> getNextState() {
        return Optional.of(FifthRoundState());
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.FOURTH_ROUND_DELAY.getSecondDelay();
    }
}
