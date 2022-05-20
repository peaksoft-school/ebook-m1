package kg.peaksoft.ebookm1.api.payLoad.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.enums.BookLanguage;
import kg.peaksoft.ebookm1.db.enums.BookType;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookRequest {

    private String title;
    private BookType bookType;
    private String author;
    private String publishingHouse;
    private String aboutTheBook;
    private String bookFragment;
    private BookLanguage language;
    private Date yearOfIssue;
    private int pageVolume;
    private double price;
    private int numberOfBooks;
    private int discount;
    private Boolean bestseller;
}
