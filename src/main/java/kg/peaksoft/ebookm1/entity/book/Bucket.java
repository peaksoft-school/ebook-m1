package kg.peaksoft.ebookm1.entity.book;

import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "buckets")
@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class Bucket {
    @Id
    @SequenceGenerator(
            name = "bucket_sequence",
            sequenceName = "bucket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bucket_sequence")
    @Column(name = "bucket_id")
    private Long id;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "bucket_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "bucket_id"))
    private List<Book> books;
    private int amountOfBooks;

}
