package kg.peaksoft.ebookm1.mapper.vendor;

import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        List<Role> roleList = new ArrayList<>();
        Role role  = roleRepository.findById(2L).get();
        roleList.add(role);
        user.setRoles(roleList);
        return user;
    }

    public User updateVendor(User user, VendorRequest vendorRequest) {
        user.setFirstName(vendorRequest.getFirstName());
        user.setLastName(vendorRequest.getLastName());
        user.setPhoneNumber(vendorRequest.getPhoneNumber());
        user.setEmail(vendorRequest.getEmail());
        user.setPassword(vendorRequest.getPassword());
        return user;
    }
}
