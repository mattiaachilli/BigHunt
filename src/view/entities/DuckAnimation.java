package view.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.plaf.synth.SynthSpinnerUI;

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
        this.duckRightImages.put("Standard", StandardDuckImageType.getRight());
        this.duckRightImages.put("Yellow", YellowDuckImageType.getRight());
        this.duckRightImages.put("Orange", OrangeDuckImageType.getRight());
        this.duckRightImages.put("Pink", PinkDuckImageType.getRight());
        this.duckLeftImages.put("Standard", StandardDuckImageType.getLeft());
        this.duckLeftImages.put("Yellow", YellowDuckImageType.getLeft());
        this.duckLeftImages.put("Orange", OrangeDuckImageType.getLeft());
        this.duckLeftImages.put("Pink", PinkDuckImageType.getLeft());
        this.duckUpRightImages.put("Standard", StandardDuckImageType.getUpRight());
        this.duckUpRightImages.put("Yellow", YellowDuckImageType.getUpRight());
        this.duckUpRightImages.put("Orange", OrangeDuckImageType.getUpRight());
        this.duckUpRightImages.put("Pink", PinkDuckImageType.getUpRight());
        this.duckUpLeftImages.put("Standard", StandardDuckImageType.getUpLeft());
        this.duckUpLeftImages.put("Yellow", YellowDuckImageType.getUpLeft());
        this.duckUpLeftImages.put("Orange", OrangeDuckImageType.getUpLeft());
        this.duckUpLeftImages.put("Pink", PinkDuckImageType.getUpLeft());
        this.duckPrecipitateImages.put("Standard", StandardDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckPrecipitateImages.put("Yellow", YellowDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckPrecipitateImages.put("Orange", OrangeDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckPrecipitateImages.put("Pink", PinkDuckImageType.DUCK_PRECIPITATE.getPicture());
        this.duckFlyAwayImages.put("Standard", StandardDuckImageType.getFlyAway());
        this.duckFlyAwayImages.put("Yellow", YellowDuckImageType.getFlyAway());
        this.duckFlyAwayImages.put("Orange", OrangeDuckImageType.getFlyAway());
        this.duckFlyAwayImages.put("Pink", PinkDuckImageType.getFlyAway());
        this.duckKilled.put("Standard", StandardDuckImageType.DUCK_DEAD.getPicture());
        this.duckKilled.put("Yellow", YellowDuckImageType.DUCK_DEAD.getPicture());
        this.duckKilled.put("Orange", OrangeDuckImageType.DUCK_DEAD.getPicture());
        this.duckKilled.put("Pink", PinkDuckImageType.DUCK_DEAD.getPicture());
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
            color = "Standard";
        } else if (this.duck instanceof YellowDuck) {
            color = "Yellow";
        } else if (this.duck instanceof OrangeDuck) {
            color = "Orange";
        } else if (this.duck instanceof PinkDuck) {
            color = "Pink";
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
