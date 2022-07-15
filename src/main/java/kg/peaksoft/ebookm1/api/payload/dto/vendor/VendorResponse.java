package kg.peaksoft.ebookm1.api.payload.dto.vendor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.entity.Promocode;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VendorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDateTime created;
    private Boolean isActive;
    private List<Book> bookList;
    private List<Promocode> promocodes;
}
