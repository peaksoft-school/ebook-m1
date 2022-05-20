package kg.peaksoft.ebookm1.db.mapper.user;

import kg.peaksoft.ebookm1.api.payLoad.dto.user.UserResponse;
import kg.peaksoft.ebookm1.db.entity.securityEntity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserViewMapper {

    public UserResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        if (user.getId() != null) {
            response.setId(Long.valueOf(user.getId()));
        }
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        response.setCreated(user.getCreated());
        response.setActive(true);
        return response;
    }

    public List<UserResponse> viewUsers(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(viewUser(user));
        }
        return responses;
    }
}
