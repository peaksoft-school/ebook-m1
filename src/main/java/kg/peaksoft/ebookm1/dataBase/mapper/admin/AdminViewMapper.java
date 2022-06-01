package kg.peaksoft.ebookm1.dataBase.mapper.admin;

import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AdminViewMapper {

    public AdminResponse viewAdmin(User admin) {
        if (admin == null) {
            return null;
        }
        AdminResponse response = new AdminResponse();
        if (admin.getId() != null) {
            response.setId(admin.getId());
        }
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setEmail(admin.getEmail());
        response.setPhoneNumber(admin.getPhoneNumber());
        response.setCreated(admin.getCreated());
        response.setIsActive(true);
        return response;
    }
}
