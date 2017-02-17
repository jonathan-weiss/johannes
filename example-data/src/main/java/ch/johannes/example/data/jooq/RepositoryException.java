package ch.johannes.example.data.jooq;

public class RepositoryException extends RuntimeException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Exception exception) {
        super(message, exception);
    }

}
