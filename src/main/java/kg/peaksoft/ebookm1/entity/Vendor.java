package kg.peaksoft.ebookm1.entity;

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
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PaperBook> paperBooks;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AudioBook> audioBooks;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<eBook> eBooks;
}
