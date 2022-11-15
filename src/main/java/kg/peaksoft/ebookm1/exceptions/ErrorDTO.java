package kg.peaksoft.ebookm1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {

    private Date timestamp;
    private String message;
    private String details;

}
