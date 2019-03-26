package view.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.StandardDuck;
import model.properties.DuckDirection;

/**
 * 
 * Animation of a duck.
 *
 */
public class DuckAnimation implements EntityImageAnimation {

    private int index;
    private int elapsed;
    private Duck duck;
    private DuckDirection lastDirection;
    private final Map<String, List<Image>> duckRightImages;
    private final Map<String, List<Image>> duckLeftImages;
    private final Map<String, List<Image>> duckUpRightImages;
    private final Map<String, List<Image>> duckUpLeftImages;
    private final Map<String, List<Image>> duckPrecipitateImages;
    private final Map<String, List<Image>> duckFlyAwayImages;
    private final Map<String, Image> duckKilled;

    /**
     * Constructor for duckAnimation.
     */
    public DuckAnimation() {
        this.duckRightImages = new HashMap<>();
        this.duckLeftImages = new HashMap<>();
        this.duckUpRightImages = new HashMap<>();
        this.duckUpLeftImages = new HashMap<>();
        this.duckPrecipitateImages = new HashMap<>();
        this.duckFlyAwayImages = new HashMap<>();
        this.duckKilled = new HashMap<>();
        this.initializeDuckImages();
    }

    private void initializeDuckImages() {
        this.duckRightImages.put("Standard", StandardDuckType.getRight());
        this.duckRightImages.put("Yellow", YellowDuckType.getRight());
        this.duckRightImages.put("Orange", OrangeDuckType.getRight());
        this.duckRightImages.put("Pink", PinkDuckType.getRight());
        this.duckLeftImages.put("Standard", StandardDuckType.getLeft());
        this.duckLeftImages.put("Yellow", YellowDuckType.getLeft());
        this.duckLeftImages.put("Orange", OrangeDuckType.getLeft());
        this.duckLeftImages.put("Pink", PinkDuckType.getLeft());
        this.duckUpRightImages.put("Standard", StandardDuckType.getUpRight());
        this.duckUpRightImages.put("Yellow", YellowDuckType.getUpRight());
        this.duckUpRightImages.put("Orange", OrangeDuckType.getUpRight());
        this.duckUpRightImages.put("Pink", PinkDuckType.getUpRight());
        this.duckUpLeftImages.put("Standard", StandardDuckType.getUpLeft());
        this.duckUpLeftImages.put("Yellow", YellowDuckType.getUpLeft());
        this.duckUpLeftImages.put("Orange", OrangeDuckType.getUpLeft());
        this.duckUpLeftImages.put("Pink", PinkDuckType.getUpLeft());
        this.duckPrecipitateImages.put("Standard", StandardDuckType.getPrecipitate());
        this.duckPrecipitateImages.put("Yellow", YellowDuckType.getPrecipitate());
        this.duckPrecipitateImages.put("Orange", OrangeDuckType.getPrecipitate());
        this.duckPrecipitateImages.put("Pink", PinkDuckType.getPrecipitate());
        this.duckFlyAwayImages.put("Standard", StandardDuckType.getFlyAway());
        this.duckFlyAwayImages.put("Yellow", YellowDuckType.getFlyAway());
        this.duckFlyAwayImages.put("Orange", OrangeDuckType.getFlyAway());
        this.duckFlyAwayImages.put("Pink", PinkDuckType.getFlyAway());
        this.duckKilled.put("Standard", StandardDuckType.DUCK_DEAD.getPicture());
        this.duckKilled.put("Yellow", YellowDuckType.DUCK_DEAD.getPicture());
        this.duckKilled.put("Orange", OrangeDuckType.DUCK_DEAD.getPicture());
        this.duckKilled.put("Pink", PinkDuckType.DUCK_DEAD.getPicture());
    }

    private void updateIndex() {
        if (this.elapsed >= EntityImageTypeImpl.UPDATE_IMAGE) {
            this.elapsed -= EntityImageTypeImpl.UPDATE_IMAGE;
            this.index++;
        }
    }

    @Override
    public final Image getImage() {
        Image image = null;
        String color = "";
        if (this.duck instanceof StandardDuck) {
            color = "Standard";
        } else if (this.duck instanceof YellowDuck) {
            color = "Yellow";
        } else if (this.duck instanceof OrangeDuck) {
            color = "Orange";
        } else if (this.duck instanceof PinkDuck) {
            color = "PinkDuck";
        } 

        switch (this.duck.getActualDirection()) {
            case RIGHT:
                if (this.index >= this.duckRightImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckRightImages.get(color).get(this.index);
                break;
            case LEFT:
                if (this.index >= this.duckLeftImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckLeftImages.get(color).get(this.index);
                break;
            case RIGHT_UP:
                if (this.index >= this.duckUpRightImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckUpRightImages.get(color).get(this.index);
                break;
            case LEFT_UP:
                if (this.index >= this.duckUpLeftImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckUpLeftImages.get(color).get(this.index);
                break;
            case RIGHT_DOWN:
                if (this.index >= this.duckUpRightImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckUpRightImages.get(color).get(this.index);
                break;
            case LEFT_DOWN:
                if (this.index >= this.duckUpLeftImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckUpLeftImages.get(color).get(this.index);
                break;
            case KILLED:
                image = this.duckKilled.get(color);
                break;
            case FLOWN_AWAY:
                if (this.index >= this.duckFlyAwayImages.get(color).size()) {
                    this.index = 0;
                }
                image = this.duckFlyAwayImages.get(color).get(this.index);
                break;
            default:
                break;
        }
        this.updateIndex();
        return image;
    }

    @Override
    public final void update(final Entity entity, final int elapsed) {
        this.elapsed += elapsed;
        this.duck = (Duck) entity;
        if (this.lastDirection != this.duck.getActualDirection()) {
            this.index = 0;
        }
        this.lastDirection = this.duck.getActualDirection();
    }
}
