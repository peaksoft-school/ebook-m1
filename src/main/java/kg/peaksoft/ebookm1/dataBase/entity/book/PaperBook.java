package kg.peaksoft.ebookm1.dataBase.entity.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paperBooks")
public class PaperBook {

    private static final String SEQ_NAME = "paperBook_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @Column(length = 950)
    private String paperBookFragment;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
