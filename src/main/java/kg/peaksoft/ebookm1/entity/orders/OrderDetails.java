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
    @Column(name = "order_details_id")
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    private int bookOfAmount;
    private int discount;
    private double sum;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "promoCode_id")
    private PromoCode promoCode;
    private Double totalPrice;
}
