package kg.peaksoft.ebookm1.entity.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "favoritesBooks")
@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class FavoritesBooks {

    @Id
    @SequenceGenerator(
            name = "favoritesBooks_sequence",
            sequenceName = "favoritesBooks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "favoritesBooks_sequence")
    @Column(name = "favoritesBooks_id")
    private Long id;
    @OneToMany
    private List<Book> books;


}
