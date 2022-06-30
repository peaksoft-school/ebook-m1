package kg.peaksoft.ebookm1.db.mappers.basket;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket.BasketResponse;
import kg.peaksoft.ebookm1.db.entities.others.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketViewMapper {

    public BasketResponse viewBasket(Basket basket) {
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setBasketId(basket.getId());
        basketResponse.setCreatedDate(basket.getCreatedDate());
        basketResponse.setBook(basket.getBook());
        basketResponse.setClientId(basket.getClient().getId());
        basketResponse.setQuantity(basket.getQuantity());
        basketResponse.setPurchaseStatus(basket.getStatus());
        basketResponse.setBasketPrice(basket.getBasketPrice());
        return basketResponse;
    }

    public List<BasketResponse> viewAllBaskets(List<Basket> baskets) {
        List<BasketResponse> basketResponses = new ArrayList<>();
        for (Basket basket : baskets) {
            basketResponses.add(viewBasket(basket));
        }
        return basketResponses;
    }
}
