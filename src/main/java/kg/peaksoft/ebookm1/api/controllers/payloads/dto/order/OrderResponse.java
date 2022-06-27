package kg.peaksoft.ebookm1.api.controllers.payloads.dto.order;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderResponse {

    private String image;
    private String bookName;
    private Integer quantity;
    private Double orderPrice;
    private LocalDate createdDate;
    private PurchaseStatus status;
}
