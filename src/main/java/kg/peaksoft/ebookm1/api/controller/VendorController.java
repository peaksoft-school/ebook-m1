package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.playLoads.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.vendor.VendorResponse;
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
}
