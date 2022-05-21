package kg.peaksoft.ebookm1.entity;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String search;
    @OneToMany
    private List<Book> books;

//    @OneToOne
//    @JoinColumn(name = "paper_book_id")
//    private PaperBook paperBook;
//    @OneToOne
//    @JoinColumn(name = "audio_book_id")
//    private AudioBook audioBook;
//    @OneToOne
//    @JoinColumn(name = "ebook_id")
//    private eBook eBook;


}
