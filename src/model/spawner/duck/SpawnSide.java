package model.spawner.duck;

import java.util.Random;

import model.ModelImpl;
import model.entities.DuckProperty;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * Class for initial spawn.
 *
 */
public final class SpawnSide {
    private static final int SIDE = 2;

    private SpawnSide() {
        super();
    }
    /**
     * Random spawn side.
     * 
     * @return a random spawn side
     */
    public static DuckDirection getSpawnSide() {
        final int random = new Random().nextInt(SIDE);
        final DuckDirection direction;
        direction = random == 0 ? DuckDirection.LEFT : DuckDirection.RIGHT;
        return direction;
    }
    /**
     * 
     * @param initDirection 
     *                  of the duck
     * @param duckType 
     *          is the duck's type
     * @return velocity
     */
    public static Velocity getVelocity(final DuckDirection initDirection, final DuckProperty duckType) {
        final Velocity velocity;
        if (initDirection == DuckDirection.LEFT) {
            velocity = new VelocityImpl(-duckType.getVelocity(), 0);
        } else {
            velocity = new VelocityImpl(duckType.getVelocity(), 0);
        }
        return velocity;
    }
    /**
     * 
     * @param initDirection
     *                  initial direction of the duck 
     * @return the initial position of x
     */
    public static int initPosX(final DuckDirection initDirection) {
        return initDirection == DuckDirection.LEFT ? ModelImpl.GAME_WIDTH + StandardDuck.WIDTH_DUCK 
                                                     : 0 - StandardDuck.WIDTH_DUCK;
    }
}
