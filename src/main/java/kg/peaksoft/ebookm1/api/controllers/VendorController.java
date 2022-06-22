package kg.peaksoft.ebookm1.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.db.services.BookService;
import kg.peaksoft.ebookm1.db.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
@Tag(name = "Vendor", description = "The Vendor API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VendorController {

    private final VendorService service;
    private final BookService bookService;

    // Vendors
    @Operation(summary = "Method update by id", description = "User with role VENDOR can update")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PutMapping("{id}")
    public VendorResponse updateById(@PathVariable Long id, @RequestBody VendorRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_VENDOR')")
    @DeleteMapping("{id}")
    public VendorResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    // Books
    @Operation(summary = "Method to add new book", description = "Vendor can add new book to his profile")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PostMapping("/book/{id}")
    public VendorResponse addBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return service.addBookToVendor(request, id);
    }

    @Operation(summary = "Method to update vendor's book", description = "Vendor can update his books from his book list")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PutMapping("/book/{vendorId}/{bookId}")
    public VendorResponse updateBook(@PathVariable long vendorId, @PathVariable long bookId, @RequestBody BookRequest request) {
        return service.updateBookVendor(vendorId, bookId, request);
    }

    @Operation(summary = "Method to delete book", description = "Vendor and ADMIN can delete book from his book list")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_VENDOR')")
    @DeleteMapping("/book/{vendorId}/{bookId}")
    public VendorResponse deleteBook(@PathVariable long vendorId, @PathVariable long bookId) {
        return service.deleteBookVendor(vendorId, bookId);
    }

    @Operation(summary = "Method get all vendor books", description = "Admin can to get all VENDOR'S books from the database")
    @GetMapping("/vendor-books/{vendorId}")
    public List<BookResponse> getAllVendorBooks(@PathVariable Long vendorId) {
        return bookService.getAllVendorBooks(vendorId);
    }

    // Promo code
    @Operation(summary = "Method to add new promo code", description = "Vendor can add new promo code to his profile")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PostMapping("/promo/{id}")
    public VendorResponse addPromoCode(@PathVariable long id, @RequestBody PromocodeRequest promocodeRequest) {
        return service.addPromocode(promocodeRequest, id);
    }

    @Operation(summary = "Method to update vendor's promo code", description = "Vendor can update his books from his promo code list")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PutMapping("/promo/{vendorId}/{promoCodeId}")
    public VendorResponse updatePromoCode(@PathVariable long vendorId, @PathVariable long promoCodeId, @RequestBody PromocodeRequest promocodeRequest) {
        return service.updatePromocode(promocodeRequest, vendorId, promoCodeId);
    }

    @Operation(summary = "Method to delete book", description = "Vendor and ADMIN can delete promo code from his book list")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_VENDOR')")
    @DeleteMapping("/promo/{vendorId}/{promoCodeId}")
    public VendorResponse deletePromoCode(@PathVariable long vendorId, @PathVariable long promoCodeId) {
        return service.deletePromocode(vendorId, promoCodeId);
    }
}
