package kg.peaksoft.ebookm1.api.controllers.payloads.dto.history;

import kg.peaksoft.ebookm1.db.entities.others.Basket;
import kg.peaksoft.ebookm1.db.entities.others.WishList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HistoryResponse {

    private Long id;
    private Basket basket;
    private WishList wishList;
    private LocalDate createdDate;
}
