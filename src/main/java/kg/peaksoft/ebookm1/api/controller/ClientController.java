package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payload.basket.BasketRequest;
import kg.peaksoft.ebookm1.api.payload.basket.BasketResponse;
import kg.peaksoft.ebookm1.api.payload.book.BookResponse;
import kg.peaksoft.ebookm1.api.payload.book.BookResponseView;
import kg.peaksoft.ebookm1.api.payload.client.ClientRequest;
import kg.peaksoft.ebookm1.api.payload.client.ClientResponse;
import kg.peaksoft.ebookm1.api.payload.promocode.PromocodeResponse;
import kg.peaksoft.ebookm1.api.payload.wishlist.WishListRequest;
import kg.peaksoft.ebookm1.api.payload.wishlist.WishListResponse;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.db.services.BasketService;
import kg.peaksoft.ebookm1.db.services.BookService;
import kg.peaksoft.ebookm1.db.services.ClientService;
import kg.peaksoft.ebookm1.db.services.PromoService;
import kg.peaksoft.ebookm1.db.services.WishListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/clients")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Client API", description = "The client endpoints")
public class ClientController {

    private final ClientService userService;
    private final BookService bookService;
    private final BasketService basketService;
    private final WishListService wishListService;
    private final PromoService promoService;

    @Operation(summary = "Method update", description = "A user who has only the CLIENT role can update")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping("{id}")
    public ClientResponse updateById(@PathVariable long id, @RequestBody ClientRequest request) {
        log.info("Inside the client controller, the update method client by id");
        return userService.update(request, id);
    }

