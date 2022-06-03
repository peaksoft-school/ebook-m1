package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
@PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
@Tag(name = "Vendor", description = "The Vendor API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VendorController {

    private final VendorService service;

    @Operation(summary = "Method update by id", description = "User with role VENDOR can update")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PutMapping("{id}")
    public VendorResponse updateById(@PathVariable Long id, @RequestBody VendorRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("{id}")
    public VendorResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
    @Operation(summary = "Method to add new book", description = "Vendor can add new book to his profile")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping("/book/{id}")
    public VendorResponse addBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return service.addBookToVendor(request, id);
    }

    @Operation(summary = "Method to update vendor's book", description = "Vendor can update his books from his book list")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PutMapping("/book/{vendorId}/{bookId}")
    public VendorResponse updateBook(@PathVariable long vendorId, @PathVariable long bookId, @RequestBody BookRequest request) {
        return service.updateBookVendor(vendorId, bookId, request);
    }

    @Operation(summary = "Method to delete book", description = "Vendor and ADMIN can delete book from his book list")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("/book/{vendorId}/{bookId}")
    public VendorResponse deleteBook(@PathVariable long vendorId, @PathVariable long bookId) {
        return service.deleteBookVendor(vendorId, bookId);
    }

    @Operation(summary = "Method to add new promocode", description = "Vendor can add new promocode to his profile")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping("/promo/{id}")
    public VendorResponse addPromoCode(@PathVariable long id, @RequestBody PromocodeRequest promocodeRequest) {
        return service.addPromocode(promocodeRequest, id);
    }

    @Operation(summary = "Method to update vendor's promocode", description = "Vendor can update his books from his promocode list")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PutMapping("/promo/{vendorId}/{promoCodeId}")
    public VendorResponse updatePromoCode(@PathVariable long vendorId, @PathVariable long promoCodeId, @RequestBody PromocodeRequest promocodeRequest) {
        return service.updatePromocode(promocodeRequest, vendorId, promoCodeId);
    }

    @Operation(summary = "Method to delete book", description = "Vendor and ADMIN can delete promocode from his book list")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("/promo/{vendorId}/{promoCodeId}")
    public VendorResponse deletePromocode(@PathVariable long vendorId, @PathVariable long promoCodeId) {
        return service.deletePromocode(vendorId, promoCodeId);
    }

}
