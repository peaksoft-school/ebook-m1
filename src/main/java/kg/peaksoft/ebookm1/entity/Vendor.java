package kg.peaksoft.ebookm1.entity;

//import kg.peaksoft.ebookm1.entity.book.Book;
//import kg.peaksoft.ebookm1.entity.otherClass.Address;
import kg.peaksoft.ebookm1.entity.book.Book;
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
    @SequenceGenerator(
            name = "vendor_sequence",
            sequenceName = "vendor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "vendor_sequence")
    @Column(name = "vendor_id")
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "address_vendor",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "vendor_id"))
//    private Address address;
    private String phoneNumber;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vendor_books",
            joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

}
