package kg.peaksoft.ebookm1.entity.book;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "favoritesBooks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class FavoritesBooks {

    @Id
    @SequenceGenerator(
            name = "favoritesBooks_sequence",
            sequenceName = "favoritesBooks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "favoritesBooks_sequence")
    @Column(name = "favorites_book_id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "favorites_books_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "favorites_book_id"))
    private List<Book> books;
    private int quantityOfBooks;



}
