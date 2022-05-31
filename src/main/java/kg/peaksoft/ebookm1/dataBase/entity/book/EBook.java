package kg.peaksoft.ebookm1.dataBase.entity.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eBooks")
public class EBook {

    private static final String SEQ_NAME = "eBook_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @Column(length = 950)
    private String eBookFragment;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
