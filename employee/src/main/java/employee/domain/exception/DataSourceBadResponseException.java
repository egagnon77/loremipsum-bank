package employee.domain.exception;

public class DataSourceBadResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataSourceBadResponseException(String message) {
        super(message);
    }
}

