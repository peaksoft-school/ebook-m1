package kg.peaksoft.ebookm1.db.mappers.order;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.order.OrderResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.entities.others.Basket;
import kg.peaksoft.ebookm1.db.entities.others.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderViewMapper {

    public OrderResponse viewOrder(Book book,Basket basket,Order order){
        OrderResponse response = new OrderResponse();
        response.setImage(book.getImage());
        response.setQuantity(basket.getQuantity());
        response.setOrderPrice(order.getOrderPrice());
        response.setCreatedDate(order.getTransActionCreatedDate());
        response.setStatus(basket.getStatus());
        return response;
    }
}
