package kg.peaksoft.ebookm1.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.ebookm1.db.enums.BookLanguage;
import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    private String image;

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

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String comments;

    @Enumerated(EnumType.STRING)
    private TypeOfBook typeOfBook;

    @Enumerated(EnumType.STRING)
    private Genre genreEnum;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne()
    @JoinColumn(name = "promocode_id")
    private Promocode promocode;

}
