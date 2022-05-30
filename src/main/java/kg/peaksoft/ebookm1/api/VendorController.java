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
@CrossOrigin
@RequestMapping("/api/vendors")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Vendor", description = "The vendors API")
public class VendorController {

    private final VendorService userService;

    @PostMapping
    @Operation(summary = "New Vendor user creation")
    public VendorResponse createVendor(@RequestBody VendorRequest request) {
        return userService.create(request);
    }

    @PostMapping("/addBook/{id}")
    @Operation(summary = "New book added to vendors profile")
    public VendorResponse addBookToVendor(@PathVariable long id, @RequestBody BookRequest request) {
        return userService.addBookToVendor(request, id);
    }

    @PutMapping("/updateBook/{vendorId}/{bookId}")
    @Operation(summary = "Update book of vendors profile")
    public VendorResponse updateBook(@PathVariable long vendorId, @PathVariable long bookId, @RequestBody BookRequest request) {
        return userService.updateBookVendor(vendorId, bookId, request);
    }

    @DeleteMapping("/deleteBook/{vendorId}/{bookId}")
    @Operation(summary = "Delete book from vendors profile")
    public VendorResponse deleteBook(@PathVariable long vendorId, @PathVariable long bookId) {
        return userService.deleteBookVendor(vendorId, bookId);
    }


    @PostMapping("/addPromocode/{id}")
    public VendorResponse addPromocode(@PathVariable long id, @RequestBody PromocodeRequest promocodeRequest) {
        return userService.addPromocode(promocodeRequest, id);
    }

    @PutMapping("{id}")
    public VendorResponse updateUser(@PathVariable long id,
                                     @RequestBody VendorRequest request) {
        return userService.update(request, id);
    }

    @GetMapping("{id}")
    public VendorResponse getByIdUser(@PathVariable long id,
                                      @RequestBody VendorRequest request) {
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public VendorResponse deleteByIdUser(@PathVariable long id,
                                         @RequestBody VendorRequest request) {
        return userService.deleteById(id);
    }

    @GetMapping
    public List<VendorResponse> getAllUsers() {
        return userService.getAllUsers();
    }

}
