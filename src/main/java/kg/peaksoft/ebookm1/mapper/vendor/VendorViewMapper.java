package kg.peaksoft.ebookm1.mapper.vendor;

import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class VendorViewMapper {

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

    public List<VendorResponse> viewVendors(List<User> vendors) {
        List<VendorResponse> responses = new ArrayList<>();
        for (User vendor : vendors) {
            responses.add(viewVendor(vendor));
        }
        return responses;
    }
}
