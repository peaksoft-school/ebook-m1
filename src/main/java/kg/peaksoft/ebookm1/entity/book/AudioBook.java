package kg.peaksoft.ebookm1.entity.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "audioBooks")
@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class AudioBook {
    @Id
    @SequenceGenerator(
            name = "audioBook_sequence",
            sequenceName = "audioBook_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "audioBook_sequence")
    @Column(name = "audio_id")
    private Long id;
    private String bookFragment;
}

