package model.spawner.duck;

import java.util.Optional;

import model.decorator.OrangeDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.entities.StandardDuck;

public class SecondRoundState extends AbstractDuckState {

    private static final int FIRST_WAVE = 10;
    private static final int SECOND_WAVE = 3;
    
    public SecondRoundState() {
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
        } else { //Last wave of two ducks
            return super.getDuckFactory().createOrangeDuck(standardDuck);
        }
    }

    @Override
    public boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public Optional<DuckState> getNextState() {
        return Optional.of(new ThirdRoundState());
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.SECOND_ROUND_DELAY.getSecondDelay();
    }
}
