package kg.peaksoft.ebookm1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "buckets")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Client> client;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PaperBook> paperBooks;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AudioBook> audioBooks;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<eBook> eBooks;
}
