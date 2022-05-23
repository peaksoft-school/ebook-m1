package kg.peaksoft.ebookm1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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

