package kg.peaksoft.ebookm1.entity.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "paperBooks")
@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor

public class PaperBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paperBook_id")
    private Long id;
    private String bookFragment;

}
