package kg.peaksoft.ebookm1.entity;

import kg.peaksoft.ebookm1.entity.book.Book;
import kg.peaksoft.ebookm1.entity.otherClass.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vendor_id")
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String phoneNumber;
    private String email;
    @OneToMany
    @JoinColumn(name = "books_book_id")
    private List<Book> books;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
