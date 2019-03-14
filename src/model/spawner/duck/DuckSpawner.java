package model.spawner.duck;

import java.util.Optional;

import model.entities.Duck;

/**
 * 
 * Interface for the duck spawner.
 *
 */
public interface DuckSpawner {

    /**
     * 
     * @param elapsed
     *            time elapsed from the previous update.
     */
    void update(int elapsed);
    
    /**
     * Check if duck can be spawned.
     * 
     * @return true if duck can be spawned.
     */
    boolean canSpawnDuck();
    
    /**
     * 
     * @param delay
     *            new spawn delay.
     */
    void setSpawnDelay(int delay);
    
    /**
     * Spawn a duck
     * 
     * @return Optional of duck.
     */
    Optional<Duck> spawnDuck();
    
    /**
     * 
     * @return the number of duck spawned.
     */
    int getNumberDuckSpawned();
    
    /**
     * Check if all ducks are spawned.
     * 
     * @return true if all ducks are already spawned.
     */
    boolean isSpawnFinished();

    /**
     * Get the current duck state.
     * 
     * @return the actual state of the spawner.
     */
    Optional<DuckState> getCurrentDuckState();

    /**
     * 
     * @param state
     *            to set.
     */
    void setState(Optional<DuckState> state);
    
    /**
     * Get actual round.
     * 
     * @return the actual round.
     */
    int getActualRound();
}
