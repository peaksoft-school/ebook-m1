package kg.peaksoft.ebookm1.entity.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "eBooks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class eBook {

    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "eBook_id")
    private Long id;
    private String bookFragment;
    @Transient
    private eBook eBook;



    }

