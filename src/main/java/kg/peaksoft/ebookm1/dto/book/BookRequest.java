package kg.peaksoft.ebookm1.dto.book;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    private String name;
    private int price;
    private Long promocodeid;

}
