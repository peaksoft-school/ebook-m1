package kg.peaksoft.ebookm1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "genres")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    @OneToOne
    @JoinColumn(name = "paper_book_id")
    private PaperBook paperBook;
    @OneToOne
    @JoinColumn(name = "audio_book_id")
    private AudioBook audioBook;
    @OneToOne
    @JoinColumn(name = "ebook_id")
    private eBook eBook;

}
