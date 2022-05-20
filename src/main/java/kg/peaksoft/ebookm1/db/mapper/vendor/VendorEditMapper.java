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
        vendor.setName(request.getName());
        vendor.setEmail(request.getEmail());
        vendor.setPassword(request.getPassword());
//        vendor.setEmailConfirm(true);
        return vendor;
    }

    public Vendor updateVendor(Vendor vendor, VendorRequest request) {
        vendor.setName(request.getName());
        vendor.setEmail(request.getEmail());
        vendor.setPassword(request.getPassword());
        return vendor;
    }
}
