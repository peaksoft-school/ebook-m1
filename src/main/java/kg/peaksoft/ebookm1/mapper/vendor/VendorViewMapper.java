package kg.peaksoft.ebookm1.mapper.vendor;


import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VendorViewMapper {

    public final RoleRepository roleRepository;

    public VendorResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        VendorResponse response = new VendorResponse();
        if (user.getId() != null) {
            response.setId(Long.valueOf(user.getId()));
        }
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        response.setIsActive(true);
        response.setPassword(user.getPassword());
        response.setBookList(user.getBooks());
        response.setPromocodeList(user.getPromocode());
        return response;
    }




    public List<VendorResponse> viewVendors() {
       List<VendorResponse> vendorUsers = new ArrayList<>();
       Role role = roleRepository.findById(2l).get();

        for (User user: role.getUsers()
             ) {
            vendorUsers.add(viewUser(user));
        }

        return vendorUsers;
    }
}
