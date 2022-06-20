package kg.peaksoft.ebookm1.db.entities.others;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.ebookm1.db.entities.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history")
public class HistoryOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdDate;

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
        this.createdDate = LocalDate.now();
    }

    public HistoryOperation(WishList wishList, User user) {
        this.wishList = wishList;
        this.user = user;
        this.createdDate = LocalDate.now();
    }
}
