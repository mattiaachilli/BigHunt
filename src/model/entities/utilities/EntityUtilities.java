package model.entities.utilities;

import model.decorator.YellowDuck;

import java.security.SecureRandom;
import model.entities.StandardDuck;
import model.ModelImpl;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import model.spawner.duck.DelayDuckSpawner;

/**
 * 
 * Utilities for the entities, ducks for example.
 *
 */
public final class EntityUtilities {

    private static final int MILLIS = 1000;
    private static final int FLY_AWAY_VELOCITY = 1500;
    private static final int KILLED_VELOCITY = 400;
    /**
     * Check if fly away is possible.
     * 
     * @param duck
     *          duck's type
     * @return true if duck can fly away.
     */

    private EntityUtilities() {
        super();
    }

    /**
     * Check if duck can fly away.
     * 
     * @param duck
     *          is the duck to check if fly away is possible
     *
     * @return true if the duck can fly away
     */
    public static boolean checkFlyAway(final Duck duck) {
        int time;
        if (duck instanceof StandardDuck) {
            time = DuckProperty.STANDARD_DUCK.getTimeFlyAway();
        } else if (duck instanceof YellowDuck) {
            time = DuckProperty.YELLOW_DUCK.getTimeFlyAway();
        } else if (duck instanceof OrangeDuck) {
            time = DuckProperty.ORANGE_DUCK.getTimeFlyAway();
        } else if (duck instanceof PinkDuck) {
            time = DuckProperty.PINK_DUCK.getTimeFlyAway();
        } else {
            throw new IllegalArgumentException();
        }
        return duck.getTimeFromBirth() >= time;
    }

    /**
     * Generate a random number between 1000(ONE MILLISECOND)-maxRange.
     * 
     * @param maxRange 
     *          max number to generate random number.
     * @return random number approximated
     */
    public static int getRandomDelay(final int maxRange) {
        final int numberRandom = new SecureRandom().nextInt(maxRange) + MILLIS;
        int newValue = 0;
        if (numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(0) 
            && 
            numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(1)) {

            newValue = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(0);
        } else if (numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(1) 
                   && 
                   numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(2)) {

            newValue = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(1);
        } else if (numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(2) 
                   && 
                   numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(3)) {

            newValue = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(2);
        } else if (numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(3) 
                   && 
                   numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(4)) {

            newValue = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(3);
        } else if (numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(4)) {

            newValue = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(4); 
        }
        return newValue;
    }

    /**
     * 
     * @param duck
     *          duck to set new position
     * @param direction
     *          direction to change
     */

    public static void setNewPosition(final Duck duck, final DuckDirection direction) {
        Velocity velocity = new VelocityImpl(0, 0);
        double movement = 0;
        if (duck instanceof StandardDuck) {
            movement = DuckProperty.STANDARD_DUCK.getVelocity();
        } else if (duck instanceof YellowDuck) {
            movement = DuckProperty.YELLOW_DUCK.getVelocity();
        } else if (duck instanceof OrangeDuck) {
            movement = DuckProperty.ORANGE_DUCK.getVelocity();
        } else if (duck instanceof PinkDuck) {
            movement = DuckProperty.PINK_DUCK.getVelocity();
        }
        if (duck.getActualDirection() != direction) {
            switch (direction) {
                case RIGHT:
                    velocity = new VelocityImpl(movement, 0);
                    break;
                case LEFT:
                    velocity = new VelocityImpl(-movement, 0);
                    break;
                case RIGHT_UP:
                    velocity = new VelocityImpl(movement, -movement);
                    break;
                case RIGHT_DOWN:
                    velocity = new VelocityImpl(movement, movement);
                    break;
                case LEFT_UP:
                    velocity = new VelocityImpl(-movement, -movement);
                    break;
                case LEFT_DOWN:
                    velocity = new VelocityImpl(-movement, movement);
                    break;
                case FLOWN_AWAY:
                    velocity = new VelocityImpl(0, -FLY_AWAY_VELOCITY);
                    break;
                case KILLED:
                    velocity = new VelocityImpl(0, KILLED_VELOCITY);
                    break;
                default:
                    break;
            }
            duck.setDirection(direction);
            duck.setVelocity(velocity);
        }
    }
    /**
     * Check duck collision with the window, if there are, change duck's position.
     * 
     * @param duck
     *          check collision
     * @param actualDirection
     *          duck's flying
     */
    public static void checkCollision(final Duck duck, final DuckDirection actualDirection) {
        //RIGHT
        if (actualDirection == DuckDirection.RIGHT) {
            if (duck.getPosition().getX() >= ModelImpl.GAME_WIDTH - StandardDuck.WIDTH_DUCK / 2) {
                EntityUtilities.setNewPosition(duck, actualDirection.getOpponentPosition().get());
            } 
        } else if (actualDirection == DuckDirection.LEFT) { //LEFT
            if (duck.getPosition().getX() <= StandardDuck.COLLISION_X) {
                EntityUtilities.setNewPosition(duck, actualDirection.getOpponentPosition().get());
            }
        }
        //LEFT/RIGHT DOWN
        if (actualDirection == DuckDirection.LEFT_DOWN) {
            if (duck.getPosition().getY() >= ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 4) {
                EntityUtilities.setNewPosition(duck, DuckDirection.LEFT_UP);
            } else if (duck.getPosition().getX() <= StandardDuck.COLLISION_X) {
                EntityUtilities.setNewPosition(duck, actualDirection.getOpponentPosition().get());
            }
        }
        if (actualDirection == DuckDirection.RIGHT_DOWN) {
            if (duck.getPosition().getY() >= ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 4) {
                EntityUtilities.setNewPosition(duck, DuckDirection.RIGHT_UP);
            } 
            if (duck.getPosition().getX() >= ModelImpl.GAME_WIDTH - StandardDuck.WIDTH_DUCK / 2) {
                EntityUtilities.setNewPosition(duck, actualDirection.getOpponentPosition().get());
            }
        }
        //LEFT/RIGHT UP
        if (actualDirection == DuckDirection.LEFT_UP) {
            if (duck.getPosition().getY() <= StandardDuck.COLLISION_Y) {
                EntityUtilities.setNewPosition(duck, DuckDirection.LEFT_DOWN);
            }
            if (duck.getPosition().getX() <= StandardDuck.COLLISION_X) {
                EntityUtilities.setNewPosition(duck, actualDirection.getOpponentPosition().get());
            }
        }
        if (actualDirection == DuckDirection.RIGHT_UP) {
            if (duck.getPosition().getY() <= StandardDuck.COLLISION_Y) {
                EntityUtilities.setNewPosition(duck, DuckDirection.RIGHT_DOWN);
            } 
            if (duck.getPosition().getX() >= ModelImpl.GAME_WIDTH - StandardDuck.WIDTH_DUCK / 2) {
                EntityUtilities.setNewPosition(duck, actualDirection.getOpponentPosition().get());
            }
        }
    }
}
