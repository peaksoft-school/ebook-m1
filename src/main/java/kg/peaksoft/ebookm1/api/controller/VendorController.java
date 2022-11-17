package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payload.book.BookRequest;
import kg.peaksoft.ebookm1.api.payload.book.BookResponse;
import kg.peaksoft.ebookm1.api.payload.promocode.PromoCodeRequest;
import kg.peaksoft.ebookm1.api.payload.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payload.vendor.VendorResponse;
import kg.peaksoft.ebookm1.db.services.BookService;
import kg.peaksoft.ebookm1.db.services.VendorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/vendors")
@Tag(name = "Vendor API", description = "The Vendor endpoints")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VendorController {

    private final VendorService service;
    private final BookService bookService;

    // Vendors
    @Operation(summary = "Method update by id", description = "User with role VENDOR can update")
    @PutMapping("{id}")
    public VendorResponse updateById(@PathVariable Long id, @RequestBody VendorRequest request) {
        log.info("Inside Vendor controller update vendor by id method");
        return service.update(id, request);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @DeleteMapping("{id}")
    public VendorResponse deleteById(@PathVariable Long id) {
        log.info("Inside Vendor controller delete vendor by id method");
        return service.deleteById(id);
    }

    // Books
    @Operation(summary = "Method to add new book", description = "Vendor can add new book to his profile")
    @PostMapping("book/{id}")
    public VendorResponse addBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        log.info("Inside Vendor controller add book to vendor method");
        return service.addBookToVendor(request, id);
    }

    @Operation(summary = "Method to update vendor's book", description = "Vendor can update his books from his book list")
    @PutMapping("book/{vendorId}/{bookId}")
    public VendorResponse updateBook(@PathVariable long vendorId, @PathVariable long bookId, @RequestBody BookRequest request) {
        log.info("Inside Vendor controller update book by id method");
        return service.updateBookVendor(vendorId, bookId, request);
    }

    @Operation(summary = "Method to delete book", description = "Vendor and ADMIN can delete book from his book list")
    @DeleteMapping("book/{vendorId}/{bookId}")
    public VendorResponse deleteBook(@PathVariable long vendorId, @PathVariable long bookId) {
        log.info("Inside the Vendor controller is a method for deleting their books");
        return service.deleteBookVendor(vendorId, bookId);
    }

    @Operation(summary = "Method get all vendor books", description = "Admin can to get all VENDOR'S books from the database")
    @GetMapping("vendor-books/{vendorId}")
    public List<BookResponse> getAllVendorBooks(@PathVariable Long vendorId) {
        log.info("Inside the Vendor controller, method for getting all the books of vendor");
        return bookService.getAllVendorBooks(vendorId);
    }

    // Promo code
    @Operation(summary = "Method to add new promo code", description = "Vendor can add new promo code to his profile")
    @PostMapping("promo/{id}")
    public VendorResponse addPromoCode(@PathVariable long id, @RequestBody PromoCodeRequest promocodeRequest) {
        log.info("Inside Vendor controller add promo code to vendor method");
        return service.addPromoCode(promocodeRequest, id);
    }

    @Operation(summary = "Method to update vendor's promo code", description = "Vendor can update his books from his promo code list")
    @PutMapping("promo/{vendorId}/{promoCodeId}")
    public VendorResponse updatePromoCode(@PathVariable long vendorId, @PathVariable long promoCodeId, @RequestBody PromoCodeRequest promocodeRequest) {
        log.info("Inside the Vendor controller, update the promo code of the vendor method");
        return service.updatePromoCode(promocodeRequest, vendorId, promoCodeId);
    }

    @Operation(summary = "Method to delete book", description = "Vendor and ADMIN can delete promo code from his book list")
    @DeleteMapping("promo/{vendorId}/{promoCodeId}")
    public VendorResponse deletePromoCode(@PathVariable long vendorId, @PathVariable long promoCodeId) {
        log.info("Inside the Vendor controller, delete the promo code of the vendor method");
        return service.deletePromoCode(vendorId, promoCodeId);
    }

}
