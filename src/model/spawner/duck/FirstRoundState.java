package model.spawner.duck;

import java.util.Optional;

import model.entities.Duck;

public class FirstRoundState extends AbstractDuckState {
   
    private static final int FIRST_WAVE = 12;
    
    public FirstRoundState() {
        super();
        super.resetDuckSpawned();
    }
    
    @Override
    public Duck spawnDuck() {
        super.incDuckSpawned();
        if(super.getDuckSpawned() < FIRST_WAVE) {
            //return super.getDuckFactory().createStandardDuck(shape, velocity);
        } else {
            //final Duck duck = super.getDuckFactory().createStandardDuck(new Circle, velocity);
            //return super.getDuckFactory().createYellowDuck(duck);
        }
    }

    @Override
    public boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public Optional<DuckState> getNextState() {
       // return Optional.of(value);
        return Optional.empty();
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.FIRST_ROUND_DELAY.getSecondDelay();
    }

}
