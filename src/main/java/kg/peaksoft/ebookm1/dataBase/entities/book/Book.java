package kg.peaksoft.ebookm1.dataBase.entities.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.ebookm1.api.payloads.dto.enums.BookLanguage;
import kg.peaksoft.ebookm1.dataBase.entities.others.Basket;
import kg.peaksoft.ebookm1.dataBase.entities.others.Favorite;
import kg.peaksoft.ebookm1.dataBase.entities.others.Promocode;
import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "promocode_id")
    private Promocode promocode;
}
