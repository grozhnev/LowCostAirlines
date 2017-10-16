package connectionpool;

import java.sql.SQLException;

public class ConnectionPoolException extends SQLException {
    private static final long serialVersionUID = 1L;
    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }
}
