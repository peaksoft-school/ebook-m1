package kg.peaksoft.ebookm1.entity.otherClass;

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
    @SequenceGenerator(
            name = "promoCode_sequence",
            sequenceName = "promoCode_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "promoCOde_sequence")
    private Long id;
    private String promoName;
    private LocalDateTime startingDay;
    private LocalDateTime finishingDay;
    private byte percent;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "promoCode_books",
            joinColumns = @JoinColumn(name = "promoCode_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
