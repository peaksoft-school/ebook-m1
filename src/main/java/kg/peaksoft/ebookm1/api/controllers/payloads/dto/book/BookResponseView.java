package kg.peaksoft.ebookm1.api.controllers.payloads.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookResponseView {

    List<BookResponse> bookResponses;
}
