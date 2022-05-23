package kg.peaksoft.ebookm1.entity.otherClass;

import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "searches")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    @Id
    @SequenceGenerator(
            name = "search_sequence",
            sequenceName = "search_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "search_sequence")
    @Column(name = "search_id")
    private Long id;
    private String search;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "search_books",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

}
