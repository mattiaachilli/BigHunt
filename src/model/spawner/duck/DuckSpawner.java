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
     * Checks if a duck can be spawned.
     * 
     * @return true if ducks can be spawned.
     */
    boolean canSpawnDuck();

    /**
     * 
     * @param delay
     *            new spawn delay.
     */
    void setSpawnDelay(int delay);

    /**
     * Spawns a duck.
     * 
     * @return Optional of duck.
     */
    Optional<Duck> spawnDuck();

    /**
     * 
     * @return the number of spawned ducks.
     */
    int getNumberDuckSpawned();

    /**
     * Checks if all ducks are spawned.
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
     * Gets the current round.
     * 
     * @return the actual round.
     */
    int getCurrentRound();
}
