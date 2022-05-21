package kg.peaksoft.ebookm1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Long id;
    @Column(name = "bucket_books")  //research again
    private List<Bucket> buckets;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PaperBook> paperBooks;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AudioBook> audioBooks;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<eBook> eBooks;
}
