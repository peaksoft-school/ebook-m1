package kg.peaksoft.ebookm1.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.ebookm1.entity.book.Book;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promocodes")
@Data
public class Promocode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String promoname;
    private int amountofpromo;
    private LocalDate startingDay;
    private LocalDate finishingDay;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

//    @OneToMany(mappedBy ="promocode")
//    @JsonIgnore
//    List<Book> books;


}
