package kg.peaksoft.ebookm1.entity.book;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "paperBooks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaperBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paperBook_id")
    private Long id;
    @Column(length = 1234)
    private String fragment;


}
