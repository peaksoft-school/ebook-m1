package kg.peaksoft.ebookm1.api.payload.basket;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasketRequest {

    private Long basketId;
    private Long bookId;
    private Integer quantity;
    private PurchaseStatus purchaseStatus;
}
