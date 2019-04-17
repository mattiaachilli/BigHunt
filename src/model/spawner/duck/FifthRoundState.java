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
 * Fifth round state.
 *
 */

public class FifthRoundState extends AbstractDuckState {

    private static final int FIRST_WAVE = 1;
    private static final int SECOND_WAVE = 3;
    private static final int THIRD_WAVE = 5;

    /**
     * Fifth round constructor.
     */
    public FifthRoundState() {
        super();
        super.resetDuckSpawned();
    }

    @Override
    public final Duck spawnDuck() {
        super.incDuckSpawned();
        final DuckDirection direction = SpawnSideUtil.getSpawnSide(); //Init direction
        final int posX = SpawnSideUtil.initPosX(direction);
        final int posY = SpawnSideUtil.getRandomPosY();
        final Shape shape = new Rectangle(posX, posY, StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK);
        Velocity velocity = SpawnSideUtil.getVelocity(direction, StandardDuck.VELOCITY);
        Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
        if (super.getDuckSpawned() <= FIRST_WAVE) {
            return standardDuck;
        } else if (super.getDuckSpawned() > FIRST_WAVE 
                   && 
                   super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE) {
            velocity = SpawnSideUtil.getVelocity(direction, YellowDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            return super.getDuckFactory().createYellowDuck(standardDuck);
        } else if (super.getDuckSpawned() > FIRST_WAVE + SECOND_WAVE 
                   && 
                   super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE + THIRD_WAVE) {
            velocity = SpawnSideUtil.getVelocity(direction, OrangeDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            return super.getDuckFactory().createOrangeDuck(standardDuck);
        } else {
            velocity = SpawnSideUtil.getVelocity(direction, PinkDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            return super.getDuckFactory().createPinkDuck(standardDuck);
        }
    }

    @Override
    public final boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public final Optional<DuckState> getNextState() {
        return Optional.empty();
    }

    @Override
    public final int getSpawnDelay() {
        return DelayDuckSpawner.FIFTH_ROUND_DELAY.getSecondDelay();
    }

}
