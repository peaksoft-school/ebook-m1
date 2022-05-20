package kg.peaksoft.ebookm1.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String name;
    @Column(unique = true, length = 30)
    private String email;
    @Column(unique = true, length = 30)
    private String password;
    private Boolean emailConfirm;
}
