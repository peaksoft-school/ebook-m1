package kg.peaksoft.ebookm1.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyException extends RuntimeException {

    private String HttpStatus;
    private String message;

    public MyException(String message, Throwable cause, String httpStatus, String message1) {
        super(message, cause);
        HttpStatus = httpStatus;
        this.message = message1;
    }
}
