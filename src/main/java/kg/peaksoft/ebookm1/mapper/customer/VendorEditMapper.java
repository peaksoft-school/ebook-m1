package kg.peaksoft.ebookm1.mapper.customer;

import kg.peaksoft.ebookm1.dto.customer.VendorRequest;
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

    public User createCustomer(VendorRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        List<Role> roleList = new ArrayList<>();
        Role role  = roleRepository.findById(2L).get();
        roleList.add(role);
        user.setRoles(roleList);
        return user;
    }

    public User updateUser(User user, VendorRequest customerRequest) {
        user.setFirstName(customerRequest.getFirstName());
        user.setLastName(customerRequest.getLastName());
        user.setEmail(customerRequest.getEmail());
        user.setPassword(customerRequest.getPassword());
        return user;
    }
}
