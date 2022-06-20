package kg.peaksoft.ebookm1.api.controllers.payloads.dto.wishlist;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WishListResponse {

    private Long id;
    private LocalDate createdDate;
    private Book book;
    private Long clientId;
}
