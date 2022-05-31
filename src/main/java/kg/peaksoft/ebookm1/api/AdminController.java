package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.service.AdminService;
import kg.peaksoft.ebookm1.service.UserService;
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
    private final UserService clientService;
    private final AdminService adminService;

    //Admin
    @Operation(summary = "Method get all admins", description = " ")
    @GetMapping("/getAllAdmins")
    public List<AdminResponse> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Vendor
    @Operation(summary = "Method get by id", description = "Allows all users to get a VENDOR by ID")
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

    @Operation(summary = "Method all vendors", description = "Allows to get all VENDORS from the database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getAllVendors")
    public List<VendorResponse> getAllVendors() {
        return vendorService.getAllVendors();
    }



    // Client
    @GetMapping("/getClientById/{id}")
    @Operation(summary = "Allows all users to get a user by ID")
    public UserResponse getByIdUser(@PathVariable long id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/deleteClientById/{id}")
    @Operation(summary = "User with role ADMIN can delete")
    public UserResponse deleteByIdUser(@PathVariable long id) {
        return clientService.deleteById(id);
    }

    @Operation(summary = "Method all clients", description = "Admin to get all CLIENTS from the database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getAllClients")
    public List<UserResponse> getAllClients() {
        return clientService.getAllClients();
    }
}
