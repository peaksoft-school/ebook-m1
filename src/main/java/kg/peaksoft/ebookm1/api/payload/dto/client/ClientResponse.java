package kg.peaksoft.ebookm1.api.payload.dto.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.entity.HistoryOperation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientResponse {

    private Long id;
    private String firstName;
    private String email;
    private LocalDateTime created;
    private boolean isActive;
    private List<HistoryOperation> operationList;
}
