package kg.peaksoft.ebookm1.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.dto.enums.BookLanguage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookRequest {

    private String image;
    private String title;
    private String author;
    private String publishingHouse;
    private String aboutTheBook;
    private String bookFragment;
    private BookLanguage bookLanguage;
    private int yearOfIssue;
    private int pageVolume;
    private double price;
    private int amountOfBooks;
    private int discount;
    private Boolean bestseller;

    private String eBook;
    private String eBookFragment;

    private String paperBook;
    private String paperBookFragment;

    private String audioBook;
    private String audioBookFragment;

    private String genre;
    private String genreName;
    private int quantityOfBooks;
}
