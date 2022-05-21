package kg.peaksoft.ebookm1.entity;

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
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private int sum;
    private String address;
    @ManyToMany
    private List<Client> client;
    @OneToMany
    private List<OrderDetails> orderDetails;

}
