package model.spawner.duck;

import java.util.Optional;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;

/**
 * 
 * Fourth round state.
 *
 */

public class FourthRoundState extends AbstractDuckState {

    private static final int FIRST_WAVE = 5;
    private static final int SECOND_WAVE = 5;
    private static final int THIRD_WAVE = 3;

    /**
     * Fourth round constructor.
     */
    public FourthRoundState() {
        super();
        super.resetDuckSpawned();
    }

    @Override
    public final Duck spawnDuck() {
        super.incDuckSpawned();
        final DuckDirection direction = SpawnSide.getSpawnSide(); //Init direction
        int posX = SpawnSide.initPosX(direction);
        int posY = SpawnSide.getRandomPosY();
        Velocity velocity = SpawnSide.getVelocity(direction, DuckProperty.STANDARD_DUCK);
        final Shape shape = new Rectangle(posX, posY, StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK);
        Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction, DuckProperty.STANDARD_DUCK);
        if (super.getDuckSpawned() <= FIRST_WAVE) {
            return standardDuck;
        } else if (super.getDuckSpawned() > FIRST_WAVE 
                   && 
                   super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE) {
            velocity = SpawnSide.getVelocity(direction, DuckProperty.YELLOW_DUCK);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction, DuckProperty.YELLOW_DUCK);
            return super.getDuckFactory().createYellowDuck(standardDuck);
        } else if (super.getDuckSpawned() > FIRST_WAVE + SECOND_WAVE 
                   && 
                   super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE + THIRD_WAVE) { //Last wave of four ducks
            velocity = SpawnSide.getVelocity(direction, DuckProperty.ORANGE_DUCK);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction, DuckProperty.ORANGE_DUCK);
            return super.getDuckFactory().createOrangeDuck(standardDuck);
        } else {
            velocity = SpawnSide.getVelocity(direction, DuckProperty.PINK_DUCK);
            standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction, DuckProperty.PINK_DUCK);
            return super.getDuckFactory().createPinkDuck(standardDuck);
        }
    }

    @Override
    public final boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public final Optional<DuckState> getNextState() {
        return Optional.of(new FifthRoundState());
    }

    @Override
    public final int getSpawnDelay() {
        return DelayDuckSpawner.FOURTH_ROUND_DELAY.getSecondDelay();
    }
}
