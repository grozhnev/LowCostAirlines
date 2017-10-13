package exceptions;

import javax.servlet.ServletException;

/**
 * Class for intercept errors fom filters
 */
public class NotLoggedException extends ServletException {
    public NotLoggedException(String message) {
        super(message);
    }
}
