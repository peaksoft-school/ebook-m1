package kg.peaksoft.ebookm1.dataBase.mappers.user;

import kg.peaksoft.ebookm1.api.payloads.dto.user.UserRequest;
import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserEditMapper {
    public User createUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setActive(true);
        return user;
    }

    public User updateUser(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setActive(true);
        return user;
    }
}
