package kg.peaksoft.ebookm1.api.payLoad.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
