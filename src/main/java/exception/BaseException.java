package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException{
    private String code;
    private String message;

    public BaseException(String message, String code) {
        super(message);
        this.code = code;
        this.message=message;
    }

    public BaseException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
        this.message=message;
    }
}
