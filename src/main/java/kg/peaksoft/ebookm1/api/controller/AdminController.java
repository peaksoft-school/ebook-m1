package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payload.dto.basket.BasketResponse;
import kg.peaksoft.ebookm1.api.payload.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payload.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.payload.dto.book.BookResponseView;
import kg.peaksoft.ebookm1.api.payload.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.api.payload.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.api.payload.dto.wishlist.WishListResponse;
import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.db.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Admin", description = "The Admin API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@RequestMapping("/api/admin")
public class AdminController {

    private final VendorService vendorService;
    private final BookService bookService;
    private final ClientService userService;
    private final BasketService basketService;
    private final WishListService wishListService;

    // Vendors
    @Operation(summary = "Method all vendors", description = "Admin to get all VENDORS from the database")
    @GetMapping("/vendors")
    public List<VendorResponse> getAllVendors() {
        log.info("Inside Admin controller get all vendors method");
        return vendorService.getAllVendors();
    }

    @Operation(summary = "Vendor's book count method", description = "Admin can to get all VENDOR'S count books from the database")
    @GetMapping("/count-books/{vendorId}")
    public String countBooks(@PathVariable Long vendorId) {
        log.info("Inside Admin controller count vendor books method");
        return bookService.countBooks(vendorId);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @DeleteMapping("/vendor/{vendorId}")
    public ResponseEntity<String> deleteById(@PathVariable Long vendorId) {
        vendorService.deleteById(vendorId);
        log.info("Inside Admin controller delete vendor by id method");
        return new ResponseEntity<>("Successfully removed vendor by id: " + vendorId, HttpStatus.OK);
    }

    @Operation(summary = "Method get by id", description = "Admin can get the vendor by id and view the profile.")
    @GetMapping("/vendor-profile/{vendorId}")
    public VendorResponse getVendorById(@PathVariable Long vendorId) {
        log.info("Inside Admin controller get vendor by id method");
        return vendorService.gitById(vendorId);
    }

    // Books
    @Operation(summary = "Method get all vendor books", description = "Admin can to get all VENDOR'S books from the database")
    @GetMapping("/vendor-books/{vendorId}")
    public List<BookResponse> getAllVendorBooks(@PathVariable Long vendorId) {
        log.info("Inside Admin controller get all vendor books method");
        return bookService.getAllVendorBooks(vendorId);
    }

    @Operation(summary = "Method get all books", description = "Allows to get all books from the database")
    @GetMapping("/books")
    public List<BookResponse> getAllBooks() {
        log.info("Inside Admin controller get all books method");
        return bookService.getAllBooks();
    }

    @Operation(summary = "Method get all books with status-submitted", description = "Admin can  get all VENDOR'S submitted books from the database")
    @GetMapping("/book-request")
    public List<BookResponse> getAllSubmittedBooks(@RequestParam(value = "page", required = false) int page) {
        log.info("Inside Admin controller get all submitted books method");
        return bookService.getAllSubmittedBooks(page - 1);
    }

    @Operation(summary = "Method update by id", description = "User with role ADMIN can change request status " +
            "to following words:APPROVED OR REJECTED." +
            "In case of changing to REJECT, admin, should describe his decision")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_VENDOR')")
    @PutMapping("/book-request/{bookId}")
    public BookResponse updateBook(@PathVariable Long bookId, @RequestBody BookRequest request) {
        log.info("Inside the Admin controller the method of changing the status of the book and adding comments");
        return bookService.updateRequestStatus(bookId, request);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Allows to filter by GENRE and TYPE-OF-BOOK from the database")
    @GetMapping("/book/filter")
    public List<BookResponse> filter(@RequestParam(value = "genreEnum", required = false) Genre genreEnum,
                                     @RequestParam(value = "typeofbook", required = false) TypeOfBook typeOfBook,
                                     @RequestParam(value = "page", required = false) int page) {
        log.info("Inside the admin controller is the filter book method");
        return bookService.filterByGenreAndTypeOfBooks(genreEnum, typeOfBook, page - 1);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Allows to search all books from the database")
    @GetMapping("/search")
    public BookResponseView searchAndPagination(@RequestParam(name = "name", required = false)
                                                        String name, @RequestParam int page) {
        log.info("Inside Admin controller search and pagination book method");
        return bookService.searchAndPagination(name, page - 1);
    }

    //Clients
    @Operation(summary = "Method all clients", description = "Admin to get all CLIENTS from the database")
    @GetMapping("/clients")
    public List<ClientResponse> getAllClients() {
        log.info("Inside the Admin controller is the method of getting all clients");
        return userService.getAllClients();
    }

    @Operation(summary = "A user with the ADMIN role can get clients by Id from the database")
    @GetMapping("/client-profile/{clientId}")
    public ClientResponse getById(@PathVariable Long clientId) {
        log.info("Inside the Admin controller is the method of getting client by id");
        return userService.getById(clientId);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN and CLIENT can delete")
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteByIdClient(@PathVariable Long id) {
        log.info("Inside the administrator controller, the method of deleting the client by id");
        userService.deleteById(id);
        return new ResponseEntity<>("Successfully removed client by id: " + id, HttpStatus.OK);
    }

    //Baskets
    @Operation(summary = "Method get basket by ID", description = "The ADMIN and the CLIENT can get the basket by ID")
    @GetMapping("/baskets/{basketId}")
    public BasketResponse getBasketById(@PathVariable long basketId) {
        log.info("Inside the administrator controller is the method of getting the basket by id");
        return basketService.getBasketById(basketId);
    }

    @Operation(summary = "Method get all baskets", description = "The CLIENT and the ADMIN can get all the baskets")
    @GetMapping("/baskets")
    public List<BasketResponse> getAllBaskets() {
        log.info("Inside Admin controller get all baskets method");
        return basketService.getAllBaskets();
    }

    @Operation(summary = "Method get all purchased books", description = "The CLIENT and the ADMIN can get all the purchased books")
    @GetMapping("/purchased-books")
    public List<BasketResponse> getAllPurchasedBooks() {
        log.info("Inside Admin controller get all purchased-books method");
        return basketService.getAllPurchasedBooks();
    }

    //Wishlists
    @Operation(summary = "Method get wishlist by ID", description = "The ADMIN and the CLIENT can get the wishlist by ID")
    @GetMapping("/wishlists/{wishlistId}")
    public WishListResponse getWishListById(@PathVariable long wishlistId) {
        log.info("Inside the administrator controller is the method of getting the wishlist by id");
        return wishListService.getWishListById(wishlistId);
    }

    @Operation(summary = "Method get all wishlists", description = "The CLIENT and the ADMIN can get all the wishlists")
    @GetMapping("/wishlists")
    public List<WishListResponse> getAllWishLists() {
        log.info("Inside Admin controller get all wishlists method");
        return wishListService.getAllWishLists();
    }

    //HistoryOperation
    @Operation(summary = "CLIENT's history operation", description = "CLIENT's can have the history operation")
    @GetMapping("/history/{clientId}")
    public ClientResponse getUserHistory(@PathVariable long clientId) {
        log.info("Inside the Admin controller, the view client history method");
        return userService.getClientHistory(clientId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    @Operation(summary = "Method delete history by ID", description = "The CLIENT and ADMIN can delete history operation")
    @DeleteMapping("/delete-history/{clientId}")
    public void deleteClientHistory(@PathVariable long clientId) {
        log.info("Inside the controller admin, the delete client history method");
        userService.deleteClientHistory(clientId);
    }
}
