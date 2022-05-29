package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
@PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
@Tag(name = "Vendor", description = "The Vendor API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VendorController {

    private final VendorService service;

    @Operation(summary = "Method create", description = "User with role VENDOR can create")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping
    public VendorResponse createVendor(@RequestBody VendorRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Method update by id", description = "User with role VENDOR can update")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PutMapping("{id}")
    public VendorResponse updateById(@PathVariable Long id, @RequestBody VendorRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a VENDOR by ID")
    @GetMapping("{id}")
    public VendorResponse getVendorById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "<Method delete by id", description = "User with role ADMIN and VENDOR can deleted")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("{id}")
    public VendorResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Method all", description = "Allows to get all VENDORS from the database")
    @GetMapping
    public List<VendorResponse> getAllVendors() {
        return service.getAll();
    }
}
