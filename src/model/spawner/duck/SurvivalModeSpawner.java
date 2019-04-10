package model.spawner.duck;

import java.util.Optional;
import model.entities.Duck;

/**
 * 
 * This abstract class represents the survival mode spawner with incremental difficulty.
 *
 */

public class SurvivalModeSpawner extends AbstractSpawner {

    private static final int INIT_DELAY = DelayDuckSpawner.DOG_DELAY.getSecondDelay();
    private static final int UPDATE_INCREMENT_DUCK = 10000;
    private static int duckround = 2;
    private int timeElapsedUpdate;

    /**
     * Survival spawner constructor.
     */
    public SurvivalModeSpawner() {
        super(INIT_DELAY, new SurvivalState());
        this.timeElapsedUpdate = 0;
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
        return 1;
    }

    @Override
    public final void update(final int elapsed) { 
        this.timeElapsedUpdate += elapsed;
        if (timeElapsedUpdate >= UPDATE_INCREMENT_DUCK) {
            this.timeElapsedUpdate -= UPDATE_INCREMENT_DUCK;
            duckround++;
        }
    }

    @Override
    protected final int getDuckMax() {
        return duckround;
    }
}
