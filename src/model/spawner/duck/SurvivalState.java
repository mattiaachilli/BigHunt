package model.spawner.duck;

import java.util.Optional;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
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

    /**
     * Survival state constructor.
     */
    public SurvivalState() {
        super();
        this.currentDifficulty = 0;
    }

    @Override
    public final Duck spawnDuck() {
        this.calculateDifficulty();
        super.incDuckSpawned();
        return this.computeSpawn();
    }

    private Duck computeSpawn() {
        Duck duck;
        final DuckDirection direction = SpawnSideUtil.getSpawnSide(); //Init direction
        final int posX = SpawnSideUtil.initPosX(direction);
        final int posY = SpawnSideUtil.getRandomPosY();
        final Shape shape = new Rectangle(posX, posY, StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK);
        Velocity velocity = SpawnSideUtil.getVelocity(direction, StandardDuck.VELOCITY);
        Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
        if (this.currentDifficulty < BEGINNER_DIFFICULTY) {
            duck = standardDuck;
        } else if (currentDifficulty >= BEGINNER_DIFFICULTY
                    && this.currentDifficulty < NORMAL_DIFFICULTY) {
            velocity = SpawnSideUtil.getVelocity(direction, YellowDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            duck = super.getDuckFactory().createYellowDuck(standardDuck);
        } else if (this.currentDifficulty >= NORMAL_DIFFICULTY 
            && this.currentDifficulty < HARD_DIFFICULTY) {
            velocity = SpawnSideUtil.getVelocity(direction, OrangeDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            duck = super.getDuckFactory().createOrangeDuck(standardDuck);
        } else {
            velocity = SpawnSideUtil.getVelocity(direction, PinkDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            duck = super.getDuckFactory().createPinkDuck(standardDuck);
        }
        return duck;
    }

    private void calculateDifficulty() {
        this.currentDifficulty = super.getDuckSpawned() / CHANGE_DIFFICULTY;
    }

    @Override
    public final boolean isStateEnded() {
        return false; //Infinite
    }

    @Override
    public final Optional<DuckState> getNextState() {
        return Optional.empty();
    }

    @Override
    public final int getSpawnDelay() {
        return DelayDuckSpawner.SURVIVAL_DELAY.getSecondDelay();
    }
}
