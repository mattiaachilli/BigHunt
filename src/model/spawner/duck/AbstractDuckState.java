package model.spawner.duck;

import java.util.Optional;

import model.entities.Duck;
import model.factories.DuckFactory;
import model.factories.DuckFactoryImpl;

/**
 * 
 * AbstractDuckState of the spawner
 *
 */

public abstract class AbstractDuckState implements DuckState {
    
    protected static final int NUM_DUCK_ROUND = 15; //Number ducks for round
    
    private int duckSpawned;
    private final DuckFactory duckFactory;
    
    public AbstractDuckState() {
        this.duckSpawned = 0;
        this.duckFactory = new DuckFactoryImpl();
    }
    
    public final DuckFactory getDuckFactory() {
        return this.duckFactory;
    }
    
    public final void resetDuckSpawned() {
        this.duckSpawned = 0;
    }
    
    public final int getDuckSpawned() {
        return this.duckSpawned;
    }
    
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
