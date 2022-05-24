package kg.peaksoft.ebookm1.entity.book;

//import kg.peaksoft.ebookm1.entity.otherClass.Genre;
//import kg.peaksoft.ebookm1.entity.otherClass.Image;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
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
    private int pageVolume;
//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "book_genres",
//            joinColumns = @JoinColumn(name = "genre_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
////    @JoinColumn(name = "genre_id")
//    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Language bookLanguage;
    @DateTimeFormat(pattern ="yyyy")
    private byte yearOfIssue;
    private int price;
    private int discount;
    private Boolean bestseller;
//    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "images_book",
//            joinColumns = @JoinColumn(name = "image_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Image> image;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "paper_books",
            joinColumns = @JoinColumn(name = "paperBook_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<PaperBook> paperBooks;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
     @JoinTable(name = "audio_books",
            joinColumns = @JoinColumn(name = "audioBook_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<AudioBook> audioBooks;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
     @JoinTable(name = "electronic_books",
            joinColumns = @JoinColumn(name = "eBook_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<eBook> eBooks;
}
