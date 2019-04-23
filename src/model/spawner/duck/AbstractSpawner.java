package model.spawner.duck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.entities.Duck;

/**
 * 
 * AbstractSpwanwer: Survival and story.
 *
 */

public abstract class AbstractSpawner implements DuckSpawner {
    private int spawnDelay;
    private int ducksSpawned; //Total in all round
    private int timeElapsed;

    private Optional<DuckState> actualState;
    private final List<Duck> listDucksSpawned; //List of ducks spawned

    /**
     * Constructor of the spawner.
     * 
     * @param initDelay
     *          initial delay before spawn.
     * @param initialState
     *          initial duck state.
     */
    public AbstractSpawner(final int initDelay, final DuckState initialState) {
        this.spawnDelay = initDelay;
        this.ducksSpawned = 0;
        this.timeElapsed = 0;
        this.actualState = Optional.of(initialState);
        this.listDucksSpawned = new ArrayList<>();
    }

    /**
     * 
     * @param elapsed
     *            time elapsed from the previous update.
     */
    public void update(final int elapsed) {
        this.timeElapsed += elapsed;
    }

    /**
     * Resets the time elapsed.
     */
    protected void resetTimeElapsed() {
        this.timeElapsed = 0;
    }

    /**
     * Gets time elapsed.
     * 
     * @return timeElapsed.
     */
    protected int getTimeElapsed() {
        return this.timeElapsed;
    }

    /**
     * Clears the list of ducks spawned in order to enter the next round.
     */
    protected void clearDucksSpawned() {
        if (this instanceof StoryModeSpawner) {
            listDucksSpawned.clear();
        }
    }

    /**
     * Gets the list of ducks spawned.
     * @return the list of ducks spawned
     */
    protected List<Duck> getListDuckSpawned() {
        return this.listDucksSpawned;
    }

    /**
     * 
     * @param duck
     *          duck to add.
     */
    protected void addDuck(final Duck duck) {
        this.listDucksSpawned.add(Objects.requireNonNull(duck));
    }

    private boolean checkNumberDuck() {
        int numDuckAlive = 0;
        for (final Duck duck: this.listDucksSpawned) {
            if (duck.isAlive()) {
                numDuckAlive++;
            }
        }
        return numDuckAlive < this.getDuckMax();
    }

    @Override
    public final boolean canSpawnDuck() {
        return this.checkNumberDuck() 
            && this.timeElapsed >= this.spawnDelay 
            && this.actualState.isPresent();
    }

    @Override
    public final void setSpawnDelay(final int delay) {
        this.spawnDelay = delay;
    }

    @Override
    public final int getNumberDuckSpawned() {
        return this.ducksSpawned;
    }

    /**
     * Increments the duck spawned.
     */
    protected void incDuckSpawned() {
        this.ducksSpawned++;
    }

    @Override
    public final Optional<DuckState> getCurrentDuckState() {
        return this.actualState;
    }

    @Override
    public final void setState(final Optional<DuckState> state) {
        this.actualState = state;
    }

    /**
     * Gets the maximum number of ducks to spawn at a time.
     * 
     * @return max number of duck to spawn
     */
    protected abstract int getDuckMax();

    @Override
    public abstract Optional<Duck> spawnDuck();

    @Override
    public abstract boolean isSpawnFinished();

    @Override
    public abstract int getCurrentRound();
}
