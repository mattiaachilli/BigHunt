package model.properties;

/**
 * Class that represents the velocity in the game.
 */
public interface Velocity {

    /**
     * @param v
     *          the velocity of the entity
     * @return new velocity
     */
    Velocity sum(Velocity v);

    /**
     * @return the velocity of the entity
     */
    double module();

    /**
     * @param fact
     *          used to compute a new velocity
     * @return new velocity
     */
    Velocity mul(double fact);

    /**
     * Getter velocity axis x.
     *
     * @return the velocity axis x.
     */
    double getX();

    /**
     * Getter velocity axis y.
     *
     * @return the velocity axis y.
     */
    double getY();

    /**
     * Checks if velocities are equal.
     *
     * @param velocity
     *          velocity to compare
     * @return true if they are equal.
     */
    boolean compareTo(Velocity velocity);

}
