package model.spawner.duck;

import java.util.Optional;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.Duck;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;

/**
 * 
 * First round state.
 *
 */

public class FirstRoundState extends AbstractDuckState {

    private static final int FIRST_WAVE = 10;

    /**
     * First Round constructor.
     */
    public FirstRoundState() {
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
        } else {
            velocity = SpawnSideUtil.getVelocity(direction, StandardDuck.VELOCITY);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
            return super.getDuckFactory().createYellowDuck(standardDuck);
        }
    }

    @Override
    public final boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public final Optional<DuckState> getNextState() {
       return Optional.of(new SecondRoundState());
    }

    @Override
    public final int getSpawnDelay() {
        return DelayDuckSpawner.FIRST_ROUND_DELAY.getSecondDelay();
    }
}
