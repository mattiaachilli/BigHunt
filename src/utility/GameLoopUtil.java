package utility;

/**
 * 
 * Class utilities for some operation, like waitForNextFrame in GameLoop.
 *
 */

public final class GameLoopUtil {

    private GameLoopUtil() {
        super();
    }

   /**
     * 
     * @param period
     *            total time available to perform the cycle phase.
     * @param start
     *            start time of the cycle phase.
     */
    public static void waitForNextFrame(final int period, final long start) {
        final long dt = System.currentTimeMillis() - start;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
