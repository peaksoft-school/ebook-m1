package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.payload.dto.wishlist.WishListRequest;
import kg.peaksoft.ebookm1.api.payload.dto.wishlist.WishListResponse;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.entity.HistoryOperation;
import kg.peaksoft.ebookm1.db.entity.WishList;
import kg.peaksoft.ebookm1.db.entity.security.User;
import kg.peaksoft.ebookm1.db.mapper.WishListViewMapper;
import kg.peaksoft.ebookm1.db.repository.BookRepository;
import kg.peaksoft.ebookm1.db.repository.HistoryOperationRepository;
import kg.peaksoft.ebookm1.db.repository.UserRepository;
import kg.peaksoft.ebookm1.db.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final WishListViewMapper viewMapper;
    private final WishListRepository wishListRepository;
    private final HistoryOperationRepository historyOperationRepo;

    public WishListResponse addWishList(WishListRequest wishListRequest, long clientId) {
        User client = userRepository.findById(clientId).get();
        Book book = bookRepository.findById(wishListRequest.getBookId()).get();
        WishList wishList = new WishList(client, book);
        HistoryOperation wishListOperation = new HistoryOperation(wishList, client);
        historyOperationRepo.save(wishListOperation);
        client.getHistoryOperation().add(wishListOperation);
        log.info("The client adds books to wishlist: {}", book.getTitle());
        return viewMapper.viewWishList(wishListRepository.save(wishList));
    }

    public WishListResponse getWishListById(Long id) {
        log.info("Getting wishlist by id: {}", id);
        return viewMapper.viewWishList(wishListRepository.findById(id).get());
    }

    public void deleteWishList(Long wishListId) {
        log.info("Deleted wishlist by id: {}", wishListId);
        wishListRepository.delete(wishListRepository.findById(wishListId).get());
    }

    public List<WishListResponse> getAllWishLists() {
        log.info("Getting all wishlists");
        return viewMapper.viewAllWishLists(wishListRepository.findAll());
    }
}
