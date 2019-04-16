package model.cleaner;

import java.util.Iterator;
import java.util.List;

import model.entities.DogImpl;
import model.entities.Duck;
import model.entities.powerup.PowerUp;

/**
 * 
 * Implementation of the cleaner.
 *
 */
public class CleanerImpl implements Cleaner {
    @Override
    public final void clean(final List<Duck> ducks, final List<PowerUp> powerUps) {
        final Iterator<Duck> itDucks = ducks.iterator();
        while (itDucks.hasNext()) {
            final Duck duck = itDucks.next();
            if (duck.getPosition().getY() < 0 || duck.getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                itDucks.remove();
            }
        }
        final Iterator<PowerUp> itPowerUps = powerUps.iterator();
        while (itPowerUps.hasNext()) {
            final PowerUp pu = itPowerUps.next();
            if (pu.isHit() && pu.isVisible()
            || pu.getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                itPowerUps.remove();
            }
        }
    }
}
