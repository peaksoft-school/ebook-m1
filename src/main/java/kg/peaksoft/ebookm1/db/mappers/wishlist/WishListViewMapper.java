package kg.peaksoft.ebookm1.db.mappers.wishlist;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.wishlist.WishListResponse;
import kg.peaksoft.ebookm1.db.entities.others.WishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WishListViewMapper {

    public WishListResponse viewWishList(WishList wishList) {
        WishListResponse wishListResponse = new WishListResponse();
        wishListResponse.setId(wishList.getId());
        wishListResponse.setCreatedDate(wishList.getCreatedDate());
        wishListResponse.setBook(wishList.getBook());
        wishListResponse.setClientId(wishList.getUser().getId());
        return wishListResponse;
    }

    public List<WishListResponse> viewAllWishLists(List<WishList> wishLists) {
        List<WishListResponse> wishlistResponses = new ArrayList<>();
        for (WishList wishList : wishLists) {
            wishlistResponses.add(viewWishList(wishList));
        }
        return wishlistResponses;
    }
}
