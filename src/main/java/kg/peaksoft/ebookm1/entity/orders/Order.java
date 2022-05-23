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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int sum;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany
    private List<Client> client;
}
