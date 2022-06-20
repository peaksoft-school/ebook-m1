package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket.BasketRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket.BasketResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.entities.others.Basket;
import kg.peaksoft.ebookm1.db.entities.others.HistoryOperation;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import kg.peaksoft.ebookm1.db.mappers.basket.BasketEditMapper;
import kg.peaksoft.ebookm1.db.mappers.basket.BasketViewMapper;
import kg.peaksoft.ebookm1.db.repositories.BasketRepository;
import kg.peaksoft.ebookm1.db.repositories.BookRepository;
import kg.peaksoft.ebookm1.db.repositories.HistoryOperationRepository;
import kg.peaksoft.ebookm1.db.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BasketViewMapper viewMapper;
    private final BasketEditMapper editMapper;
    private final HistoryOperationRepository historyOperationRepo;

    public BasketResponse addBasket(BasketRequest basketRequest, long clientId) {
        User client = userRepository.findById(clientId).get();
        Book book = bookRepository.findById(basketRequest.getBookId()).get();
        Basket basket = new Basket(basketRequest.getQuantity(), book, client, basketRequest.getPurchaseStatus());
        HistoryOperation basketOperation = new HistoryOperation(basket, client);
        historyOperationRepo.save(basketOperation);
        client.getHistoryOperation().add(basketOperation);
        return viewMapper.viewBasket(basketRepository.save(basket));
    }

    public BasketResponse updateBasket(BasketRequest basketRequest, long clientId) {
        return viewMapper.viewBasket(basketRepository.save(editMapper.updateBasket
                (basketRepository.findById(basketRequest.getBasketId()).get(), basketRequest)));
    }

    public BasketResponse getBasketById(Long id) {
        return viewMapper.viewBasket(basketRepository.findById(id).get());
    }

    public void deleteBasket(Long basketId) {
        basketRepository.delete(basketRepository.findById(basketId).get());
    }

    public List<BasketResponse> getAllBaskets() {
        return viewMapper.viewAllBaskets(basketRepository.findAll());
    }

    public List<BasketResponse> getAllPurchasedBooks() {
        return viewMapper.viewAllBaskets(basketRepository.findAllByStatus(PurchaseStatus.FINISHED));
    }
}
