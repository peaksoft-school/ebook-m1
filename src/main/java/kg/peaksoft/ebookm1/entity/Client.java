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
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "client_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String PhoneNumber;
    private String email;
    private String password;
    private Boolean mailing;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "client_buckets",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Bucket bucket;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "client_favoritesBooks",
            joinColumns = @JoinColumn(name = "favoritesBooks_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private FavoritesBooks favoritesBooks;

    @OneToOne
    @JoinTable(name = "client_details",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private User user;

}
