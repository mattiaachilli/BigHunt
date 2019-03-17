package model.spawner.duck;

import java.util.Optional;

import model.entities.Duck;
import model.factories.DuckFactory;
import model.factories.DuckFactoryImpl;

/**
 * 
 * AbstractDuckState of the spawner.
 *
 */

public abstract class AbstractDuckState implements DuckState {

   /**
     * NUM_DUCK_ROUND = NUMBER OF THE DUCK TO SPAWN IN A ROUND.
     */
    protected static final int NUM_DUCK_ROUND = 15; //Number ducks for round

    private int duckSpawned;
    private final DuckFactory duckFactory;

    /**
     * Constructor, set the actual round and the duckFactory.
     */
    public AbstractDuckState() {
        this.duckSpawned = 0;
        this.duckFactory = new DuckFactoryImpl();
    }

    /**
     * 
     * @return the duckFactory
     */
    public final DuckFactory getDuckFactory() {
        return this.duckFactory;
    }

    /**
     * Reset the number of duck spawned in each round.
     */
    public final void resetDuckSpawned() {
        this.duckSpawned = 0;
    }

    /**
     * 
     * @return the number of duck spawned
     */
    public final int getDuckSpawned() {
        return this.duckSpawned;
    }

    /**
     * Increment the number of duck spawned when we spawn(create) it.
     */
    public final void incDuckSpawned() {
        this.duckSpawned++;
    }

    @Override
    public abstract Duck spawnDuck();

    @Override
    public abstract boolean isStateEnded();

    @Override
    public abstract Optional<DuckState> getNextState();

    @Override
    public abstract int getSpawnDelay();
}
