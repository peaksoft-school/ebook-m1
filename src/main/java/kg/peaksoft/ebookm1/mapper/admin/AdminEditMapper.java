package kg.peaksoft.ebookm1.mapper.admin;

import kg.peaksoft.ebookm1.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminEditMapper {

    private final RoleRepository roleRepository;

    public User createAdmin(AdminRequest request) {
        if (request == null) {
            return null;
        }
        User admin = new User();
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setPassword(request.getPassword());
        admin.setCreated(LocalDateTime.now());
        admin.setActive(true);

        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById(1L).get();
        roles.add(role);
        admin.setRoles(roles);
        return admin;
    }

    public User updateAdmin(User admin, AdminRequest request) {
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        return admin;
    }
}
