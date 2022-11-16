package kg.peaksoft.ebookm1.db.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promocodes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Promocode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String promoName;
    private int amountOfPromo;
    private LocalDate startingDay;
    private LocalDate finishingDay;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "promocode")
    @JsonIgnore
    List<Book> books;
}
