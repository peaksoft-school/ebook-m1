package kg.peaksoft.ebookm1.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history")
public class HistoryOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_gen")
    @SequenceGenerator(name = "history_gen", sequenceName = "history_seq", allocationSize = 1)
    private Long id;

    private LocalDate createdAt;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlist_id")
    private WishList wishList;

    public HistoryOperation(Basket basket, User user) {
        this.basket = basket;
        this.user = user;
        this.createdAt = LocalDate.now();
    }

    public HistoryOperation(WishList wishList, User user) {
        this.wishList = wishList;
        this.user = user;
        this.createdAt = LocalDate.now();
    }

}
