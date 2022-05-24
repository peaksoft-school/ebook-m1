package kg.peaksoft.ebookm1.entity.orders;

import kg.peaksoft.ebookm1.entity.book.Book;
import kg.peaksoft.ebookm1.entity.otherClass.Address;
import kg.peaksoft.ebookm1.entity.otherClass.PromoCode;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderDetails")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
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
    @JoinTable(name = "order_orderdetails",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "orderDetails_id"))
    @JoinColumn(name = "order_order_id")
    private Address address;
    private int bookOfAmount;
    private int discount;
    private double sum;
    @OneToOne
    @JoinColumn(name = "promo_code_id")
    private PromoCode promoCode;
    private Double totalPrice;
}
