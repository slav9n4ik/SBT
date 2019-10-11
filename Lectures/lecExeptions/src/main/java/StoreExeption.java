import java.util.List;

public class StoreExeption extends RuntimeException {

    private List<XlsError> errors;

    static class XlsError {
        private int row;
        private int column;
        //private ErrorType errorType;
        private String reason;
    }


    public StoreExeption() {
    }

    public StoreExeption(String message) {
        super(message);
    }

    public StoreExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreExeption(Throwable cause) {
        super(cause);
    }

    public StoreExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
