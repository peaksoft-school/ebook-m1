package kg.peaksoft.ebookm1.entity.book;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "audioBooks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
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
    private String audioFragment;

}

