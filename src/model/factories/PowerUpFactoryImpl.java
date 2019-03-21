package model.factories;

import java.util.Optional;
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

    private static final double POWER_UP_SIDE = 20.0;
    private static final Velocity POWER_UP_VELOCITY = new VelocityImpl(0, 0); 
    private int randPowerUpCounter = 0;
    private final Random showPowerUp = new Random();

    @Override
    public final Optional<PowerUp> createRandomPowerUp(final Position position) {
        final int random;
        final PowerUpType randomPowerUpType;

        if (showPowerUp.nextInt() % 1  == 0) {
            if (randPowerUpCounter != 0) {
                resetCounter();
                random = new Random().nextInt(PowerUpType.values().length);
                randomPowerUpType = PowerUpType.values()[random];
            } else {
                randPowerUpCounter++;
                randomPowerUpType = PowerUpType.INFINITE_AMMO;
            }
            return Optional.of(this.createPowerUp(randomPowerUpType, position));
        } else {
            return Optional.empty();
        }
    }

    private final PowerUp createPowerUp(final PowerUpType type, final Position position) {
        final Shape shape = new Rectangle(position.getX(), position.getY(), POWER_UP_SIDE, POWER_UP_SIDE);
        return new PowerUpImpl(type, shape, POWER_UP_VELOCITY);
    }

    private void resetCounter() {
        this.randPowerUpCounter = 0;
    }
}
