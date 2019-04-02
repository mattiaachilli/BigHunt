package controller.input;

import model.Model;
import model.entities.Duck;
import model.entities.EntityStatus;
import model.entities.powerup.PowerUp;

/**
 * 
 * @author giuli
 *
 */
public class Shoot implements Command {

    private final double x;
    private final double y;
    private final CommandType type = CommandType.SHOOT;

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
    public void execute(final Model model) {
        if (model.canShoot()) {
            int score = 0;
            model.shoot();
            for (final Duck d : model.getDucks()) {
                if (d.getShape().contains(x, y) && d.getStatus() == EntityStatus.ALIVE) {
                    d.kill();
                    score = model.getPowerUpActive().isPresent() ? d.getScore() * 2 : d.getScore();
                    model.getMatchData().incrementScoreOf(score);
                    model.getMatchData().incrementKilledDucks();
                }
            }
            for (final PowerUp p : model.getPowerUps()) {
                if (p.getShape().contains(x, y)) {
                    p.hit();
                }
            }
        }
    }

    @Override
    public CommandType getType() {
        return this.type;
    }

}
