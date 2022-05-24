package kg.peaksoft.ebookm1.entity.otherClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private String name;


}
