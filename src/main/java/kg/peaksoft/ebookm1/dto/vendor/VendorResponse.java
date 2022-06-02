package kg.peaksoft.ebookm1.dto.vendor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VendorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDateTime created;
    private Boolean isActive;
}
