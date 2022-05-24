package kg.peaksoft.ebookm1.entity.book;

import kg.peaksoft.ebookm1.entity.book.Book;
import kg.peaksoft.ebookm1.entity.orders.OrderDetails;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "buckets")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Bucket {
    @Id
    @SequenceGenerator(
            name = "bucket_sequence",
            sequenceName = "bucket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bucket_sequence")
    @Column(name = "bucket_id")
    private Long id;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Book> books;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetails_id")
    private List<OrderDetails> orderDetails;
    private int amountOfBooks;

}
