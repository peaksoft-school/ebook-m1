package kg.peaksoft.ebookm1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "paperBooks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PaperBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paperBook_id")
    private Long id;
    private String title;
    private String author;
    private String publishingHouse;
    private String aboutTheBook;
    private int price;
    private int numberOfBooks;
    private int pageVolume;
    private int discount;
    @DateTimeFormat(pattern ="yyyy")
    private LocalDate yearOfIssue;
    private Boolean bestseller;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    @Enumerated(EnumType.STRING)
    private Language bookLanguage;
    @ManyToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Bucket> buckets;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Genre> genres;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Image> image;
}
