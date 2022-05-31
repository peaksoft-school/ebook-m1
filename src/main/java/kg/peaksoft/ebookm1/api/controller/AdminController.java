package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.api.playLoads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.api.playLoads.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.service.AdminService;
import kg.peaksoft.ebookm1.service.BookService;
import kg.peaksoft.ebookm1.service.ClientService;
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
    private final ClientService clientService;
    private final AdminService adminService;
    private final BookService bookService;

    //Admin
    @Operation(summary = "Method get all admins", description = "ADMINS from the database")
    @GetMapping("/getAllAdmins")
    public List<AdminResponse> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @Operation(summary = "Method update", description = "User with role ADMIN can update")
    @GetMapping("/updateAdmin/{id}")
    public AdminResponse updateAdmin(@PathVariable Long id, @RequestBody AdminRequest request) {
        return adminService.updateAdmin(id, request);
    }

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

    // Client
    @Operation(summary = "Method get Client by id",description = "User with role ADMIN get a client by Id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getClientById/{id}")
    public ClientResponse getClientById(@PathVariable long id) {
        return clientService.getById(id);
    }

    @Operation(summary = "Method delete Client by id",description = "User with role ADMIN can delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("/deleteClientById/{id}")
    public ClientResponse deleteClientById(@PathVariable long id) {
        return clientService.deleteById(id);
    }

    @Operation(summary = "Method all clients", description = "Admin to get all CLIENTS from the database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getAllClients")
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    //Book
    @Operation(summary = "Method get all books", description = "Allows to get all books from the database")
    @GetMapping("/getAllBooks")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
}
