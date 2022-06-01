package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.service.BookService;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "The Admin API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class AdminController {

    private final VendorService vendorService;
    private final BookService bookService;

    // Vendor
    @Operation(summary = "Method get by id", description = "User with role ADMIN to get a VENDOR by Id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getVendorById/{id}")
    public VendorResponse getVendorById(@PathVariable Long id) {
        return vendorService.getById(id);
    }

    @Operation(summary = "<Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("/deleteVendorById/{id}")
    public VendorResponse deleteById(@PathVariable Long id) {
        return vendorService.deleteById(id);
    }

    @Operation(summary = "Method all vendors", description = "Admin to get all VENDORS from the database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getAllVendors")
    public List<VendorResponse> getAllVendors() {
        return vendorService.getAllVendors();
    }

    //Book
    @Operation(summary = "Method get all books", description = "Allows to get all books from the database")
    @GetMapping("/getAllBooks")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
}
