package kg.peaksoft.ebookm1.entity.book;

import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "promoCodes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String promoName;
    private LocalDateTime startingDay;
    private LocalDateTime finishingDay;
    private byte percent;
    @OneToMany
    private List<Book> books;
}
