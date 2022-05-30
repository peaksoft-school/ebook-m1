package kg.peaksoft.ebookm1.entity.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.ebookm1.dto.enums.BookLanguage;
import kg.peaksoft.ebookm1.entity.Promocode;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.entity.others.Basket;
import kg.peaksoft.ebookm1.entity.others.Favorite;
import kg.peaksoft.ebookm1.entity.others.Promo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
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
    private String image;
    @Column(unique = true)
    private String title;
    private String author;
    private String publishingHouse;
    @Column(length = 1500)
    private String aboutTheBook;
    @Column(length = 9500)
    private String bookFragment;
    @Enumerated(EnumType.STRING)
    private BookLanguage bookLanguage;
    @Column(length = 4)
    private int yearOfIssue;
    private int pageVolume;
    private double price;
    private int amountOfBooks;
    private int discount;
    private Boolean bestseller;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eBook_id")
    private EBook eBook;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paperBook_id")
    private PaperBook paperBook;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audioBook_id")
    private AudioBook audioBook;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promo_id")
    private Promo promo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocode_id")
    private Promocode promocode;

}
