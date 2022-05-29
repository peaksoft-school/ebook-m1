package kg.peaksoft.ebookm1.mapper.vendor;

import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VendorEditMapper {

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
