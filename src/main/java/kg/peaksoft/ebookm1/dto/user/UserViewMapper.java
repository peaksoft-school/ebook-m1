package kg.peaksoft.ebookm1.dto.user;

import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserViewMapper {

    private final RoleRepository roleRepository;

    public UserResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        if (user.getId() != null) {
            response.setId(Long.valueOf(user.getId()));
        }
        response.setFirstName(user.getFirstName());
        response.setEmail(user.getEmail());
        response.setCreated(user.getCreated());
        response.setActive(true);
        return response;
    }

//    public List<UserResponse> viewUsers(List<User> users) {
//        List<UserResponse> responses = new ArrayList<>();
//        for (User user : users) {
//            responses.add(viewUser(user));
//        }
//        return responses;
//    }

    public List<UserResponse> viewClients() {
        List<UserResponse> clientUsers = new ArrayList<>();
        Role role = roleRepository.findById(3L).get();
        for (User client : role.getUsers()) {
            clientUsers.add(viewUser(client));
        }
        return clientUsers;
    }
}
