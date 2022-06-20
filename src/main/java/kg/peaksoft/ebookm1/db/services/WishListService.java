package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.wishlist.WishListRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.wishlist.WishListResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.entities.others.HistoryOperation;
import kg.peaksoft.ebookm1.db.entities.others.WishList;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.db.mappers.wishlist.WishListViewMapper;
import kg.peaksoft.ebookm1.db.repositories.BookRepository;
import kg.peaksoft.ebookm1.db.repositories.HistoryOperationRepository;
import kg.peaksoft.ebookm1.db.repositories.UserRepository;
import kg.peaksoft.ebookm1.db.repositories.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return viewMapper.viewWishList(wishListRepository.save(wishList));
    }

    public WishListResponse getWishListById(Long id) {
        return viewMapper.viewWishList(wishListRepository.findById(id).get());
    }

    public void deleteWishList(Long wishListId) {
        wishListRepository.delete(wishListRepository.findById(wishListId).get());
    }

    public List<WishListResponse> getAllWishLists() {
        return viewMapper.viewAllWishLists(wishListRepository.findAll());
    }
}
