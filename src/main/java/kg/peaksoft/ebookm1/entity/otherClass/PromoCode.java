package kg.peaksoft.ebookm1.entity.otherClass;

import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "promoCodes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PromoCode {
    @Id
    @SequenceGenerator(
            name = "promoCode_sequence",
            sequenceName = "promoCode_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "promoCOde_sequence")
    private Long id;
    private String promoName;
    private LocalDate startingDay;
    private LocalDate finishingDay;
    private byte percent;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "promoCode_books",
            joinColumns = @JoinColumn(name = "promoCode_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
