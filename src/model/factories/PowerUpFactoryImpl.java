package model.factories;

import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpImpl;
import model.entities.powerup.PowerUpType;
import model.properties.Position;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * Implementation of the powerUp factory.
 *
 */
public class PowerUpFactoryImpl implements PowerUpFactory {

    private static final Velocity POWER_UP_VELOCITY = new VelocityImpl(0.0, 250.0);
    private static final double POWER_UP_SIDE = 20.0;

    @Override
    public final PowerUp createPowerUp(final PowerUpType type, final Position position) {
        final Shape shape = new Rectangle(position.getX(), position.getY(), POWER_UP_SIDE, POWER_UP_SIDE);
        return new PowerUpImpl(type, shape, POWER_UP_VELOCITY);
    }

    @Override
    public final PowerUp createRandomPowerUp(final Position position) {
        /*
         * da fare in base alle rarità
         */
        final int random = new Random().nextInt(PowerUpType.values().length);
        final PowerUpType randomPowerUpType = PowerUpType.values()[random];
        return this.createPowerUp(randomPowerUpType, position);
    }
}
