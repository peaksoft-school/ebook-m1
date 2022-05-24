package kg.peaksoft.ebookm1.entity.book;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "eBooks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class eBook {

    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "eBook_id")
    private Long id;
    @Column(length = 1234)
    private String bookFragment;




    }

