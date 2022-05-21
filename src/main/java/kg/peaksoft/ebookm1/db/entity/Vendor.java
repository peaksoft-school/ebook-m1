package kg.peaksoft.ebookm1.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vebdors")
public class Vendor {

    private static  final String SEQ_NAME = "vendor_generation";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true, length = 30)
    private String email;
    @Column(unique = true, length = 30)
    private String password;
    private Boolean emailConfirm;

    @OneToMany
    @JoinColumn(name = "book_id")
    private List<Book> books;

    @OneToMany
    @JoinColumn(name = "promo_id")
    private List<Promo> promos;
}
