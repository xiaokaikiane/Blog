package exception;

public class BusinessException extends BaseException{
    public BusinessException(String message, String code) {
        super(message, code);
    }
    public BusinessException(String message, Throwable cause, String code) {
        super(message, cause, code);
    }
}
