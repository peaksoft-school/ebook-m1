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
@Table(name = "eBooks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class eBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "eBook_id")
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private String author;
    private String publishingHouse;
    private String aboutTheBook;
    @Enumerated(EnumType.STRING)
    private Language bookLanguage;
    @DateTimeFormat(pattern ="yyyy")
    private LocalDate yearOfIssue;
    private int pageVolume;
    private int price;
    private int numberOfBooks;
    private int discount;
    private Boolean bestseller;
    @ManyToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Bucket> buckets;
    @OneToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    private  List<Genre> genres;
    @OneToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Image> image;


    }

