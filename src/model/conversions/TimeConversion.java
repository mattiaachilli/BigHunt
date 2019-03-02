package model.conversions;
import java.util.concurrent.TimeUnit;

/**
 * This class convert from milliseconds to seconds/minutes using TimeUnit class.
 *
 */

public final class TimeConversion {
    public static long getSecondsByMillis(final long millisTime) {
	return TimeUnit.MILLISECONDS.toSeconds(millisTime);
    }
    
    public static long getMinutesByMillis(final long millisTime) {
	return TimeUnit.MILLISECONDS.toMinutes(millisTime);
    }
}
