package kg.peaksoft.ebookm1.api.payload.history;

import kg.peaksoft.ebookm1.db.entity.Basket;
import kg.peaksoft.ebookm1.db.entity.WishList;
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
