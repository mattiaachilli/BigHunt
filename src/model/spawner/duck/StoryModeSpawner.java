package model.spawner.duck;

import java.util.Optional;
import model.entities.Duck;
import model.utilities.RoundUtility;

/**
 * 
 * This abstract class represents the story mode spawner in rounds.
 *
 */
public class StoryModeSpawner extends AbstractSpawner {

    private static final int INIT_DELAY = DelayDuckSpawner.FIRST_ROUND_DELAY.getSecondDelay();
    private static final int ROUND_DELAY = DelayDuckSpawner.ROUND_DELAY.getSecondDelay();
    private int round;

    /**
     * Story spawner constructor.
     */
    public StoryModeSpawner() {
        super(INIT_DELAY, new FirstRoundState());
        this.round = 1;
    }

    private void incRound() {
        if (this.round < RoundUtility.FIVE_ROUNDS.getRounds()) {
            this.round++;
        }
    }

    @Override
    public final Optional<Duck> spawnDuck() {
        super.resetTimeElapsed();
        final Optional<Duck> duck;

        if (super.getCurrentDuckState().isPresent()) {
            final DuckState duckState = super.getCurrentDuckState().get();
            duck = Optional.of(duckState.spawnDuck());
            super.addDuck(duck.get());

            super.setSpawnDelay(duckState.getSpawnDelay());
            super.incDuckSpawned();

            if (duckState.isStateEnded()) {
                duckState.resetDuckSpawned();
                this.incRound();
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
    public final boolean isSpawnFinished() {
        return !super.getCurrentDuckState().isPresent();
    }

    @Override
    public final int getActualRound() {
        return this.round;
    }
}
