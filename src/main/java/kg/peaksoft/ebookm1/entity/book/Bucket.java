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
@NoArgsConstructor
@AllArgsConstructor
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany
    private List<Book> books;
    private int amountOfBooks;

}
