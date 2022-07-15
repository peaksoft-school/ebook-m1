package kg.peaksoft.ebookm1.api.payload.book;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseView {

    List<BookResponse> bookResponses;
}
