package model.factories;

import java.util.Objects;
import java.util.Optional;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpImpl;
import model.entities.powerup.PowerUpType;
import model.properties.Position;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import model.rarity.ItemRarity;

/**
 * 
 * Implementation of the powerUp factory.
 *
 */
public final class PowerUpFactoryImpl implements PowerUpFactory {

    private static final int POWER_UP_VERY_COMMON = 4;
    private static final int POWER_UP_COMMON = 7;
    private static final int POWER_UP_RARE = 10;
    private static final int POWER_UP_VERY_RARE = 15;

    private static final double POWER_UP_SIDE = 20.0;
    private static final Velocity POWER_UP_VELOCITY = new VelocityImpl(0, 0); 
    private int randPowerUpCounter = 0;
    private static PowerUpFactory instance;

    private PowerUpFactoryImpl() {
        super();
    }

    /**
     * 
     * @return the instance of this object, once.
     */
    public static PowerUpFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PowerUpFactoryImpl();
        }
        return instance;
    }

    @Override
    public Optional<PowerUp> createRandomPowerUp(final Position position) {
        final ItemRarity rarityPowerUp;
        this.randPowerUpCounter++;

        if (this.randPowerUpCounter % POWER_UP_VERY_COMMON == 0) {
            rarityPowerUp = ItemRarity.VERY_COMMON;
        } else if (this.randPowerUpCounter % POWER_UP_COMMON == 0) {
            rarityPowerUp = ItemRarity.COMMON;
        } else if (this.randPowerUpCounter % POWER_UP_RARE == 0) {
            rarityPowerUp = ItemRarity.RARE;
        } else if (this.randPowerUpCounter % POWER_UP_VERY_RARE == 0) {
            rarityPowerUp = ItemRarity.VERY_RARE;
        } else {
            return Optional.empty();
        }

        return Optional.of(this.createPowerUp(rarityPowerUp, position));
    }

    private PowerUp createPowerUp(final ItemRarity rarity, final Position position) {
        final Shape shape = new Rectangle(position.getX(), position.getY(), POWER_UP_SIDE, POWER_UP_SIDE);
        Optional<PowerUpType> powerUpType = Optional.empty();
        for (final PowerUpType type: PowerUpType.values()) {
            if (type.getPowerUpRarity() == rarity) {
                powerUpType = Optional.of(type);
            }
        }
        return new PowerUpImpl(powerUpType.get(), shape, POWER_UP_VELOCITY);
    }
}
