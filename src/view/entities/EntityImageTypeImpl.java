package view.entities;

import java.util.Optional;

import javafx.scene.image.Image;
import model.entities.Dog;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.powerup.PowerUp;

/**
 *  EntityImage implementation.
 */
public final class EntityImageTypeImpl implements EntityImageType {

    /**
     * MILLIS UPDATE FRAME.
     */
    protected static final int UPDATE_IMAGE = 250;
    private final EntityImageAnimation dogAnimation;
    private final EntityImageAnimation duckAnimation;

    /**
     * Constructor of EntityImageTypeImpl.
     */
    public EntityImageTypeImpl() {
        super();
        this.dogAnimation = new DogAnimation();
        this.duckAnimation = new DuckAnimation();
    }

    @Override
    public Optional<Image> getImageType(final Entity entity) {
        Optional<Image> image = Optional.empty();
        Optional<Image> entityImage = Optional.empty();
        if (entity instanceof Dog) {
            entityImage = this.dogAnimation.getImage();
            image = entityImage.isPresent() ? Optional.of(entityImage.get()) : Optional.empty();
        } else if (entity instanceof Duck) {
            entityImage = this.duckAnimation.getImage();
            image = entityImage.isPresent() ? Optional.of(entityImage.get()) : Optional.empty();
        } else if (entity instanceof PowerUp) {
            entityImage = this.getPowerUpImage((PowerUp) entity);
            image = entityImage.isPresent() ? Optional.of(entityImage.get()) : Optional.empty();
        }
        return image;
    }

    @Override
    public void updateEntity(final Entity entity, final int elapsed) {
        if (entity instanceof Dog) {
            this.dogAnimation.update(entity, elapsed);
        } else if (entity instanceof Duck) {
            this.duckAnimation.update(entity, elapsed);
        }
    }

    private Optional<Image> getPowerUpImage(final PowerUp powerUp) {
        Optional<Image> image = Optional.empty();
        switch (powerUp.getType()) {
            case DOUBLE_SCORE:
                image = Optional.of(PowerUpImages.DOUBLE_SCORE.getPicture());
                break;
            case INFINITE_AMMO:
                image = Optional.of(PowerUpImages.INFITE_AMMO.getPicture());
                break;
            case SLOW_DOWN:
                image = Optional.of(PowerUpImages.SLOW_DOWN.getPicture());
                break;
            case KILL_ALL:
                image = Optional.of(PowerUpImages.KILL_ALL.getPicture());
                break;
            default:
                break;
        }
        if (!powerUp.isVisible()) {
            return Optional.empty();
        }
        return image;
    }
}
