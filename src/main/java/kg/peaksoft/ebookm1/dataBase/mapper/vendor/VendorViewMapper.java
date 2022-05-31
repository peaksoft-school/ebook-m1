package kg.peaksoft.ebookm1.dataBase.mapper.vendor;

import kg.peaksoft.ebookm1.api.playLoads.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.dataBase.entity.Role;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import kg.peaksoft.ebookm1.dataBase.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VendorViewMapper {

    private final RoleRepository roleRepository;

    public VendorResponse viewVendor(User vendor) {
        if (vendor == null) {
            return null;
        }
        VendorResponse response = new VendorResponse();
        if (vendor.getId() != null) {
            response.setId(vendor.getId());
        }
        response.setFirstName(vendor.getFirstName());
        response.setLastName(vendor.getLastName());
        response.setPhoneNumber(vendor.getPhoneNumber());
        response.setEmail(vendor.getEmail());
        response.setCreated(vendor.getCreated());
        response.setIsActive(true);
        return response;
    }

    public List<VendorResponse> viewVendors() {
        List<VendorResponse> vendorUsers = new ArrayList<>();
        Role role = roleRepository.findById(2L).get();
        for (User user : role.getUsers()) {
            vendorUsers.add(viewVendor(user));
        }
        return vendorUsers;
    }
}
