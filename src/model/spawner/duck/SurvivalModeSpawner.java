package model.spawner.duck;

import java.util.Optional;
import model.entities.Duck;

/**
 * 
 * This abstract class represents the survival mode spawner with incremental difficulty.
 *
 */

public class SurvivalModeSpawner extends AbstractSpawner {

    private static final int INIT_DELAY = DelayDuckSpawner.SURVIVAL_DELAY.getSecondDelay();

    /**
     * Survival spawner constructor.
     */
    public SurvivalModeSpawner() {
        super(INIT_DELAY, new SurvivalState());
    }

    @Override
    public final Optional<Duck> spawnDuck() {
        super.resetTimeElapsed();
        final DuckState duckState = super.getCurrentDuckState().get();
        final Duck duck = duckState.spawnDuck();

        super.addDuck(duck);
        super.setSpawnDelay(duckState.getSpawnDelay());
        super.incDuckSpawned();

        return Optional.of(duck);
    }

    @Override
    public final boolean isSpawnFinished() {
        return false;
    }

    @Override
    public final int getActualRound() {
        throw new UnsupportedOperationException();
    }
}
