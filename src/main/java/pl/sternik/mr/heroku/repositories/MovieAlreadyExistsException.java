package pl.sternik.mr.heroku.repositories;

public class MovieAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public MovieAlreadyExistsException() {
    }

    public MovieAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MovieAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieAlreadyExistsException(String message) {
        super(message);
    }

    public MovieAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
