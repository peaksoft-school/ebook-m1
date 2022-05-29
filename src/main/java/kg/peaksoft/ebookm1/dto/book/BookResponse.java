package kg.peaksoft.ebookm1.dto.book;

import kg.peaksoft.ebookm1.entity.Promocode;
import kg.peaksoft.ebookm1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String name;
    private User user;
    private Promocode promocode;
}
