package kg.peaksoft.ebookm1.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDTO {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDTO(String message,
                    String description) {
    }
}
