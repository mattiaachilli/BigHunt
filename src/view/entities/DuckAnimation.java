package view.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.scene.image.Image;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.StandardDuck;

/**
 * 
 * Animation of a duck.
 *
 */
public class DuckAnimation implements EntityImageAnimation {

    private static final int UPDATE_DUCK = 500;
    
    private static final String STANDARD = "Standard";
    private static final String YELLOW = "Yellow";
    private static final String ORANGE = "Orange";
    private static final String PINK = "PINK";

    private int index;
    private int elapsed;
    private Duck duck;
    private final Map<String, List<Image>> duckRightImages;
    private final Map<String, List<Image>> duckLeftImages;
    private final Map<String, List<Image>> duckUpRightImages;
    private final Map<String, List<Image>> duckUpLeftImages;
    private final Map<String, List<Image>> duckFlyAwayImages;
    private final Map<String, Image> duckPrecipitateImages;
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
        this.duckRightImages.put(STANDARD, StandardDuckImageType.getRight());
        this.duckRightImages.put(YELLOW, YellowDuckImageType.getRight());
        this.duckRightImages.put(ORANGE, OrangeDuckImageType.getRight());
        this.duckRightImages.put(PINK, PinkDuckImageType.getRight());
        this.duckLeftImages.put(STANDARD, StandardDuckImageType.getLeft());
        this.duckLeftImages.put(YELLOW, YellowDuckImageType.getLeft());
        this.duckLeftImages.put(ORANGE, OrangeDuckImageType.getLeft());
        this.duckLeftImages.put(PINK, PinkDuckImageType.getLeft());
        this.duckUpRightImages.put(STANDARD, StandardDuckImageType.getUpRight());
        this.duckUpRightImages.put(YELLOW, YellowDuckImageType.getUpRight());
        this.duckUpRightImages.put(ORANGE, OrangeDuckImageType.getUpRight());
        this.duckUpRightImages.put(PINK, PinkDuckImageType.getUpRight());
        this.duckUpLeftImages.put(STANDARD, StandardDuckImageType.getUpLeft());
        this.duckUpLeftImages.put(YELLOW, YellowDuckImageType.getUpLeft());
        this.duckUpLeftImages.put(ORANGE, OrangeDuckImageType.getUpLeft());
        this.duckUpLeftImages.put(PINK, PinkDuckImageType.getUpLeft());
        this.duckPrecipitateImages.put(STANDARD, StandardDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckPrecipitateImages.put(YELLOW, YellowDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckPrecipitateImages.put(ORANGE, OrangeDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckPrecipitateImages.put(PINK, PinkDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckFlyAwayImages.put(STANDARD, StandardDuckImageType.getFlyAway());
        this.duckFlyAwayImages.put(YELLOW, YellowDuckImageType.getFlyAway());
        this.duckFlyAwayImages.put(ORANGE, OrangeDuckImageType.getFlyAway());
        this.duckFlyAwayImages.put(PINK, PinkDuckImageType.getFlyAway());
        this.duckKilled.put(STANDARD, StandardDuckImageType.DUCK_DEAD.getPicture());
        this.duckKilled.put(YELLOW, YellowDuckImageType.DUCK_DEAD.getPicture());
        this.duckKilled.put(ORANGE, OrangeDuckImageType.DUCK_DEAD.getPicture());
        this.duckKilled.put(PINK, PinkDuckImageType.DUCK_DEAD.getPicture());
    }

    private void updateIndex() {
        if (this.elapsed >= UPDATE_DUCK) {
            this.elapsed -= UPDATE_DUCK;
            this.index++;
        }
    }

    @Override
    public final Optional<Image> getImage() {
        Optional<Image> image = Optional.empty();
        String color = "";
        if (this.duck instanceof StandardDuck) {
            color = STANDARD;
        } else if (this.duck instanceof YellowDuck) {
            color = YELLOW;
        } else if (this.duck instanceof OrangeDuck) {
            color = ORANGE;
        } else if (this.duck instanceof PinkDuck) {
            color = PINK;
        } 

        switch (this.duck.getActualDirection()) {
            case RIGHT:
                if (this.index >= this.duckRightImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckRightImages.get(color).get(this.index));
                break;
            case LEFT:
                if (this.index >= this.duckLeftImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckLeftImages.get(color).get(this.index));
                break;
            case RIGHT_UP:
                if (this.index >= this.duckUpRightImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckUpRightImages.get(color).get(this.index));
                break;
            case LEFT_UP:
                if (this.index >= this.duckUpLeftImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckUpLeftImages.get(color).get(this.index));
                break;
            case RIGHT_DOWN:
                if (this.index >= this.duckUpRightImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckUpRightImages.get(color).get(this.index));
                break;
            case LEFT_DOWN:
                if (this.index >= this.duckUpLeftImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckUpLeftImages.get(color).get(this.index));
                break;
            case KILLED:
                image = Optional.of(this.duckKilled.get(color));
                break;
            case FLOWN_AWAY:
                if (this.index >= this.duckFlyAwayImages.get(color).size()) {
                    this.index = 0;
                }
                image = Optional.of(this.duckFlyAwayImages.get(color).get(this.index));
                break;
            case PRECIPITATE:
                image = Optional.of(this.duckPrecipitateImages.get(color));
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
    }
}
