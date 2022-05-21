package kg.peaksoft.ebookm1.entity;

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
    @JoinColumn(name = "order_id")
    private Order order;
    private int amount;
    private int price;
    @OneToMany
    private List<Book> books;

//    private List<PaperBook> paperBooks;
//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<AudioBook> audioBooks;
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<eBook> eBooks;
}
