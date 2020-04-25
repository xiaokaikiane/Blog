package exception;

public class SystemException extends BaseException {
    public SystemException(String message, String code) {
        super(message, code);
    }

    public SystemException(String message, Throwable cause, String code) {
        super(message, cause, code);
    }
}
