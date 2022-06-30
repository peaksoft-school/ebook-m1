package kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasketResponse {

    private Long basketId;
    private LocalDate createdDate;
    private Book book;
    private Long clientId;
    private Integer quantity;
    private PurchaseStatus purchaseStatus;
    private Double basketPrice;
}
