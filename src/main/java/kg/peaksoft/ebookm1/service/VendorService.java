package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.mapper.vendor.VendorEditMapper;
import kg.peaksoft.ebookm1.mapper.vendor.VendorViewMapper;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final VendorEditMapper editMapper;
    private final VendorViewMapper viewMapper;

    public VendorResponse create(VendorRequest request) {
        User vendor = editMapper.createVendor(request);
        vendor.setPassword(passwordEncoder.encode(request.getPassword()));
        vendor.isActive();
        repository.save(vendor);
        return viewMapper.viewVendor(vendor);
    }

    public VendorResponse update(Long id, VendorRequest request) {
        User vendor = repository.findById(id).get();
        editMapper.updateVendor(vendor, request);
        return viewMapper.viewVendor(repository.save(vendor));
    }

    public VendorResponse deleteById(Long id) {
        User vendor = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewVendor(vendor);
    }

    public List<VendorResponse> getAllVendors() {
        return viewMapper.viewVendors();
    }
}
