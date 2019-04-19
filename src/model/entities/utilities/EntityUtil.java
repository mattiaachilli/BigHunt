package model.entities.utilities;


import model.entities.StandardDuck;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import model.ModelImpl;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * Utilities for the entities.
 *
 */
public final class EntityUtil {
    private static final int POSSIBLE_DIRECTION = 6;
    private static final double FLY_AWAY_VELOCITY = ModelImpl.GAME_WIDTH / 1.2;
    private static final double KILLED_VELOCITY = ModelImpl.GAME_WIDTH / 4;
    private static final double MAX_DOWN_Y = ModelImpl.GAME_HEIGHT * 0.60;

    private EntityUtil() {
        super();
    }

    /**
     * Change direction of the duck.
     * @param duck
     *          duck to change direction.
     */
    public static void changeDirection(final Duck duck) {
        final int randomDirection = new Random().nextInt(POSSIBLE_DIRECTION) + 1;

        for (final Pair<DuckDirection, Integer> direction: DuckDirection.getRandomDirection()) {
            if (direction.getRight() == randomDirection) {
                EntityUtil.setNewPosition(duck, duck.isDecelerated(), direction.getLeft());
            }
        }
    }

    private static boolean checkVelocity(final Duck duck, final double velocity) {
        return Double.compare(duck.getVelocity().getX(), velocity) == 0
               || 
               Double.compare(duck.getVelocity().getY(), velocity) == 0
               ||
               Double.compare(duck.getVelocity().getX(), -velocity) == 0
               || 
               Double.compare(duck.getVelocity().getY(), -velocity) == 0
               || 
               Double.compare(duck.getVelocity().getX(), velocity * 0.5) == 0
               || 
               Double.compare(duck.getVelocity().getY(), velocity * 0.5) == 0
               ||
               Double.compare(duck.getVelocity().getX(), -velocity * 0.5) == 0
               || 
               Double.compare(duck.getVelocity().getY(), -velocity * 0.5) == 0;
    }


    /**
     * Set new position of the duck about its direction and velocity.
     * 
     * @param duck
     *          duck to set new position
     * @param direction
     *          direction to change
     * @param decelerate
     *          if duck must slow down.
     */
    public static void setNewPosition(final Duck duck, final boolean decelerate,
                                    final DuckDirection direction) {
        Velocity velocity = new VelocityImpl(0, 0);
        double movement = 0;
        final double deceleration = decelerate ? 0.5 : 1;
        if (checkVelocity(duck, StandardDuck.VELOCITY)) {
            movement = StandardDuck.VELOCITY * deceleration;
        } else if (checkVelocity(duck, YellowDuck.VELOCITY)) {
            movement = YellowDuck.VELOCITY * deceleration;
        } else if (checkVelocity(duck, OrangeDuck.VELOCITY)) {
            movement = OrangeDuck.VELOCITY * deceleration;
        } else if (checkVelocity(duck, PinkDuck.VELOCITY)) {
            movement = PinkDuck.VELOCITY * deceleration;
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
                case PRECIPITATE:
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
     *          duck.
     * @param actualDirection
     *          duck's flying.
     * @param decelerate
     *          if duck must slow down.
     */
    public static void checkCollision(final Duck duck, final boolean decelerate, final DuckDirection actualDirection) {
        //RIGHT
        if (actualDirection == DuckDirection.RIGHT && duck.getPosition().getX() >= ModelImpl.GAME_WIDTH - StandardDuck.COLLISION_X) {
            EntityUtil.setNewPosition(duck, decelerate, actualDirection.getOpponentPosition().get());
        } else if (actualDirection == DuckDirection.LEFT && duck.getPosition().getX() <= StandardDuck.COLLISION_X) {
            EntityUtil.setNewPosition(duck, decelerate, actualDirection.getOpponentPosition().get());
        }
        //LEFT/RIGHT DOWN
        if (actualDirection == DuckDirection.LEFT_DOWN) {
            if (duck.getPosition().getY() >= MAX_DOWN_Y) {
                EntityUtil.setNewPosition(duck, decelerate, DuckDirection.LEFT_UP);
            }
            if (duck.getPosition().getX() <= StandardDuck.COLLISION_X) {
                EntityUtil.setNewPosition(duck, decelerate, actualDirection.getOpponentPosition().get());
            }
        }
        if (actualDirection == DuckDirection.RIGHT_DOWN) {
            if (duck.getPosition().getY() >= MAX_DOWN_Y) {
                EntityUtil.setNewPosition(duck, decelerate, DuckDirection.RIGHT_UP);
            } 
            if (duck.getPosition().getX() >= ModelImpl.GAME_WIDTH - StandardDuck.COLLISION_X) {
                EntityUtil.setNewPosition(duck, decelerate, actualDirection.getOpponentPosition().get());
            }
        }
        //LEFT/RIGHT UP
        if (actualDirection == DuckDirection.LEFT_UP) {
            if (duck.getPosition().getY() <= StandardDuck.COLLISION_Y) {
                EntityUtil.setNewPosition(duck, decelerate, DuckDirection.LEFT_DOWN);
            }
            if (duck.getPosition().getX() <= StandardDuck.COLLISION_X) {
                EntityUtil.setNewPosition(duck, decelerate, actualDirection.getOpponentPosition().get());
            }
        }
        if (actualDirection == DuckDirection.RIGHT_UP) {
            if (duck.getPosition().getY() <= StandardDuck.COLLISION_Y) {
                EntityUtil.setNewPosition(duck, decelerate, DuckDirection.RIGHT_DOWN);
            } 
            if (duck.getPosition().getX() >= ModelImpl.GAME_WIDTH - StandardDuck.COLLISION_X) {
                EntityUtil.setNewPosition(duck, decelerate, actualDirection.getOpponentPosition().get());
            }
        }
    }
}
