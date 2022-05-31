package kg.peaksoft.ebookm1.dataBase.mapper.admin;

import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.dataBase.entity.Role;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import kg.peaksoft.ebookm1.dataBase.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminViewMapper {

    private final RoleRepository roleRepository;

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

    public List<AdminResponse> viewAdmins() {
        List<AdminResponse> adminUsers = new ArrayList<>();
        Role role = roleRepository.findById(1L).get();
        for (User user : role.getUsers()) {
            adminUsers.add(viewAdmin(user));
        }
        return adminUsers;
    }
}
