package kg.peaksoft.ebookm1.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.ebookm1.db.entity.security.User;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private LocalDate createdDate;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
    private Double basketPrice;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonIgnore
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "user_id")
    private User client;

    public Basket(Integer quantity, Book book, User client, PurchaseStatus status) {
        this.quantity = quantity;
        this.createdDate = LocalDate.now();
        this.book = book;
        this.client = client;
        this.status = status;
        this.basketPrice = book.getPrice();
    }
}
