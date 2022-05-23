package kg.peaksoft.ebookm1.entity;

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
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany
    private List<Book> books;


}
