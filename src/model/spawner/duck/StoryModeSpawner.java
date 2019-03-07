package model.spawner.duck;

import java.util.Optional;
import model.entities.Duck;

/**
 * 
 * This abstract class represents the story mode spawner in rounds.
 *
 */

public class StoryModeSpawner extends AbstractSpawner {
    
    private static final int INIT_DELAY = DelayDuckSpawner.FIRST_ROUND_DELAY.getSecondDelay();
    private static final int ROUND_DELAY = DelayDuckSpawner.ROUND_DELAY.getSecondDelay();
    
    public StoryModeSpawner() {
        super(INIT_DELAY, new FirstRoundState());
    }

    @Override
    public Optional<Duck> spawnDuck() {
        super.resetTimeElapsed();
        final Optional<Duck> duck;
        
        if(super.getCurrentDuckState().isPresent()) {
            final DuckState duckState = super.getCurrentDuckState().get();
            duck = Optional.of(duckState.spawnDuck());
            super.addDuck(duck.get());
            
            super.setSpawnDelay(duckState.getSpawnDelay());
            super.incDuckSpawned();
            
            if(duckState.isStateEnded()) {
                duckState.resetDuckSpawned();
                super.setState(duckState.getNextState());
                super.clearDucksSpawned();
                super.setSpawnDelay(ROUND_DELAY);
            } 
        } else {
            duck = Optional.empty();
        }
        
        return duck;
    }

    @Override
    public boolean isSpawnFinished() {
        return !super.getCurrentDuckState().isPresent();
    }
}
