package kg.peaksoft.ebookm1.entity.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audioBooks")
public class AudioBook {

    private static final String SEQ_NAME = "audioBook_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String audioFragment;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
