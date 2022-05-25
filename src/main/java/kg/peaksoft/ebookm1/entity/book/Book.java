package kg.peaksoft.ebookm1.entity.book;

import kg.peaksoft.ebookm1.entity.enumClass.BookType;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
import kg.peaksoft.ebookm1.entity.otherClass.Genre;
import kg.peaksoft.ebookm1.entity.otherClass.Image;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_sequence")
    @Column(name = "book_id")
    private Long id;
    private String title;
    private String author;
    private String publishingHouse;
    @OneToOne(cascade = CascadeType.ALL)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Language bookLanguage;
    private BookType bookType;
    private int pageVolume;
    @Column(length = 1234)
    private String aboutTheBook;
    private String bookFragment;
    @Column(length =4)
    private int yearOfIssue;
    private double price;
    private int discount;
    private Boolean bestseller;
    private int amountOfBooks;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "images_book",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Image> image;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "paperBook_id")
    private PaperBook paperBooks;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
   @JoinColumn(name = "audioBook_id")
    private AudioBook audioBooks;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "eBook_id")
    private eBook eBooks;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "bucket_id")
//    private Bucket bucket;
//    @ManyToOne(cascade = CascadeType.ALL)
//    private PromoCode promoCode;
//    @ManyToOne(cascade = CascadeType.ALL)
//    private FavoritesBooks favoritesBooks;
}
