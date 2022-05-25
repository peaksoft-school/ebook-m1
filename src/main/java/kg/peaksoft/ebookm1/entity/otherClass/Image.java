package kg.peaksoft.ebookm1.entity.otherClass;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Image {

    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "image_sequence")
    @Column(name = "image_id")
    private Long id;
    private String image;


}
