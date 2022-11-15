package kg.peaksoft.ebookm1.api.payload.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthResponse {

    private String jwtToken;
    private String message;
    private String firstName;
    private String email;
    private Set<String> authorities;

}
