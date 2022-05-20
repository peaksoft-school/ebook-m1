package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.api.payLoad.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payLoad.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.db.mapper.vendor.VendorEditMapper;
import kg.peaksoft.ebookm1.db.mapper.vendor.VendorViewMapper;
import kg.peaksoft.ebookm1.db.repository.VendorRepository;
import kg.peaksoft.ebookm1.db.entity.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository repository;
    private final VendorEditMapper editMapper;
    private final VendorViewMapper viewMapper;

    public VendorResponse create(VendorRequest request) {
        Vendor vendor = editMapper.createVendor(request);
        repository.save(vendor);
        return viewMapper.viewVendor(vendor);
    }

    public VendorResponse update(Long id, VendorRequest request) {
        Vendor vendor = repository.getById(id);
        editMapper.updateVendor(vendor, request);
        return viewMapper.viewVendor(repository.save(vendor));
    }

    public VendorResponse getById(Long id) {
        Vendor vendor = repository.getById(id);
        return viewMapper.viewVendor(vendor);
    }

    public VendorResponse deleteById(Long id) {
        Vendor vendor = repository.getById(id);
        repository.deleteById(id);
        return viewMapper.viewVendor(vendor);
    }

    public List<VendorResponse> getAll() {
        return viewMapper.viewVendors(repository.findAll());
    }
}
