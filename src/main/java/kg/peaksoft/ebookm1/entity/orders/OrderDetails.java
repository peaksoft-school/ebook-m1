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
    @SequenceGenerator(
            name = "orderDetails_sequence",
            sequenceName = "orderDetails_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orderDetails_sequence")
    @Column(name = "orderDetails_id")
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "orderDetails",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "orderDetails_id"))
    @JoinColumn(name = "order_order_id")
    private Order order;
    private int amount;
    private int price;
    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "orderDetails_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "orderDetails_id"))
    private List<Book> books;
}
