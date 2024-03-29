package kg.peaksoft.ebookm1.db.mapper;

import kg.peaksoft.ebookm1.api.payload.basket.BasketRequest;
import kg.peaksoft.ebookm1.db.entity.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketEditMapper {

    public Basket updateBasket(Basket basket, BasketRequest request) {
        basket.setQuantity(request.getQuantity());
        basket.setStatus(request.getPurchaseStatus());
        return basket;
    }

}
