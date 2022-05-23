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
@NoArgsConstructor
@AllArgsConstructor
public class AudioBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "audio_id")
    private Long id;
    private String bookFragment;
//    public AudioBook audioBook;
}

