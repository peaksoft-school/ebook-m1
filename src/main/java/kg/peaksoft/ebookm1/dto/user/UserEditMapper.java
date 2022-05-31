package kg.peaksoft.ebookm1.dto.user;

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
public class UserEditMapper {

    private final RoleRepository roleRepository;

    public User createUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setActive(true);

        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById(3L).get();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    public User updateUser(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setActive(true);
        return user;
    }
}

