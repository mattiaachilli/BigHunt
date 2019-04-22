package model.utilities;

/**
 * 
 * This simple class, throws an exception if necessary.
 *
 */

public final class ExceptionRuntimeUtil {

    private ExceptionRuntimeUtil() {
        super();
    };
    /**
     * 
     * @param condition 
     *          if true throws exception.
     * @param exception
     *          to throws.
     */
    public static void checkException(final boolean condition, final RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }
}
