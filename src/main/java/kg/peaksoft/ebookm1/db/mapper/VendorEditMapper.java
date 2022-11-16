package kg.peaksoft.ebookm1.db.mapper;

import kg.peaksoft.ebookm1.api.payload.vendor.VendorRequest;
import kg.peaksoft.ebookm1.db.entity.Role;
import kg.peaksoft.ebookm1.db.entity.User;
import kg.peaksoft.ebookm1.db.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VendorEditMapper {

    private final RoleRepository roleRepository;

    public User createVendor(VendorRequest request) {
        if (request == null) {
            return null;
        }
        User vendor = new User();
        vendor.setFirstName(request.getFirstName());
        vendor.setLastName(request.getLastName());
        vendor.setPhoneNumber(request.getPhoneNumber());
        vendor.setEmail(request.getEmail());
        vendor.setPassword(request.getPassword());
        vendor.setCreated(LocalDateTime.now());
        vendor.setActive(true);
        
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById(2L).get();
        roles.add(role);
        vendor.setRoles(roles);
        return vendor;
    }

    public User updateVendor(User vendor, VendorRequest request) {
        vendor.setFirstName(request.getFirstName());
        vendor.setLastName(request.getLastName());
        vendor.setPhoneNumber(request.getPhoneNumber());
        vendor.setEmail(request.getEmail());
        vendor.setPassword(request.getPassword());
        return vendor;
    }
}
