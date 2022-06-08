package kg.peaksoft.ebookm1.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
@Tag(name = "Vendor", description = "The Vendor API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VendorController {

    private final VendorService service;

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
    @Operation(summary = "Method to add new audio book", description = "Vendor can add new book to his profile")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PostMapping("/audio-book/{id}")
    public VendorResponse addAudioBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return service.addAudioBookToVendor(request, id);
    }

    @Operation(summary = "Method to add new electronic book", description = "Vendor can add new book to his profile")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PostMapping("/e-book/{id}")
    public VendorResponse addEBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return service.addEBookToVendor(request, id);
    }

    @Operation(summary = "Method to add new paper book", description = "Vendor can add new book to his profile")
    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @PostMapping("/paper-book/{id}")
    public VendorResponse addPaperBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return service.addPaperBookToVendor(request, id);
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
