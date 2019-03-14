package model.spawner.duck;

import java.util.Optional;

import javafx.scene.shape.Rectangle;
import model.ModelImpl;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;

/**
 * 
 * Survival round state.
 *
 */

public class SurvivalState extends AbstractDuckState {
    
    private static final int CHANGE_DIFFICULTY = 10; //Difficulty + 1
    private static final int BEGINNER_DIFFICULTY = 1;
    private static final int NORMAL_DIFFICULTY = 2;
    private static final int HARD_DIFFICULTY = 3;
    
    private int currentDifficulty;

    public SurvivalState() {
        super();
        this.currentDifficulty = 0;
    }
    
    @Override
    public Duck spawnDuck() {
        this.calculateDifficulty();
        super.incDuckSpawned();
        
        return this.computeSpawn();
    }
    
    private Duck computeSpawn() {
        Duck duck;
        final DuckDirection direction = SpawnSide.getSpawnSide(); //Init direction
        int posX = SpawnSide.initPosX(direction);
        final Velocity velocity = SpawnSide.getVelocity(direction, DuckProperty.STANDARD_DUCK);
        final Duck standardDuck = super.getDuckFactory()
                                       .createStandardDuck(
                                       new Rectangle(posX, ModelImpl.GAME_HEIGHT / 2 - SPAWN_Y, StandardDuck.HEIGHT_DUCK, StandardDuck.HEIGHT_DUCK)
                                       ,velocity 
                                       ,direction);
        if(this.currentDifficulty < BEGINNER_DIFFICULTY) {
            duck = standardDuck;
        } else if(currentDifficulty >= BEGINNER_DIFFICULTY 
            && this.currentDifficulty < NORMAL_DIFFICULTY) {
            standardDuck.setVelocity(SpawnSide.getVelocity(direction, DuckProperty.YELLOW_DUCK));
            duck = super.getDuckFactory().createYellowDuck(standardDuck);
        } else if(this.currentDifficulty >= NORMAL_DIFFICULTY 
            && this.currentDifficulty < HARD_DIFFICULTY) {
            standardDuck.setVelocity(SpawnSide.getVelocity(direction, DuckProperty.ORANGE_DUCK));
            duck = super.getDuckFactory().createOrangeDuck(standardDuck);
        } else {
            standardDuck.setVelocity(SpawnSide.getVelocity(direction, DuckProperty.PINK_DUCK));
            duck = super.getDuckFactory().createPinkDuck(standardDuck);
        }
        return duck;
    }
    
    private void calculateDifficulty() {
        this.currentDifficulty = super.getDuckSpawned() / CHANGE_DIFFICULTY;
    }

    @Override
    public boolean isStateEnded() {
        return false; //Infinite
    }

    @Override
    public Optional<DuckState> getNextState() {
        return Optional.empty();
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.SURVIVAL_DELAY.getSecondDelay();
    }
}
