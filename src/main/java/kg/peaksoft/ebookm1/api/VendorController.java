package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
@PreAuthorize("hasAnyAuthority('ADMIN','VENDOR')")
@Tag(name = "Vendor", description = "The vendors API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VendorController {

    private final VendorService userService;

    @Operation(summary = "Method create", description = "User with role VENDOR can create")
//    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping
    public VendorResponse createVendor(@RequestBody VendorRequest request) {
        return userService.create(request);
    }

    @Operation(summary = "Method update by id", description = "User with role VENDOR can update")
//    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PutMapping("{id}")
    public VendorResponse updateVendor(@PathVariable long id,
                                       @RequestBody VendorRequest request) {
        return userService.update(request, id);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a VENDOR by ID")
    @GetMapping("{id}")
    public VendorResponse getByIdVendor(@PathVariable long id,
                                        @RequestBody VendorRequest request) {
        return userService.getById(id);
    }

    @Operation(summary = "<Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("{id}")
    public VendorResponse deleteByIdVendor(@PathVariable long id,
                                           @RequestBody VendorRequest request) {
        return userService.deleteById(id);
    }

    @Operation(summary = "Method all", description = "Allows to get all VENDORS from the database")
    @GetMapping
    public List<VendorResponse> getAllVendor() {
        return userService.getAllVendors();
    }

    @Operation(summary = "New book added to vendors profile")
    @PostMapping("/addBook/{id}")
    public VendorResponse addBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return userService.addBookToVendor(request, id);
    }

    @Operation(summary = "Update book of vendors profile")
    @PutMapping("/updateBook/{vendorId}/{bookId}")
    public VendorResponse updateBook(@PathVariable long vendorId, @PathVariable long bookId, @RequestBody BookRequest request) {
        return userService.updateBookVendor(vendorId, bookId, request);
    }

    @Operation(summary = "Delete book from vendors profile")
    @DeleteMapping("/deleteBook/{vendorId}/{bookId}")
    public VendorResponse deleteBook(@PathVariable long vendorId, @PathVariable long bookId) {
        return userService.deleteBookVendor(vendorId, bookId);
    }

    @Operation(summary = "New promocode added to vendors profile")
    @PostMapping("/addPromocode/{id}")
    public VendorResponse addPromocode(@PathVariable long id, @RequestBody PromocodeRequest promocodeRequest) {
        return userService.addPromocode(promocodeRequest, id);
    }

    @Operation(summary = "Update promocode of vendors profile")
    @PutMapping("/updatePromocode/{vendorId}/{promoCodeId}")
    public VendorResponse updatePromocode(@PathVariable long vendorId, @PathVariable long promoCodeIdId, @RequestBody PromocodeRequest promocodeRequest) {
        return userService.updatePromocode(promocodeRequest, vendorId, promoCodeIdId);
    }

    @Operation(summary = "Delete promocode from vendors profile")
    @DeleteMapping("/deletePromocode/{vendorId}/{promoCodeId}")
    public VendorResponse deletePromocode(@PathVariable long vendorId, @PathVariable long promoCodeId) {
        return userService.deletePromocode(vendorId, promoCodeId);
    }

}
