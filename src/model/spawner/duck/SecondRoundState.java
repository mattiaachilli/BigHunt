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
 * Second round state.
 */

public class SecondRoundState extends AbstractDuckState {

    private static final int FIRST_WAVE = 10;
    private static final int SECOND_WAVE = 3;
    
    public SecondRoundState() {
        super();
        super.resetDuckSpawned();
    }
    
    @Override
    public Duck spawnDuck() {
        super.incDuckSpawned();
        final DuckDirection direction = SpawnSide.getSpawnSide(); //Init direction
        int posX = SpawnSide.initPosX(direction);
        final Velocity velocity = SpawnSide.getVelocity(direction, DuckProperty.STANDARD_DUCK);
        final Duck standardDuck = super.getDuckFactory()
                                       .createStandardDuck(
                                       new Rectangle(posX, ModelImpl.GAME_HEIGHT / 2 - SPAWN_Y, StandardDuck.HEIGHT_DUCK, StandardDuck.HEIGHT_DUCK)
                                       ,velocity 
                                       ,direction);
        if(super.getDuckSpawned() <= FIRST_WAVE) {
            return standardDuck;
        } else if(super.getDuckSpawned() > FIRST_WAVE && 
            super.getDuckSpawned() <= FIRST_WAVE + SECOND_WAVE) {
            standardDuck.setVelocity(SpawnSide.getVelocity(direction, DuckProperty.YELLOW_DUCK));
            return super.getDuckFactory().createYellowDuck(standardDuck);
        } else { //Last wave of two ducks
            standardDuck.setVelocity(SpawnSide.getVelocity(direction, DuckProperty.ORANGE_DUCK));
            return super.getDuckFactory().createOrangeDuck(standardDuck);
        }
    }

    @Override
    public boolean isStateEnded() {
        return super.getDuckSpawned() >= NUM_DUCK_ROUND;
    }

    @Override
    public Optional<DuckState> getNextState() {
        return Optional.of(new ThirdRoundState());
    }

    @Override
    public int getSpawnDelay() {
        return DelayDuckSpawner.SECOND_ROUND_DELAY.getSecondDelay();
    }
}
