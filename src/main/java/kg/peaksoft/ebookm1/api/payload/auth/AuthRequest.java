package kg.peaksoft.ebookm1.api.payload.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String email;
    private String password;

}
