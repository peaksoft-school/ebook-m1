package kg.peaksoft.ebookm1.dto.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AdminRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