    @Operation(summary = "Method delete by id", description = "Users with the ADMIN and CLIENT roles can delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @DeleteMapping("/{id}")
    public ClientResponse deleteById(@PathVariable long id) {
        log.info("Inside the client controller, the delete method  client by id");
        return userService.deleteById(id);
    }

    // Books
    @Operation(summary = "Method get all books with status-approved", description = "All users can  get all VENDOR'S approved books from the database")
    @GetMapping("/books")
    public List<BookResponse> getAllApprovedBooks(@RequestParam(value = "page", required = false) int page) {
        log.info("Inside the client controller get all approved books");
        return bookService.getAllApprovedBooks(page - 1);
    }

    @Operation(summary = "Method get all books by type", description = "Allows to get all type books {AUDIO_BOOK,PAPER_BOOK,E_BOOK} from the database")
    @GetMapping("/books/filter")
    public List<BookResponse> getAllApprovedBookByGenreAndType(@RequestParam(value = "genreEnum", required = false) Genre genreEnum,
                                                               @RequestParam(value = "typeOfBook", required = false) TypeOfBook typeOfBook,
                                                               @RequestParam(value = "page", required = false) int page
    ) {
        log.info("Inside the client controller, method for filtering approved books by Genre and Type");
        return bookService.getAllApprovedBookByGenreAndType(genreEnum, typeOfBook, page - 1);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a book by ID")
    @GetMapping("/book/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        log.info("Inside the client controller, method getting book by id");
        return bookService.getBookById(id);
    }

    @Operation(summary = "Allows to search all books from the database")
    @GetMapping("/search")
    public BookResponseView searchAndPagination(@RequestParam(name = "name", required = false) String name,
                                                @RequestParam(value = "page", required = false) Integer page) {
        log.info("Inside the client controller, method for searching and pagination");
        return bookService.searchAndPagination(name, page - 1);
    }

    @Operation(summary = "Allows to sort all books from the database")
    @GetMapping("/sort/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Book> sortAndPagination(@PathVariable Integer pageNumber,
                                        @PathVariable Integer pageSize,
                                        @PathVariable String sortProperty) {
        log.info("Inside client controller, method for sorting and pagination");
        return bookService.sortAndPagination(pageNumber, pageSize, sortProperty);
    }

    //Basket
    @Operation(summary = "Add basket to client", description = "CLIENT can add books to the basket")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping("/baskets/{clientId}")
    public BasketResponse addBasket(@RequestBody BasketRequest request,
                                    @PathVariable long clientId) {
        log.info("Inside the client controller, method add book in basket");
        return basketService.addBasket(request, clientId);
    }

    @Operation(summary = "Method update basket", description = "CLIENT can update his basket")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping("/baskets/{clientId}")
    public BasketResponse updateBasket(@RequestBody BasketRequest request,
                                       @PathVariable long clientId) {
        log.info("Inside the client controller, update basket method");
        return basketService.updateBasket(request, clientId);
    }

    @Operation(summary = "Method get basket by ID", description = "The ADMIN and the CLIENT can get the basket by ID")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/baskets/{basketId}")
    public BasketResponse getBasketById(@PathVariable long basketId) {
        log.info("Inside the client controller, method getting basket by id");
        return basketService.getBasketById(basketId);
    }

    @Operation(summary = "Method get all baskets", description = "The CLIENT and the ADMIN can get all the baskets")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/baskets")
    public List<BasketResponse> getAllBaskets() {
        log.info("Inside the client controller, method get all baskets");
        return basketService.getAllBaskets();
    }

    @Operation(summary = "Method delete basket by ID", description = "The CLIENT can delete his basket")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @DeleteMapping("/baskets/{basketId}")
    public void deleteBasketById(@PathVariable long basketId) {
        log.info("Inside the client controller, method delete basket by id");
        basketService.deleteBasket(basketId);
    }

    //WishLists
    @Operation(summary = "Add wishlist to client", description = "CLIENT can add books to the wishlist")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping("/wishlists/{clientId}")
    public WishListResponse addWishlist(@RequestBody WishListRequest request,
                                        @PathVariable long clientId) {
        log.info("Inside the client controller, method add book in wishlist");
        return wishListService.addWishList(request, clientId);
    }

    @Operation(summary = "Method get wishlist by ID", description = "The ADMIN and the CLIENT can get the wishlist by ID")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/wishlists/{wishlistId}")
    public WishListResponse getWishListById(@PathVariable long wishlistId) {
        log.info("Inside the client controller, method getting wishlist by id");
        return wishListService.getWishListById(wishlistId);
    }

    @Operation(summary = "Method get all wishlists", description = "The CLIENT and the ADMIN can get all the wishlists")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/wishlists")
    public List<WishListResponse> getAllWishLists() {
        log.info("Inside the client controller, get all wishlists method");
        return wishListService.getAllWishLists();
    }

    @Operation(summary = "Method delete wishlist by ID", description = "The CLIENT can delete his wishlist")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @DeleteMapping("/wishlists/{wishlistId}")
    public void deleteWishListById(@PathVariable long wishlistId) {
        log.info("Inside the client controller, method delete wishlist by id");
        wishListService.deleteWishList(wishlistId);
    }

    //HistoryOperation
    @Operation(summary = "CLIENT's history operations", description = "The CLIENT and the ADMIN can get all the CLIENT's histories")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/history/{clientId}")
    public ClientResponse getUserHistory(@PathVariable long clientId) {
        log.info("Inside the Client controller, the view client history method");
        return userService.getClientHistory(clientId);
    }

    @Operation(summary = "Method delete history by ID", description = "The CLIENT and ADMIN can delete history operation")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @DeleteMapping("/history/{clientId}")
    public void deleteClientHistory(@PathVariable long clientId) {
        log.info("Inside the Client controller, the delete client history method");
        userService.deleteClientHistory(clientId);
    }

    @Operation(summary = "Method can get all books with promocode", description = "The CLIENT can get all book with promocode")
    @GetMapping("/promo-code-activation")
    public PromocodeResponse activationOfPromoCode(@RequestParam(value = "promoName", required = false) String promoName
    ) {
        log.info("Promo code is activated: ");
        return promoService.getPromocodeByName(promoName);
    }

    @Operation(summary = "Method for managing discount operations", description = "The CLIENT can check promocode whether his promocode valid or not")
    @PutMapping("/promo-code-managing/{basketId}/{bookId}")
    public BasketResponse basketPromo(@PathVariable(name = "basketId") long basketId, @PathVariable(name = "bookId") long bookId,
                                      @RequestParam(name = "name") String name) {
        log.info("Promo code checked for validation: ");
        return basketService.promoCodeCalculation(basketId, bookId, name);
    }

}
