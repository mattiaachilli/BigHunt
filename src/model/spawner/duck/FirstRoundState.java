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
 * First round state.
 *
 */

public class FirstRoundState extends AbstractDuckState {

    private static final int FIRST_WAVE = 12;

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
        final DuckDirection direction = SpawnSide.getSpawnSide(); //Init direction
        int posX = SpawnSide.initPosX(direction);
        int posY = SpawnSide.getRandomPosY();
        final Velocity velocity = SpawnSide.getVelocity(direction, DuckProperty.STANDARD_DUCK);
        final Shape shape = new Rectangle(posX, posY, StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK);
        final Duck standardDuck = super.getDuckFactory().createStandardDuck(shape, velocity, direction);
        if (super.getDuckSpawned() <= FIRST_WAVE) {
            return standardDuck;
        } else {
            standardDuck.setVelocity(SpawnSide.getVelocity(direction, DuckProperty.YELLOW_DUCK));
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
