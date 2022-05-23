package kg.peaksoft.ebookm1.entity.otherClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "genres")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private int NumberOfBooks;


}
