package kg.peaksoft.ebookm1.entity.book;

import kg.peaksoft.ebookm1.entity.otherClass.Genre;
import kg.peaksoft.ebookm1.entity.otherClass.Image;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    private Long id;
    private String title;
    private String author;
    private String publishingHouse;
    private int pageVolume;
    @OneToOne
//    @JoinColumn(name = "genre_id")
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Language bookLanguage;
    @DateTimeFormat(pattern ="yyyy")
    private byte yearOfIssue;
    private int price;
    private int discount;
    private Boolean bestseller;
    @OneToMany
    private List<Image> image;
    @OneToMany
    private List<PaperBook> paperBooks;
    @OneToMany
    private List<AudioBook> audioBooks;
    @OneToMany
    private List<eBook> eBooks;
}
