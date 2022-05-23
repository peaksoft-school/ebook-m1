package kg.peaksoft.ebookm1.dto.auth;

import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthMapper {
    public AuthResponse view(String token, String message, User user) {
        var authResponse = new AuthResponse();
        if (user != null) {
            setAuthority(authResponse, user.getRoles());
        }
        authResponse.setJwtToken(token);
        authResponse.setFirstName(user.getFirstName());
        authResponse.setEmail(user.getEmail());
        authResponse.setMessage(message);
        return authResponse;
    }

    private void setAuthority(AuthResponse authResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(role.getName());
        }
        authResponse.setAuthorities(authorities);
    }
}

