package kg.peaksoft.ebookm1.entity.otherClass;

import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Genre {
    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genre_sequence")
    @Column(name = "genre_id")
    private Long id;
    private String bookGenre;
    private int quantityOfBooks;


}
