package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payLoad.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payLoad.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Vendor", description = "The Vendor API")
public class VendorController {

    private final VendorService service;

    @PostMapping("/create")
    @Operation(summary = "A user with the Admin role can create.")
    public VendorResponse createVendor(@RequestBody VendorRequest request) {
        return service.create(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "A user with the Admin role can update by id")
    public VendorResponse updateById(@PathVariable Long id, @RequestBody VendorRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "All  users can get by id")
    public VendorResponse getByIdVendor(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "A user with the Admin role can delete by id")
    public VendorResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary = "All users can get all")
    public List<VendorResponse> getAllVendors() {
        return service.getAll();
    }
}
