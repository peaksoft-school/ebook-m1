package kg.peaksoft.ebookm1.api.playLoads.dto.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientResponse {

    private Long id;
    private String firstName;
    private String email;
    private LocalDateTime created;
    private boolean isActive;
}
