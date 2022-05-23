package kg.peaksoft.ebookm1.entity.orders;

import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderDetails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_order_id")
    private Order order;
    private int amount;
    private int price;
    @ManyToMany
    private List<Book> books;
}
