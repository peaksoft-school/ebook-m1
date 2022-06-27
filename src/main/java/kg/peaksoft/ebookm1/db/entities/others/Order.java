package kg.peaksoft.ebookm1.db.entities.others;

import kg.peaksoft.ebookm1.db.entities.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    private double orderPrice;
    private LocalDate transActionCreatedDate;
    @OneToOne()
    @JoinColumn(name = "basket_id")
    private Basket basket;

}
