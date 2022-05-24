package kg.peaksoft.ebookm1.entity;

import kg.peaksoft.ebookm1.entity.book.Book;
import kg.peaksoft.ebookm1.entity.book.Bucket;
import kg.peaksoft.ebookm1.entity.book.FavoritesBooks;
import kg.peaksoft.ebookm1.entity.otherClass.PromoCode;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String passwordConfirm;
    private Boolean mailing;
    private Boolean beVendor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bucket_bucket_id")
    private Bucket bucket;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorites_books_id")
    private FavoritesBooks favoritesBooks;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_book_id_")
    private List<Book> book;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PromoCode> promoCode;
    private LocalDate created;
    private boolean isActive;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))

    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedauthorities =new ArrayList<>();
        for (Role role:roles){
            grantedauthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedauthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
