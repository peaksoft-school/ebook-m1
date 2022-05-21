package kg.peaksoft.ebookm1.api.payLoad.dto.vendor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VendorRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
