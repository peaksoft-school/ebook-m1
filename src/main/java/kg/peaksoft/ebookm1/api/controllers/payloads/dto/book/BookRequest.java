package kg.peaksoft.ebookm1.api.controllers.payloads.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.db.enums.BookLanguage;
import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
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
    private String audioBookFragment;
    private String eBookFragment;
    private String paperBookFragment;
    private RequestStatus status;
    private String comments;
    private Genre genreEnum;
    private TypeOfBook typeOfBook;
    private Double basketPrice;
}
