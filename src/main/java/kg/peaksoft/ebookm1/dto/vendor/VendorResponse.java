package kg.peaksoft.ebookm1.dto.vendor;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.entity.Promocode;
import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VendorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Boolean isActive;
    private List<Book> bookList;
    private List<Promocode> promocodeList;
}
