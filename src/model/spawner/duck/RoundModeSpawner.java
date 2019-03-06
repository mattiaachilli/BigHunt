package model.spawner.duck;

import java.util.Optional;
import model.entities.Duck;

public class RoundModeSpawner implements DuckSpawner {
    
    private static final int INIT_DELAY = DelayDuckSpawner.FIRST_ROUND_DELAY.getSecondDelay();
    private static final int ROUND_DELAY = DelayDuckSpawner.ROUND_DELAY.getSecondDelay();
    
    private int spawnDelay;
    private int ducksSpawned;
    private int timeElapsed;
    private int lastTimeElapsedRound;
    private boolean canSpawn;
    
    private Optional<DuckState> actualState;
    
    public RoundModeSpawner() {
        this.spawnDelay = INIT_DELAY;
        this.spawnDelay = 0;
        this.ducksSpawned = 0;
        this.timeElapsed = 0;
        this.lastTimeElapsedRound = 0;
        this.canSpawn = true;
        this.actualState = Optional.of(new FirstRoundState());
    }

    @Override
    public void update(final int elapsed) {
        this.timeElapsed += elapsed;
    }

    @Override
    public boolean canSpawnDuck() {
        return this.canSpawn && this.actualState.isPresent();
    }

    @Override
    public void setSpawnDelay(final int delay) {
        this.spawnDelay = delay;
    }

    @Override
    public Optional<Duck> spawnDuck() {
        this.timeElapsed = 0;
        this.lastTimeElapsedRound = 0;
        
        final Optional<Duck> duckSpawned;
        if(this.actualState.isPresent()) {
            final DuckState duckState = this.actualState.get();
            duckSpawned = Optional.of(duckState.spawnDuck());
            
            this.setSpawnDelay(duckState.getSpawnDelay());
            this.ducksSpawned += 1;
            
            if(duckState.isStateEnded()) {
                this.setState(duckState.getNextState());
            } 
        } else {
            duckSpawned = Optional.empty();
        }
        
        return duckSpawned;
    }

    @Override
    public int getNumberDuckSpawned() {
        return this.ducksSpawned;
    }

    @Override
    public boolean isSpawnFinished() {
        return !this.actualState.isPresent();
    }

    @Override
    public Optional<DuckState> getCurrentDuckState() {
        return this.actualState;
    }

    @Override
    public void setState(final Optional<DuckState> state) {
        this.actualState = state;
    }

}
