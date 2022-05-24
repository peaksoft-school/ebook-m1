package kg.peaksoft.ebookm1.entity.otherClass;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_sequence")
    @Column(name = "address_id")
    private Long id;
    private String country;
    private String city;
    private String address;
    private int postCode;
}
