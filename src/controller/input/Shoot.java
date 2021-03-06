package controller.input;

import audio.SoundUtil;
import model.Model;
import model.ModelImpl;
import model.entities.Dog;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.EntityStatus;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import settings.SettingsImpl;

/**
 * Class that handles the shooting command.
 */
public class Shoot implements Command {

    private final double x;
    private final double y;
    private final CommandType type = CommandType.SHOOT;
    private static int numDuckDoubleScore;

    /**
     * 
     * @param x
     *          horizontal coordinate of the mouse click
     * @param y
     *          vertical coordinate of the mouse click
     */
    public Shoot(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final void execute(final Model model) {
        if (model.canShoot()) {
            int score = 0;
            boolean hit = false;
            model.shoot();
            for (final Entity entity: model.getEntities()) {
                if (!hit) {
                    if (entity instanceof Duck) {
                        final Duck d = (Duck) entity;
                        if (d.getShape().contains(x, y) && d.getStatus() == EntityStatus.ALIVE) {
                            d.kill();
                            score = d.getScore(); 
                            if (model.getPowerUpActive().isPresent() && model.getPowerUpActive().get() == PowerUpType.DOUBLE_SCORE 
                                && numDuckDoubleScore < ModelImpl.NEXT_DUCKS_POWERUP) {
                                score = d.getScore() * 2;
                                numDuckDoubleScore++;
                            } else if (numDuckDoubleScore >= ModelImpl.NEXT_DUCKS_POWERUP) {
                                numDuckDoubleScore = 0;
                                model.endPowerUp();
                            }
                            model.getMatchData().incrementScoreOf(score);
                            model.getMatchData().incrementKilledDucks();
                            hit = true;
                            if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                                SoundUtil.playSound(SoundUtil.getDuckDeadAudio());
                            }
                        }
                    } else if (entity instanceof PowerUp) {
                        final PowerUp p = (PowerUp) entity;
                        if (p.getShape().contains(x, y)) {
                            p.hit();
                            hit = true;
                            model.getMatchData().powerUpCollected(p.getType());
                        }
                    } else if (entity instanceof Dog) {
                        final Dog d = (Dog) entity;
                        if (d.getShape().contains(x, y) && !d.isInGrass()) {
                            if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                                SoundUtil.playSound(SoundUtil.getDogAudio());
                            }
                            hit = true;
                            model.getMatchData().decrementScoreOf(d.getDogNegativeScore());
                        }
                    }
                }
            }
        }
    }

    @Override
    public final CommandType getType() {
        return this.type;
    }

}
