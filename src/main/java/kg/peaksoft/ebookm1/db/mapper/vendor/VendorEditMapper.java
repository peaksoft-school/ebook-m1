package kg.peaksoft.ebookm1.db.mapper.vendor;

import kg.peaksoft.ebookm1.api.payLoad.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.db.entity.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorEditMapper {

    public Vendor createVendor(VendorRequest request) {
        if (request == null) {
            return null;
        }
        Vendor vendor = new Vendor();
        vendor.setFirstName(request.getFirstName());
        vendor.setLastName(request.getLastName());
        vendor.setPhoneNumber(request.getPhoneNumber());
        vendor.setEmail(request.getEmail());
        vendor.setPassword(request.getPassword());
        vendor.setEmailConfirm(true);
        return vendor;
    }

    public Vendor updateVendor(Vendor vendor, VendorRequest request) {
        vendor.setFirstName(request.getFirstName());
        vendor.setLastName(request.getLastName());
        vendor.setPhoneNumber(request.getPhoneNumber());
        vendor.setEmail(request.getEmail());
        vendor.setPassword(request.getPassword());
        vendor.setEmailConfirm(true);
        return vendor;
    }
}
