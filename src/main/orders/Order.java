package kg.peaksoft.ebookm1.entity.orders;

import kg.peaksoft.ebookm1.entity.Client;
import kg.peaksoft.ebookm1.entity.otherClass.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_sequence")
    @Column(name = "order_id")
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int sum;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "order_address",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Address address;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "client_orders",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Client> client;
}
