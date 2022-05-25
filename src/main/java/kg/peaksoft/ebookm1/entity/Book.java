package kg.peaksoft.ebookm1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String book;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;



}