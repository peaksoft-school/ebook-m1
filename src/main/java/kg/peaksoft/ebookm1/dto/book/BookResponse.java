package kg.peaksoft.ebookm1.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.dto.enums.BookLanguage;
import kg.peaksoft.ebookm1.entity.book.AudioBook;
import kg.peaksoft.ebookm1.entity.book.EBook;
import kg.peaksoft.ebookm1.entity.book.Genre;
import kg.peaksoft.ebookm1.entity.book.PaperBook;
import kg.peaksoft.ebookm1.entity.others.Promo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookResponse {

    private Long id;
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
    private EBook eBook;
    private PaperBook paperBook;
    private AudioBook audioBook;
    private Genre genre;
    private Promo promo;
}
