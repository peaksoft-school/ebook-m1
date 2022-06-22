package kg.peaksoft.ebookm1.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket.BasketRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.basket.BasketResponse;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponseView;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.wishlist.WishListRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.wishlist.WishListResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.db.services.BasketService;
import kg.peaksoft.ebookm1.db.services.BookService;
import kg.peaksoft.ebookm1.db.services.ClientService;
import kg.peaksoft.ebookm1.db.services.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
@Tag(name = "Client", description = "The Client API")
public class ClientController {

    private final ClientService userService;
    private final BookService bookService;
    private final BasketService basketService;
    private final WishListService wishListService;

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @Operation(summary = "Method update", description = "A user who has only the CLIENT role can update")
    @PutMapping("client/{id}")
    public ClientResponse updateById(@PathVariable long id, @RequestBody ClientRequest request) {
        return userService.update(request, id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method delete by id", description = "Users with the ADMIN and CLIENT roles can delete")
    @DeleteMapping("client/{id}")
    public ClientResponse deleteById(@PathVariable long id) {
        return userService.deleteById(id);
    }

    // Books
    @Operation(summary = "Method get all books with status-approved", description = "All users can  get all VENDOR'S approved books from the database")
    @GetMapping("/books")
    public List<BookResponse> getAllApprovedBooks(@RequestParam(value = "page", required = false) int page) {
        return bookService.getAllApprovedBooks(page - 1);
    }

    @Operation(summary = "Method get all books by type",
            description = "Allows to get all type books {AUDIO_BOOK,PAPER_BOOK,E_BOOK} from the database")
    @GetMapping("/books/filter")
    public List<BookResponse> getAllApprovedBookByGenreAndType(@RequestParam(value = "genreEnum", required = false) Genre genreEnum,
                                                               @RequestParam(value = "typeOfBook", required = false) TypeOfBook typeOfBook,
                                                               @RequestParam(value = "page", required = false) int page,
                                                               @RequestParam(value = "sort",required = false)String sort
                                                               ) {
        return bookService.getAllApprovedBookByGenreAndType(genreEnum, typeOfBook, page - 1,sort);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a book by ID")
    @GetMapping("/book/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    @Operation(summary = "Allows to search all books from the database")
    @GetMapping("/search")
    public BookResponseView searchAndPagination(@RequestParam(name = "name", required = false) String name,
                                                @RequestParam(value = "page", required = false) Integer page) {
        return bookService.searchAndPagination(name, page-1);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VENDOR','ROLE_CLIENT')")
    @Operation(summary = "Allows to sort all books from the database")
    @GetMapping( "/sort/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Book> sortAndPagination(@PathVariable Integer pageNumber,
                                        @PathVariable Integer pageSize,
                                        @PathVariable String sortProperty) {
        return bookService.sortAndPagination(pageNumber, pageSize, sortProperty);
    }

    //Basket
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @Operation(summary = "Add basket to client", description = "CLIENT can add books to the basket")
    @PostMapping("client/baskets/{clientId}")
    public BasketResponse addBasket(@RequestBody BasketRequest request,
                                    @PathVariable long clientId) {
        return basketService.addBasket(request, clientId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @Operation(summary = "Method update basket", description = "CLIENT can update his basket")
    @PutMapping("client/baskets/{clientId}")
    public BasketResponse updateBasket(@RequestBody BasketRequest request,
                                       @PathVariable long clientId) {
        return basketService.updateBasket(request, clientId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method get basket by ID", description = "The ADMIN and the CLIENT can get the basket by ID")
    @GetMapping("client/baskets/{basketId}")
    public BasketResponse getBasketById(@PathVariable long basketId) {
        return basketService.getBasketById(basketId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method get all baskets", description = "The CLIENT and the ADMIN can get all the baskets")
    @GetMapping("client/baskets")
    public List<BasketResponse> getAllBaskets() {
        return basketService.getAllBaskets();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @Operation(summary = "Method delete basket by ID", description = "The CLIENT can delete his basket")
    @DeleteMapping("client/baskets/{basketId}")
    public void deleteBasketById(@PathVariable long basketId) {
        basketService.deleteBasket(basketId);
    }

    //WishLists
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @Operation(summary = "Add wishlist to client", description = "CLIENT can add books to the wishlist")
    @PostMapping("client/wishlists/{clientId}")
    public WishListResponse addWishlist(@RequestBody WishListRequest request,
                                        @PathVariable long clientId) {
        return wishListService.addWishList(request, clientId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method get wishlist by ID", description = "The ADMIN and the CLIENT can get the wishlist by ID")
    @GetMapping("client/wishlists/{wishlistId}")
    public WishListResponse getWishListById(@PathVariable long wishlistId) {
        return wishListService.getWishListById(wishlistId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method get all wishlists", description = "The CLIENT and the ADMIN can get all the wishlists")
    @GetMapping("client/wishlists")
    public List<WishListResponse> getAllWishLists() {
        return wishListService.getAllWishLists();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @Operation(summary = "Method delete wishlist by ID", description = "The CLIENT can delete his wishlist")
    @DeleteMapping("/wishlists/{wishlistId}")
    public void deleteWishListById(@PathVariable long wishlistId) {
        wishListService.deleteWishList(wishlistId);
    }

    //HistoryOperation
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "CLIENT's history operations", description = "The CLIENT and the ADMIN can get all the CLIENT's histories")
    @GetMapping("client/history/{clientId}")
    public ClientResponse getUserHistory(@PathVariable long clientId) {
        return userService.getClientHistory(clientId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method delete history by ID", description = "The CLIENT and ADMIN can delete history operation")
    @DeleteMapping("client/history/{clientId}")
    public void deleteClientHistory(@PathVariable long clientId) {
        userService.deleteClientHistory(clientId);
    }
}
