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
/*
    @Override
    public final void clean(final List<Duck> ducks, final List<PowerUp> powerUps) {
        final List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < ducks.size(); i++) {
            if (ducks.get(i).getPosition().getY() < 0 || ducks.get(i).getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                indexes.add(i);

            }
        }

        for (final Integer index : indexes) {
            ducks.remove((int) index);
        }

        indexes.clear();

        for (int i = 0; i < powerUps.size(); i++) {
            if (powerUps.get(i).isHit() && powerUps.get(i).isVisible()
            || powerUps.get(i).getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                indexes.add(i);
            }
        }
        for (final Integer index : indexes) {
            powerUps.remove((int) index);
        }
    }
*/
    @Override
    public final void clean(final List<Duck> ducks, final List<PowerUp> powerUps) {
        final Iterator<Duck> itDucks = ducks.iterator();
        while (itDucks.hasNext()) {
            Duck duck = itDucks.next();
            if (duck.getPosition().getY() < 0 || duck.getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                itDucks.remove();
            }
        }
        final Iterator<PowerUp> itPowerUps = powerUps.iterator();
        while (itPowerUps.hasNext()) {
            PowerUp pu = itPowerUps.next();
            if (pu.isHit() && pu.isVisible()
            || pu.getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                itPowerUps.remove();
            }
        }
    }
}
