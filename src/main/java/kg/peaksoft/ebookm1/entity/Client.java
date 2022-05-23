package kg.peaksoft.ebookm1.entity;

import kg.peaksoft.ebookm1.entity.book.Bucket;
import kg.peaksoft.ebookm1.entity.book.FavoritesBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String PhoneNumber;
    private String email;
    private String password;
    private Boolean mailing;
    @OneToOne
    private Bucket bucket;
    @OneToOne
    @JoinColumn(name = "favorites_books_id")
    private FavoritesBooks favoritesBooks;

}
