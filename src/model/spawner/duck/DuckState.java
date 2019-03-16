package model.spawner.duck;


import java.util.Optional;

import model.entities.Duck;

/**
 * 
 * Interface for the spawner duck state.
 *
 */
public interface DuckState {

    /**
     * 
     * Method that spawn ducks.
     * 
     * @return the ducks spawned.
     */
    Duck spawnDuck();

    /**
     * Reset the number of duck spawned.
     */
    void resetDuckSpawned();

    /**
     * 
     * @return true if all this state's ducks have spawned.
     */
    boolean isStateEnded();

    /**
     * 
     * @return the next state of the spawner.
     */
    Optional<DuckState> getNextState();

    /**
     * @return spawn delay.
     */
    int getSpawnDelay();
}
