package model.spawner.duck;

import java.util.List;
import java.util.Optional;

import model.entities.Duck;
import model.matches.MaxOfRounds;

/**
 * 
 * This abstract class represents the story mode spawner in rounds.
 *
 */
public class StoryModeSpawner extends AbstractSpawner {

    private static final int INIT_DELAY = DelayDuckSpawner.DOG_DELAY.getSecondDelay();
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
        if (this.round < MaxOfRounds.FIVE_ROUNDS.getRounds()) {
            this.round++;
        }
    }

    @Override
    public final Optional<Duck> spawnDuck() {
        super.resetTimeElapsed();
        Optional<Duck> duck = Optional.empty();

        if (super.getCurrentDuckState().isPresent()) {
            final DuckState duckState = super.getCurrentDuckState().get();
            if (!duckState.isStateEnded()) {
                duck = Optional.of(duckState.spawnDuck());
                super.addDuck(duck.get());
                super.setSpawnDelay(duckState.getSpawnDelay());
                super.incDuckSpawned();
            } else if (duckState.isStateEnded() && !this.checkDucksAlive()) {
                duckState.resetDuckSpawned();
                super.clearDucksSpawned();
                this.incRound();
                super.setState(duckState.getNextState());
                super.setSpawnDelay(ROUND_DELAY);
            }
        } else {
            duck = Optional.empty();
        }

        return duck;
    }

    private boolean checkDucksAlive() {
        final List<Duck> ducksSpawned = super.getListDuckSpawned();
        for (final Duck duck: ducksSpawned) {
            if (duck.isAlive()) {
                return true;
            }
        }
        return false;
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
