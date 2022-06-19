package kg.peaksoft.ebookm1.api.controllers.payloads.dto.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.entities.others.HistoryOperation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientResponse {

    private Long id;
    private String firstName;
    private String email;
    private LocalDateTime created;
    private boolean isActive;
    private List<HistoryOperation> operationList;
}
