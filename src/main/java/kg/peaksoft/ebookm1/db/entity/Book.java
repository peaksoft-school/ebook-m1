package kg.peaksoft.ebookm1.db.entity;

import kg.peaksoft.ebookm1.db.enums.BookLanguage;
import kg.peaksoft.ebookm1.db.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    private static final String SEQ_NAME = "book_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private String author;
    private String publishingHouse;
    @Column(length = 1234)
    private String aboutTheBook;
    @Column(length = 9234)
    private String bookFragment;
    private BookLanguage language;
    private Date yearOfIssue;
    private int pageVolume;
    private double price;
    private int numberOfBooks;
    private int discount;
    private Boolean bestseller;

//    @ManyToMany
//    @JoinTable(name = "books_books", joinColumns = @JoinColumn(name = "book_id"),
//    inverseJoinColumns = @JoinColumn(name = "basket_id"))
//    private List<Basket> baskets;
//
//    @OneToMany
//    @JoinColumn(name = "genre_id")
//    private List<Genre> genres;
//
//    @OneToMany
//    @JoinColumn(name = "image_id")
//    private List<Image> images;

//    @ManyToMany
//    @JoinTable(name = "books_paperBooks", joinColumns = @JoinColumn(name = "book_id"),
//    inverseJoinColumns = @JoinColumn(name = "paperBook_id"))
//    private List<PaperBook> paperBooks;
//
//    @ManyToMany
//    @JoinTable(name = "books_audioBooks", joinColumns = @JoinColumn(name = "book_id"),
//    inverseJoinColumns = @JoinColumn(name = "audioBook_id"))
//    private List<AudioBook> audioBooks;
//
//    @ManyToMany
//    @JoinTable(name = "books_electronicBooks", joinColumns = @JoinColumn(name = "book_id"),
//    inverseJoinColumns = @JoinColumn(name = "electronicBook_id"))
//    private List<ElectronicBook> electronicBooks;

}
