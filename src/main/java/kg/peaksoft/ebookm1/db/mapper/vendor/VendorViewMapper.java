package kg.peaksoft.ebookm1.db.mapper.vendor;

import kg.peaksoft.ebookm1.api.payLoad.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.db.entity.Vendor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VendorViewMapper {

    public VendorResponse viewVendor(Vendor vendor) {
        if (vendor == null) {
            return null;
        }
        VendorResponse response = new VendorResponse();
        if (vendor.getId() != null) {
            response.setId(vendor.getId());
        }
        response.setName(vendor.getName());
        response.setEmail(vendor.getEmail());
        return response;
    }

    public List<VendorResponse> viewVendors(List<Vendor> vendors) {
        List<VendorResponse> responses = new ArrayList<>();
        for (Vendor vendor : vendors) {
            responses.add(viewVendor(vendor));
        }
        return responses;
    }
}
