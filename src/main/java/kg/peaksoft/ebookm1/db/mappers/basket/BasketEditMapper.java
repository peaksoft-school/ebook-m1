package kg.peaksoft.ebookm1.db.mappers.basket;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket.BasketRequest;
import kg.peaksoft.ebookm1.db.entities.others.Basket;
import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketEditMapper {

    public Basket updateBasket(Basket basket, BasketRequest basketRequest) {
        basket.setQuantity(basketRequest.getQuantity());
        basket.setStatus(basketRequest.getPurchaseStatus());
        return basket;
    }
//    public Basket orderCalculation(Basket basket, BasketRequest basketRequest, Promocode promocode) {
//        if (promocode.getPromoName() == null) {
//
//        }basketRequest.getPromocode
//        basket.setQuantity(basketRequest.getQuantity());
//        basket.setStatus(basketRequest.getPurchaseStatus());
//        return basket;
//    }
}
