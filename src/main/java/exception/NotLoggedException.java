package exception;

import javax.servlet.ServletException;

public class NotLoggedException extends ServletException {
    public NotLoggedException(String message) {
        super(message);
    }
}