package kg.peaksoft.ebookm1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "paper_book_id")
    private PaperBook paperBooks;
    @OneToMany
    @JoinColumn(name = "audio_book_id")
    private AudioBook audioBooks;
    @OneToMany
    @JoinColumn(name = "ebook_id")
    private eBook eBooks;

}
