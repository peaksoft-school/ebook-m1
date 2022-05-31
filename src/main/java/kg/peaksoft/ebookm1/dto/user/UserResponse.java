package kg.peaksoft.ebookm1.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResponse {

    private Long id;
    private String firstName;
    private String email;
    private LocalDateTime created;
    private boolean isActive;
}
