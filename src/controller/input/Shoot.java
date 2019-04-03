package controller.input;

import model.Model;
import model.ModelImpl;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.EntityStatus;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;

/**
 * 
 * @author giuli
 *
 */
public class Shoot implements Command {

    private final double x;
    private final double y;
    private final CommandType type = CommandType.SHOOT;
    private int numDuckDoubleScore;

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
        this.numDuckDoubleScore = 0;
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
                                && this.numDuckDoubleScore < ModelImpl.NEXT_DUCKS_POWERUP) {
                                score = d.getScore() * 2;
                                this.numDuckDoubleScore++;
                            } else if (this.numDuckDoubleScore >= ModelImpl.NEXT_DUCKS_POWERUP) {
                                this.numDuckDoubleScore = 0;
                                model.endPowerUp();
                            }
                            model.getDog().setLastDuckKilled(d);
                            model.getMatchData().incrementScoreOf(score);
                            model.getMatchData().incrementKilledDucks();
                            hit = true;
                        }
                    } else if (entity instanceof PowerUp) {
                        final PowerUp p = (PowerUp) entity;
                        if (p.getShape().contains(x, y)) {
                            p.hit();
                            hit = true;
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
