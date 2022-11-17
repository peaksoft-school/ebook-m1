package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.payload.basket.BasketRequest;
import kg.peaksoft.ebookm1.api.payload.basket.BasketResponse;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.entity.Basket;
import kg.peaksoft.ebookm1.db.entity.HistoryOperation;
import kg.peaksoft.ebookm1.db.entity.User;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import kg.peaksoft.ebookm1.db.mapper.BasketEditMapper;
import kg.peaksoft.ebookm1.db.mapper.BasketViewMapper;
import kg.peaksoft.ebookm1.db.repository.BasketRepository;
import kg.peaksoft.ebookm1.db.repository.BookRepository;
import kg.peaksoft.ebookm1.db.repository.HistoryOperationRepository;
import kg.peaksoft.ebookm1.db.repository.UserRepository;
import kg.peaksoft.ebookm1.exceptions.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
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
        User client = userRepository.findById(clientId).orElseThrow(() ->
                new NoSuchElementException(User.class, clientId));
        Book book = bookRepository.findById(basketRequest.getBookId()).orElseThrow(() ->
                new NoSuchElementException(Book.class, basketRequest.getBookId()));
        double amountOfBook = book.getAmountOfBooks() - (basketRequest.getQuantity());
        book.setAmountOfBooks((int) amountOfBook);
        Basket basket = new Basket(basketRequest.getQuantity(), book, client, basketRequest.getPurchaseStatus());
        HistoryOperation basketOperation = new HistoryOperation(basket, client);
        historyOperationRepo.save(basketOperation);
        client.getHistoryOperation().add(basketOperation);
        log.info("The client adds books to basket: {}", book.getId() + " - book id");
        return viewMapper.viewBasket(basketRepository.save(basket));
    }

    public BasketResponse updateBasket(BasketRequest request, long clientId) {
        log.info("Updating the contents of the shopping basket: ");
        return viewMapper.viewBasket(basketRepository.save(editMapper.updateBasket
                (basketRepository.findById(request.getBasketId()).orElseThrow(() ->
                        new NoSuchElementException(Basket.class, request.getBasketId())), request)));
    }

    public BasketResponse getBasketById(Long id) {
        log.info("Getting basket by id: {}", id + " - book id");
        return viewMapper.viewBasket(basketRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(Basket.class, id)));
    }

    public void deleteBasket(Long basketId) {
        log.info("Deleted basket by id: {}", basketId + " - basket id");
        Basket basket = basketRepository.findById(basketId).orElseThrow(() ->
                new NoSuchElementException(Basket.class, basketId));
        int addBackAmountOfBook = basket.getBook().getAmountOfBooks() + basket.getQuantity();
        basket.getBook().setAmountOfBooks(addBackAmountOfBook);
        basketRepository.deleteById(basket.getId());
    }

    public List<BasketResponse> getAllBaskets() {
        log.info("Getting all baskets: ");
        return viewMapper.viewAllBaskets(basketRepository.findAll());
    }

    public List<BasketResponse> getAllPurchasedBooks() {
        log.info("Getting all purchased books");
        return viewMapper.viewAllBaskets(basketRepository.findAllByStatus(PurchaseStatus.FINISHED));
    }

    public BasketResponse promoCodeCalculation(Long baskedId, Long bookId, String promoName) {
        Basket basket = basketRepository.findById(baskedId).orElseThrow(() ->
                new NoSuchElementException(Basket.class, baskedId));
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new NoSuchElementException(Book.class, bookId));
        if (promoName.matches("(.*)" + book.getPromoCode().getPromoName() + "(.*)")) {
            LocalDate currentTime = LocalDate.now();
            LocalDate expirationDate = (book.getPromoCode().getFinishingDay());
            long day = currentTime.until(expirationDate, ChronoUnit.DAYS);
            if (day >= 0) {
                double percentage = (basket.getBasketPrice() * book.getPromoCode().getAmountOfPromo()) / 100;
                double discount = basket.getBasketPrice() - percentage;
                basket.setBasketPrice(discount);
                basket.setStatus(PurchaseStatus.FINISHED);
                log.info("Promo code successfully activated: " + basket.getBasketPrice());
            } else {
                basket.setBasketPrice(book.getPrice());
                log.info("Promo code expired");
            }
        } else {
            basket.setBasketPrice(book.getPrice());
            log.info("Promo code not valid");
        }
        return viewMapper.viewBasket(basketRepository.save(basket));
    }

}
